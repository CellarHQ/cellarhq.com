package com.cellarhq.endpoints.auth

import com.cellarhq.CellarHQModule
import com.cellarhq.Messages
import com.cellarhq.domain.EmailAccount
import com.cellarhq.services.AccountService
import com.cellarhq.services.email.EmailService
import com.cellarhq.session.FlashMessage
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.form.Form
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class ForgotPasswordEndpoint implements Action<Chain> {

    private final AccountService accountService
    private final EmailService emailService

    @Inject
    ForgotPasswordEndpoint(AccountService accountService, EmailService emailService) {
        this.accountService = accountService
        this.emailService = emailService
    }

    @Override
    void execute(Chain chain) throws Exception {
        Groovy.chain(chain) {
            handler {
                byMethod {
                    get {
                        render handlebarsTemplate('forgot-password.html',
                            title: 'Forgot Password',
                            action: '/forgot-password',
                            pageId: 'forgot-password')
                    }
                    post {
                        Form form = parse(Form)

                        blocking {
                            String recoveryHash = null
                            log.info(LogUtil.toLog(request, 'ForgotPasswordEmail', [
                                email: form.email
                            ]))
                            EmailAccount emailAccount = accountService.findByEmail(form.email)
                            if (emailAccount) {
                                recoveryHash = accountService.startPasswordRecovery(emailAccount)
                            }
                            recoveryHash
                        } onError { Throwable t ->
                            log.error(LogUtil.toLog(request, 'ForgotPasswordFailure', [
                                exception: t.toString()
                            ]), t)

                            SessionUtil.setFlash(request, FlashMessage.error(Messages.UNEXPECTED_SERVER_ERROR))
                            redirect('/forgot-password')
                        } then { String recoveryHash ->
                            if (recoveryHash) {
                                // TODO: We should definitely do HTML emails as well.
                                emailService.sendEmail(form.email, 'CellarHQ: Password recovery', """\
                                | Hey there!
                                |
                                | Forgot your password, huh? Don't worry, we'll help you change it: Follow the link
                                | below to change it. It will only be valid for 24 hours, so make it quick!
                                |
                                | http://${CellarHQModule.hostname}/forgot-password/${recoveryHash}
                                |
                                | Cheers!
                                | Kyle and Rob
                            """.stripMargin())
                                SessionUtil.setFlash(
                                    request,
                                    FlashMessage.success(Messages.FORGOT_PASSWORD_EMAIL_SENT_NOTICE))
                                redirect('/forgot-password')
                            } else {
                                SessionUtil.setFlash(request, FlashMessage.error(Messages.FORGOT_PASSWORD_ERROR))
                                redirect('/forgot-password')
                            }
                        }
                    }
                }
            }
        }
    }
}
