package com.cellarhq.auth.callbacks

import com.cellarhq.common.Messages
import com.cellarhq.auth.AuthenticationModule
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.OAuthAccount
import com.cellarhq.auth.services.AccountService
import com.cellarhq.api.services.CellarService
import com.cellarhq.auth.services.TwitterAccountVerificationService
import com.cellarhq.common.session.FlashMessage
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.jooq.exception.DataAccessException
import org.pac4j.oauth.profile.twitter.TwitterProfile
import ratpack.handling.Context
import ratpack.http.Request
import ratpack.session.store.SessionStorage

import java.util.function.BiConsumer

@Slf4j
@CompileStatic
class TwitterCallback<C extends Context, P extends TwitterProfile> implements BiConsumer<C, P> {

    static final String REQUIRE_SCREEN_NAME_CHANGE = 'requireScreenNameChange'

    private final AccountService accountService
    private final CellarService cellarService
    private final TwitterAccountVerificationService verificationService

    @Inject
    TwitterCallback(AccountService accountService,
                    CellarService cellarService,
                    TwitterAccountVerificationService verificationService) {

        this.accountService = accountService
        this.cellarService = cellarService
        this.verificationService = verificationService
    }

    @Override
    void accept(C context, P profile) {
        Request request = context.request

        if (isLinkAccountAction(request)) {
            handleAccountLinkRequest(context, request, profile)
        } else {
            handleLogin(context, request, profile)
        }
    }

    boolean isLinkAccountAction(Request request) {
        SessionStorage sessionStorage = request.get(SessionStorage)
        return sessionStorage.getOrDefault(DefaultSuccessCallback.USER_PROFILE, false) &&
                sessionStorage.getOrDefault(AuthenticationModule.SESSION_CELLAR, false)
    }

    @SuppressWarnings('ClosureAsLastMethodParameter')
    void handleAccountLinkRequest(Context context, Request request, P profile) {
        SessionStorage sessionStorage = request.get(SessionStorage)

        Cellar cellar = (Cellar) sessionStorage.get(AuthenticationModule.SESSION_CELLAR)
        verificationService.commit(cellar, profile).subscribe({ Boolean result ->
            SessionUtil.setFlash(request, FlashMessage.success(Messages.ACCOUNT_LINK_TWITTER_SUCCESS))
            context.redirect('/settings')
        }, { Throwable t ->
            if (t instanceof DataAccessException && t.message.contains('account_oauth_client_username_key')) {
                SessionUtil.setFlash(request, FlashMessage.error(String.format(
                        Messages.ACCOUNT_LINK_TWITTER_SCREEN_NAME_UNAVAILABLE2,
                        profile.username
                )))
            } else {
                log.error('LinkTwitterAccountFailure', t)
                SessionUtil.setFlash(request, FlashMessage.error(Messages.UNEXPECTED_SERVER_ERROR))
            }

            context.redirect('/settings/link-twitter')
        })
    }

    void handleLogin(Context context, Request request, P profile) {
        SessionStorage sessionStorage = request.get(SessionStorage)

        context.blocking {
            log.info(LogUtil.toLog(request, 'Twitter Login Attempt', [
                    twitterProfile: profile.username
            ]))

            OAuthAccount account = accountService.findByCredentials(profile.username)
            if (account) {
                cellarService.updateLoginStats(account.cellar, profile)
            } else {
                Cellar cellar = Cellar.makeFrom(profile)
                account = makeOauthAccountFrom(profile, cellar)

                try {
                    // What a shitty little hack: The original image isn't offered by TwitterProfile.
                    accountService.create(account, profile.pictureUrl?.replace('_normal', ''))
                } catch (DataAccessException e) {
                    if (e.message.contains('unq_cellar_screen_name')) {
                        account = null
                        log.info(LogUtil.toLog(context.request, 'TwitterScreenNameTaken', [
                                msg: 'Twitter screen name already taken; prompting user for new name',
                                twitterProfile: profile.username
                        ]))
                        context.redirect("/settings/screen-name-conflict?conflict=${profile.username}")
                    }
                }
            }

            return account
        } onError { Throwable e ->
            log.error(LogUtil.toLog(context.request, 'TwitterAuthCallback'), e)
            SessionUtil.setFlash(context.request, FlashMessage.error(Messages.UNEXPECTED_SERVER_ERROR))
            context.redirect('/logout')
        } then { OAuthAccount oAuthAccount ->
            if (oAuthAccount) {
                sessionStorage.put(AuthenticationModule.SESSION_CELLAR_ID, oAuthAccount.cellarId)
                new DefaultSuccessCallback().defaultBehavior(context, profile, oAuthAccount.cellar)
            } else {
                // If we don't have an account, that means there was a screen name conflict. We'll still give them a
                // session, but it won't be capable of doing a whole lot.
                sessionStorage.put(DefaultSuccessCallback.USER_PROFILE, profile)
                sessionStorage.put(REQUIRE_SCREEN_NAME_CHANGE, true)
            }
        }
    }

    OAuthAccount makeOauthAccountFrom(TwitterProfile profile, Cellar cellar) {
        return new OAuthAccount(
                username: profile.username,
                cellar: cellar
        )
    }
}
