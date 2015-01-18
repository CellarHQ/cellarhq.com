package com.cellarhq.auth.rememberme

import com.cellarhq.auth.AuthenticationModule
import com.cellarhq.auth.PasswordService
import com.cellarhq.api.services.CellarService
import com.cellarhq.auth.RememberMeFlag
import com.cellarhq.auth.callbacks.DefaultSuccessCallback
import com.cellarhq.common.CellarHQConfig
import com.cellarhq.domain.Cellar
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import io.netty.handler.codec.http.Cookie
import org.mindrot.jbcrypt.BCrypt
import org.pac4j.core.profile.CommonProfile
import ratpack.handling.Context
import ratpack.http.Request
import ratpack.http.Response
import ratpack.session.store.SessionStorage

/**
 * Most of this code originally comes from Spring Security, The algorithm has been modified slightly
 * to not include the users password in the hashed cookie.
 *
 * Essentially a cookie is created when a user logs in with the format:
 *
 * USERNAME:TIMESTAMP:SECRETHASH
 *
 * The secret hash is username:tokenExpiryTime:key
 *
 * When a user comes back to the sight and the token is present, if the secret hash for the given username matches, then
 * we log in the user automatically.
 *
 * @author Luke Taylor
 * @since 2.0
 */
@Slf4j
public class RememberMeService {

    public static final int TWO_WEEKS_S = 1209600

    final static String COOKIE_NAME = 'CELLARHQ_REMEMBER_ME'
    final static String DELIMITER = ':'
    
    private final String KEY

    private final CellarService cellarService

    @Inject
    RememberMeService(CellarService cellarService, CellarHQConfig cellarHQConfig) {
        this.cellarService = cellarService
        this.KEY = cellarHQConfig.secretRememberMeToken
    }

    void autoLogin(Context context) {
        String rememberMeCookie = extractRememberMeCookie(context.request)

        if (!rememberMeCookie) {
            log.debug('NO Remember-me cookie detected')
            return
        }

        log.debug('Remember-me cookie detected')

        if (rememberMeCookie.length() == 0) {
            log.debug('Cookie was empty')
            cancelCookie(context.request, context.response)
            return
        }

        try {
            String[] cookieTokens = decodeCookie(rememberMeCookie)
            processAutoLoginCookie(cookieTokens, context)
            log.debug('Remember-me cookie accepted')
        } catch (InvalidCookieException invalidCookie) {
            log.debug("Invalid remember-me cookie: ${invalidCookie.message}")
            cancelCookie(context.request, context.response)
        }
    }

    void onLoginSuccess(Request request, Response response, RememberMeFlag rememberMeFlag, CommonProfile profile) {
        if (!rememberMeFlag.rememberMe) {
            return
        }

        String cellarId = request.get(SessionStorage)?.get(AuthenticationModule.SESSION_CELLAR_ID)?.toString()

        // If unable to find a username and password, just abort as TokenBasedRememberMeServices is
        // unable to construct a valid token in this case.
        if (!cellarId) {
            log.debug('Unable to retrieve username')
            return
        }

        int tokenLifetime = TWO_WEEKS_S
        long expiryTime = System.currentTimeMillis()
        // SEC-949
        expiryTime += 1000L * (tokenLifetime < 0 ? TWO_WEEKS_S : tokenLifetime)

        String unhashedSignature = makeTokenSignature(expiryTime, cellarId)
        String hashedSignature = BCrypt.hashpw(unhashedSignature, BCrypt.gensalt(PasswordService.BCRYPT_LOG_ROUNDS))

        setCookie([cellarId, Long.toString(expiryTime), hashedSignature], tokenLifetime, request, response)

        log.debug("Added remember-me cookie for user '${cellarId}', expiry: '${new Date(expiryTime)}'")
    }

    final void loginFail(Request request, Response response) {
        log.debug('Interactive login attempt was unsuccessful.')
        cancelCookie(request, response)
    }

