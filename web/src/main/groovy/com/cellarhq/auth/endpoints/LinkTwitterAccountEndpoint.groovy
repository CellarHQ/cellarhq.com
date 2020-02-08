package com.cellarhq.auth.endpoints

import com.cellarhq.auth.services.TwitterAccountVerificationService
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.OAuthAccount
import com.cellarhq.util.LogUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.form.Form
import ratpack.groovy.handling.GroovyChainAction
import ratpack.session.Session

import static com.cellarhq.auth.AuthenticationModule.SESSION_CELLAR
import static com.cellarhq.common.Messages.ACCOUNT_LINK_TWITTER_SCREEN_NAME_UNAVAILABLE
import static com.cellarhq.common.session.FlashMessage.error
import static com.cellarhq.util.SessionUtil.setFlash
import static java.lang.String.format
import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class LinkTwitterAccountEndpoint extends GroovyChainAction {

  TwitterAccountVerificationService verificationService

  @Inject
  LinkTwitterAccountEndpoint(TwitterAccountVerificationService verificationService) {
    this.verificationService = verificationService
  }

  @Override
  void execute() throws Exception {
    path('settings/link-twitter') {
      byMethod {
        get {
          render handlebarsTemplate('settings/link-twitter-account.html', [
            title : 'Link a Twitter Account',
            pageId: 'link-twitter-account'
          ])
        }

        post {
          Cellar cellar = (Cellar) request.get(Session).get(SESSION_CELLAR)

          parse(Form).then { Form form ->
            OAuthAccount pendingAccount = new OAuthAccount(username: form.username)

            verificationService.linkAllowed(pendingAccount).then { Boolean allowed ->
              if (allowed) {
                log.info(LogUtil.toLog(request, 'Attempting to link Twitter account', [
                  cellar : cellar.id,
                  twitter: form.username
                ]))

                redirect('/pac4j-callback?client_name=TwitterClient&needs_client_redirection=true')
              } else {
                log.warn(LogUtil.toLog(request, 'Failed attempting to link Twitter (unavailable)', [
                  cellar : cellar.id,
                  twitter: form.username
                ]))
                setFlash(context, error(format(
                  ACCOUNT_LINK_TWITTER_SCREEN_NAME_UNAVAILABLE,
                  pendingAccount.username
                ))).then {
                  redirect('/settings/link-twitter')
                }
              }
            }
          }
        }
      }
    }
  }
}
