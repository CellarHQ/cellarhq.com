package com.cellarhq.auth.callbacks

import com.cellarhq.Messages
import com.cellarhq.auth.SecurityModule
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.OAuthAccount
import com.cellarhq.services.AccountService
import com.cellarhq.services.CellarService
import com.cellarhq.session.FlashMessage
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

    @Inject
    TwitterCallback(AccountService accountService, CellarService cellarService) {
        this.accountService = accountService
        this.cellarService = cellarService
    }

    @Override
    void accept(C context, P profile) {
        Request request = context.request

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
                context.request.get(SessionStorage).put(SecurityModule.SESSION_CELLAR_ID, oAuthAccount.cellarId)
                new DefaultSuccessCallback().defaultBehavior(context, profile)
            } else {
                // If we don't have an account, that means there was a screen name conflict. We'll still give them a
                // session, but it won't be capable of doing a whole lot.
                SessionStorage sessionStorage = request.get(SessionStorage)
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