    void processAutoLoginCookie(String[] cookieTokens, Context context) {
        if (cookieTokens.length != 3) {
            throw new InvalidCookieException(
                    "Cookie token did not contain 3 tokens, but contained '${Arrays.asList(cookieTokens)}'")
        }

        long tokenExpiryTime

        try {
            tokenExpiryTime = new Long(cookieTokens[1]).longValue()
        } catch (NumberFormatException nfe) {
            throw new InvalidCookieException(
                    "Cookie token[1] did not contain a valid number (contained '${cookieTokens[1]}')")
        }

        if (isTokenExpired(tokenExpiryTime)) {
            throw new InvalidCookieException('Cookie token[1] has expired (expired on \''
                    + new Date(tokenExpiryTime) + '; current time is \'' + new Date() + '\')')
        }

        Long cellarId = Long.parseLong(cookieTokens[0])

        Cellar cellar = cellarService.getBlocking(cellarId)
        String expectedTokenSignature = makeTokenSignature(tokenExpiryTime, cellar.id.toString())

        if (!BCrypt.checkpw(expectedTokenSignature, cookieTokens[2])) {
            String warning = "Cookie token contained '${cookieTokens[2]}' but expected '${expectedTokenSignature}'"
            log.warn(warning)
            throw new InvalidCookieException(warning)
        }

        CommonProfile profile = new CommonProfile()
        profile.setId(cellar.screenName)
        profile.addAttribute(CommonProfile.USERNAME, cellar.screenName)

        log.debug('trying to add profile to session')
        Request request = context.request
        SessionStorage sessionStorage = request.get(SessionStorage)
        sessionStorage.put(DefaultSuccessCallback.USER_PROFILE, profile)
        sessionStorage.put(AuthenticationModule.SESSION_CELLAR_ID, cellar.id)
        log.debug('profile added to session')

    }

    /**
     * Calculates the digital signature to be put in the cookie.
     */
    protected String makeTokenSignature(long tokenExpiryTime, String username) {
        return "$username:$tokenExpiryTime:$KEY"
    }

    protected boolean isTokenExpired(long tokenExpiryTime) {
        return tokenExpiryTime < System.currentTimeMillis()
    }


    String[] decodeCookie(String cookieValue) {
        String newCookieValue = cookieValue
        for (int j = 0; j < newCookieValue.length() % 4; j++) {
            newCookieValue = newCookieValue + '='
        }

        String cookieAsPlainText
        try {
            cookieAsPlainText = new String(Base64.decoder.decode(newCookieValue.bytes))
        } catch (IllegalArgumentException ex) {
            throw new InvalidCookieException("Cookie token was not Base64 encoded; value was '$newCookieValue'")
        }

        String[] tokens = cookieAsPlainText.split(DELIMITER)

        if ((tokens[0].equalsIgnoreCase('http') || tokens[0].equalsIgnoreCase('https')) && tokens[1].startsWith('//')) {
            // Assume we've accidentally split a URL (OpenID identifier)
            String[] newTokens = new String[tokens.length - 1]
            newTokens[0] = tokens[0] + DELIMITER + tokens[1]
            System.arraycopy(tokens, 2, newTokens, 1, newTokens.length - 1)
            tokens = newTokens
        }

        return tokens
    }

    String extractRememberMeCookie(Request request) {
        Set<Cookie> cookies = request.cookies

        if (!cookies) {
            return null
        }

        Cookie cookie = cookies.find { Cookie cookie ->
            COOKIE_NAME == cookie.name
        }

        return cookie?.value
    }

    /**
     * Sets a "cancel cookie" (with maxAge = 0) on the response to disable persistent logins.
     */
    void cancelCookie(Request request, Response response) {
        log.debug('Cancelling cookie')
        response.expireCookie(COOKIE_NAME)
    }

    protected void setCookie(List<String> tokens, int maxAge, Request request, Response response) {
        String cookieValue = encodeCookie(tokens)

        Cookie cookie = response.cookie(COOKIE_NAME, cookieValue)
        cookie.setMaxAge(maxAge)
    }

    /**
     * Inverse operation of decodeCookie.
     *
     * @param cookieTokens the tokens to be encoded.
     * @return base64 encoding of the tokens concatenated with the ":" delimiter.
     */
    protected String encodeCookie(List<String> cookieTokens) {
        StringBuilder sb = new StringBuilder()
        for (int i = 0; i < cookieTokens.size(); i++) {
            sb.append(cookieTokens[i])

            if (i < cookieTokens.size() - 1) {
                sb.append(DELIMITER)
            }
        }

        String value = sb.toString()

        sb = new StringBuilder(new String(Base64.encoder.encode(value.bytes)))

        while (sb.charAt(sb.length() - 1) == '=') {
            sb.deleteCharAt(sb.length() - 1)
        }

        return sb.toString()
    }
}
