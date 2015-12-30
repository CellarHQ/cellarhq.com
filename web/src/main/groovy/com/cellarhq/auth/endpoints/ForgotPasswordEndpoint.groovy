package com.cellarhq.auth.endpoints

import com.cellarhq.auth.services.AccountService
import com.cellarhq.common.CellarHQConfig
import com.cellarhq.common.Messages
import com.cellarhq.common.services.email.EmailService
import com.cellarhq.common.session.FlashMessage
import com.cellarhq.domain.EmailAccount
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.exec.Blocking
import ratpack.form.Form
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class ForgotPasswordEndpoint  extends GroovyHandler {

  private final AccountService accountService
  private final EmailService emailService
  private final CellarHQConfig cellarHQConfig

  @Inject
  ForgotPasswordEndpoint(AccountService accountService, EmailService emailService, CellarHQConfig cellarHQConfig) {
    this.accountService = accountService
    this.emailService = emailService
    this.cellarHQConfig = cellarHQConfig
  }

  @Override
  protected void handle(GroovyContext context) {
    context.with {
      byMethod {
        get {
          render handlebarsTemplate('forgot-password.html',
            title: 'Forgot Password',
            action: '/forgot-password',
            pageId: 'forgot-password')
        }
        post {
          parse(Form).then { Form form ->

            Blocking.get {
              String recoveryHash = null
              log.info(LogUtil.toLog(request, 'ForgotPasswordEmail', [
                email: form.email
              ]))
              EmailAccount emailAccount = accountService.findByEmail(form.email)
              if (emailAccount) {
                recoveryHash = accountService.startPasswordRecovery(emailAccount)
              }
              recoveryHash
            }.onError { Throwable t ->
              log.error(LogUtil.toLog(request, 'ForgotPasswordFailure', [
                exception: t.toString()
              ]), t)

              SessionUtil.setFlash(context, FlashMessage.error(Messages.UNEXPECTED_SERVER_ERROR))
              redirect('/forgot-password')
            }.then { String recoveryHash ->
              if (recoveryHash) {
                emailService.sendEmail(form.email, 'CellarHQ: Password recovery', """\
                                | Hey there!
                                |
                                | Forgot your password, huh? Don't worry, we'll help you change it: Follow the link
                                | below to change it. It will only be valid for 24 hours, so make it quick!
                                |
                                | https://${cellarHQConfig.hostName}/forgot-password/${recoveryHash}
                                |
                                | Cheers!
                                | Kyle and Rob
                            """.stripMargin())
                SessionUtil.setFlash(
                  context,
                  FlashMessage.success(Messages.FORGOT_PASSWORD_EMAIL_SENT_NOTICE))
                context.redirect('/forgot-password')
              } else {
                SessionUtil.setFlash(context, FlashMessage.error(Messages.FORGOT_PASSWORD_ERROR))
                context.redirect('/forgot-password')
              }
            }
          }
        }
      }
    }
  }
}
