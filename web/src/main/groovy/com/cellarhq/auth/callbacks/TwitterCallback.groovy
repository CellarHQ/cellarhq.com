package com.cellarhq.auth.callbacks

import com.cellarhq.Messages
import com.cellarhq.auth.Role
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
import org.pac4j.oauth.profile.twitter.TwitterProfile
import ratpack.handling.Context
import ratpack.http.Request
import ratpack.session.store.SessionStorage

import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.function.BiConsumer

@Slf4j
@CompileStatic
class TwitterCallback<C extends Context, P extends TwitterProfile> implements BiConsumer<C, P> {

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
                Cellar cellar = makeCellarFrom(profile)
                account = makeOauthAccountFrom(profile, cellar)

                // What a shitty little hack: The original image isn't offered by TwitterProfile.
                accountService.create(account, profile.pictureUrl?.replace('_normal', ''))
            }

            return account
        } onError { Throwable e ->
            log.error(LogUtil.toLog(context.request, 'TwitterAuthCallback'), e)
            SessionUtil.setFlash(context.request, FlashMessage.error(Messages.UNEXPECTED_SERVER_ERROR))
            context.redirect('/logout')
        } then { OAuthAccount oAuthAccount ->
            context.request.get(SessionStorage).put(SecurityModule.SESSION_CELLAR_ID, oAuthAccount.cellarId)
            new DefaultSuccessCallback().defaultBehavior(context, profile)
        }
    }

    Cellar makeCellarFrom(TwitterProfile profile) {
        return new Cellar().with { Cellar self ->
            screenName = profile.username
            displayName = profile.displayName
            location = profile.location
            website = profile.profileUrl
            bio = profile.description
            lastLogin = Timestamp.valueOf(LocalDateTime.now())

            addRole(Role.MEMBER)
            return self
        }
    }

    OAuthAccount makeOauthAccountFrom(TwitterProfile profile, Cellar cellar) {
        return new OAuthAccount(
                username: profile.username,
                cellar: cellar
        )
    }
}
