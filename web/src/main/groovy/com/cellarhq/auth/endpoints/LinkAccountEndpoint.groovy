package com.cellarhq.auth.endpoints

import static ratpack.handlebars.Template.handlebarsTemplate

import com.cellarhq.common.Messages
import com.cellarhq.auth.AuthenticationModule
import com.cellarhq.auth.callbacks.TwitterCallback
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.OAuthAccount
import com.cellarhq.auth.services.AccountService
import com.cellarhq.common.session.FlashMessage
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.pac4j.oauth.profile.twitter.TwitterProfile
import ratpack.form.Form
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain
import ratpack.handling.Context
import ratpack.registry.NotInRegistryException
import ratpack.session.store.SessionStorage

@Slf4j
class LinkAccountEndpoint implements Action<Chain> {

    private final AccountService accountService

    @Inject
    LinkAccountEndpoint(AccountService accountService) {
        this.accountService = accountService
    }

    @Override
    void execute(Chain chain) throws Exception {
        Groovy.chain(chain) {
            handler {
                // If the app has determined the user needs to change their screen name before registering, this
                // flag will be set and will redirect any identified paths to the screen name conflict page for
                // resolution.
                if (request.get(SessionStorage).getOrDefault(TwitterCallback.REQUIRE_SCREEN_NAME_CHANGE, false)) {
                    List<String> redirectPaths = [
                            'yourcellar',
                            'settings'
                    ]
                    if (redirectPaths.contains(request.path)) {
                        redirect('settings/screen-name-conflict?conflict=' + request.get(TwitterProfile).username)
                    }
                }
                next()
            }

            handler('settings/screen-name-conflict') {
                byMethod {
                    get {
                        if (isUnauthorized(context)) {
                            redirectOnUnauthorized(context)
                        } else {
                            render handlebarsTemplate('settings/screen-name-conflict.html',
                                    title: 'Choose Screen Name',
                                    pageId: 'screenNameConflict',
                                    conflict: request.queryParams.get('conflict'))
                        }
                    }

                    post {
                        if (!isUnauthorized(context)) {
                            return redirectOnUnauthorized(context)
                        }

                        Form form = parse(Form)

                        TwitterProfile profile = request.get(TwitterProfile)

                        log.info(LogUtil.toLog(request, 'ChangeScreenName', [
                                msg: 'User is changing their screen name after conflict',
                                twitterProfile: profile.username,
                                screenName: form.screenName
                        ]))

                        blocking {
                            Cellar cellar = Cellar.makeFrom(profile)
                            cellar.screenName = form.screenName
                            OAuthAccount account = new OAuthAccount(username: profile.username, cellar: cellar)

                            return accountService.create(account, profile.pictureUrl?.replace('_normal', ''))
                        } onError { Throwable e ->
                            log.error(LogUtil.toLog(request, 'ScreenNameConflict'), e)
                            SessionUtil.setFlash(request, FlashMessage.error(Messages.UNEXPECTED_SERVER_ERROR))

                            render handlebarsTemplate('settings/screen-name-conflict.html',
                                    title: 'Choose Screen Name',
                                    pageId: 'screenNameConflict')
                        } then { OAuthAccount oAuthAccount ->
                            log.info(LogUtil.toLog(request, 'ChangeScreenName', [
                                    msg: 'User changed their screen name after a conflict',
                                    twitterProfile: profile.username,
                                    screenName: form.screenName
                            ]))
                            SessionUtil.setFlash(request, FlashMessage.success(Messages.SETTINGS_SCREEN_NAME_CHANGED))

                            SessionStorage sessionStorage = request.get(SessionStorage)
                            sessionStorage.put(AuthenticationModule.SESSION_CELLAR_ID, oAuthAccount.cellarId)
                            sessionStorage.remove(TwitterCallback.REQUIRE_SCREEN_NAME_CHANGE)

                            redirect('/yourcellar')
                        }
                    }
                }
            }
        }
    }

    /**
     * Only allow accounts with a TwitterProfile & no account.
     *
     * This will change in the future when we support self-service account merging.
     */
    boolean isUnauthorized(Context context) {
        if (context.request.get(SessionStorage).get(AuthenticationModule.SESSION_CELLAR_ID, false)) {
            return true
        }

        try {
            context.request.get(TwitterProfile)
        } catch (NotInRegistryException e) {
            return true
        }

        return false
    }

    void redirectOnUnauthorized(Context context) {
        log.warn(LogUtil.toLog(context.request, 'IllegalChangeScreenNameAttempt', [
                msg: 'User tried to change screen name without a twitter account'
        ]))
        SessionUtil.setFlash(context.request, FlashMessage.error(Messages.UNAUTHORIZED_ERROR))
        context.redirect('/')
    }
}
