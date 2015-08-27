package com.cellarhq.auth.endpoints

import static ratpack.handlebars.Template.handlebarsTemplate

import com.cellarhq.common.Messages
import com.cellarhq.auth.AuthenticationModule
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.OAuthAccount
import com.cellarhq.auth.services.TwitterAccountVerificationService
import com.cellarhq.common.session.FlashMessage
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.form.Form
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain
import ratpack.session.Session

@SuppressWarnings(['LineLength'])
@Slf4j
class LinkTwitterAccountEndpoint implements Action<Chain> {

    TwitterAccountVerificationService verificationService

    @Inject
    LinkTwitterAccountEndpoint(TwitterAccountVerificationService verificationService) {
        this.verificationService = verificationService
    }

    @Override
    void execute(Chain chain) throws Exception {
        Groovy.chain(chain) {
            path('settings/link-twitter') {
                byMethod {
                    get {
                        render handlebarsTemplate('settings/link-twitter-account.html', [
                                title: 'Link a Twitter Account',
                                pageId: 'link-twitter-account'
                        ])
                    }

                    post {
                        Cellar cellar = (Cellar) request.get(Session).get(AuthenticationModule.SESSION_CELLAR)

                        Form form = parse(Form)
                        OAuthAccount pendingAccount = new OAuthAccount(username: form.username)

                        verificationService.linkAllowed(pendingAccount).subscribe { Boolean allowed ->
                            if (allowed) {
                                log.info(LogUtil.toLog(request, 'Attempting to link Twitter account', [
                                        cellar: cellar.id,
                                        twitter: form.username
                                ]))

                                // TODO: Can't find Clients in registry?
//                                RatpackWebContext webContext = new RatpackWebContext(context)
//                                redirect(context.get(Clients).findClient(TwitterClient).getRedirectionUrl(webContext))
                                redirect('/pac4j-callback?client_name=TwitterClient&needs_client_redirection=true')
                            } else {
                                log.warn(LogUtil.toLog(request, 'Failed attempting to link Twitter (unavailable)', [
                                        cellar: cellar.id,
                                        twitter: form.username
                                ]))
                                SessionUtil.setFlash(context, FlashMessage.error(String.format(
                                        Messages.ACCOUNT_LINK_TWITTER_SCREEN_NAME_UNAVAILABLE,
                                        pendingAccount.username
                                )))
                                redirect('/settings/link-twitter')
                            }
                        }
                    }
                }
            }
        }
    }
}
