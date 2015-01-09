package com.cellarhq.endpoints.settings

import static ratpack.handlebars.Template.handlebarsTemplate

import com.cellarhq.Messages
import com.cellarhq.auth.SecurityModule
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.EmailAccount
import com.cellarhq.services.account.EmailAccountVerificationService
import com.cellarhq.services.account.VerificationResult
import com.cellarhq.session.FlashMessage
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.form.Form
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain
import ratpack.session.store.SessionStorage

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory

@SuppressWarnings(['LineLength', 'MethodSize'])
@Slf4j
class LinkEmailAccountEndpoint implements Action<Chain> {

    EmailAccountVerificationService verificationService

    ValidatorFactory validatorFactory

    @Inject
    LinkEmailAccountEndpoint(ValidatorFactory validatorFactory,
                             EmailAccountVerificationService emailAccountVerificationService) {
        this.verificationService = emailAccountVerificationService
        this.validatorFactory = validatorFactory
    }

    @Override
    void execute(Chain chain) throws Exception {
        Groovy.chain(chain) {
            handler('settings/link-email/:token') {
                Cellar cellar = (Cellar) request.get(SessionStorage).get(SecurityModule.SESSION_CELLAR)

                byMethod {
                    get {
                        verificationService.verify(cellar, pathTokens['token']).subscribe { Boolean allowed ->
                            if (allowed) {
                                render handlebarsTemplate('settings/link-email-account-password.html', [
                                        title: 'Link Email Account',
                                        pageId: 'link-email-account-password',
                                        action: "/settings/link-email/${pathTokens['token']}"
                                ])
                            } else {
                                log.warn(LogUtil.toLog(request, 'Failed linking email to existing account', [
                                        cellar: cellar.id,
                                        error: Messages.ACCOUNT_LINK_TOKEN_UNKNOWN
                                ]))
                                SessionUtil.setFlash(request, FlashMessage.error(Messages.ACCOUNT_LINK_TOKEN_UNKNOWN))
                                redirect('/settings/link-email')
                            }
                        }
                    }

                    post {
                        Form form = parse(Form)

                        EmailAccount emailAccount = new EmailAccount().with { EmailAccount self ->
                            email = 'validation@validation.com'
                            password = form.password
                            return self
                        }

                        Validator validator = validatorFactory.validator
                        Set<ConstraintViolation<EmailAccount>> accountViolations = validator.validate(emailAccount)
                        boolean passwordsMatch = form.password == form.passwordConfirm
                        if (accountViolations.size() == 0 && passwordsMatch) {
                            verificationService
                                    .verifyAndCommit(cellar, pathTokens['token'], form.password).subscribe { VerificationResult result ->
                                if (result.success) {
                                    log.info(LogUtil.toLog(request, 'Linked email to existing account', [
                                            cellar: cellar.id
                                    ]))
                                    SessionUtil.setFlash(request, FlashMessage.success(Messages.ACCOUNT_LINK_EMAIL_SUCCESS))
                                    redirect('/yourcellar')
                                } else {
                                    log.warn(LogUtil.toLog(request, 'Failed linking email to existing account', [
                                            cellar: cellar.id,
                                            error : result.message
                                    ]))
                                    SessionUtil.setFlash(request, FlashMessage.error(result.message))
                                    redirect('/settings/link-email')
                                }
                            }
                        } else {
                            List<String> messages = []
                            accountViolations.each { messages << "${it.propertyPath.toString()} ${it.message}" }
                            if (!passwordsMatch) {
                                messages << 'passwords do not match'
                            }

                            SessionUtil.setFlash(
                                    request,
                                    FlashMessage.error(Messages.FORM_VALIDATION_ERROR, messages))
                            redirect('/settings/link-email/:token')
                        }
                    }
                }
            }

            handler('settings/link-email') {
                Cellar cellar = (Cellar) request.get(SessionStorage).get(SecurityModule.SESSION_CELLAR)

                byMethod {
                    get {
                        render handlebarsTemplate('settings/link-email-account.html', [
                                title: 'Link Email Account',
                                pageId: 'link-email-account'
                        ])
                    }

                    post {
                        Form form = parse(Form)

                        EmailAccount pendingAccount = new EmailAccount(email: form.email)

                        verificationService
                                .sendVerification(cellar, pendingAccount).subscribe { VerificationResult result ->
                            if (result.success) {
                                log.info(LogUtil.toLog(request, 'Attempting email account link', [
                                        cellar: cellar.id,
                                        email: form.email
                                ]))
                                SessionUtil.setFlash(request, FlashMessage.success(
                                        Messages.ACCOUNT_LINK_EMAIL_VERIFICATION_SENT
                                ))
                                // TODO: Should we send them to a landing page instead?
                                redirect('/yourcellar')
                            } else {
                                log.warn(LogUtil.toLog(request, 'Failed attempting to link email account', [
                                        error: result.message,
                                        cellar: cellar.id,
                                        email: form.email
                                ]))
                                SessionUtil.setFlash(request, FlashMessage.error(result.message))
                                redirect('/settings/link-email')
                            }
                        }
                    }
                }
            }
        }
    }
}
