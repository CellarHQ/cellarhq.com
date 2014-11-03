package com.cellarhq.endpoints.auth

import static ratpack.handlebars.Template.handlebarsTemplate

import com.cellarhq.Messages
import com.cellarhq.domain.EmailAccount
import com.cellarhq.services.AccountService
import com.cellarhq.session.FlashMessage
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.form.Form
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory

@Slf4j
class ChangePasswordEndpoint extends GroovyHandler {

    private final AccountService accountService
    private final ValidatorFactory validatorFactory

    @Inject
    ChangePasswordEndpoint(AccountService accountService, ValidatorFactory validatorFactory) {
        this.accountService = accountService
        this.validatorFactory = validatorFactory
    }

    @Override
    protected void handle(GroovyContext context) {
        context.with {
            byMethod {
                get {
                    blocking {
                        accountService.findByPasswordChangeRequestHash(context.pathTokens['id'])
                    } onError { Throwable t ->
                        log.error(LogUtil.toLog('ForgotPasswordFailure', [
                            exception: t.toString()
                        ]), t)

                        SessionUtil.setFlash(request, FlashMessage.error(Messages.UNEXPECTED_SERVER_ERROR))
                        redirect('/forgot-password')
                    } then { EmailAccount emailAccount ->
                        if (emailAccount) {
                            render handlebarsTemplate('change-password.html',
                                title: 'Change Password',
                                action: "/forgot-password/${context.pathTokens['id']}",
                                pageId: 'change-password')
                        } else {
                            SessionUtil.setFlash(request, FlashMessage.error(Messages.FORGOT_PASSWORD_UNKNOWN_REQUEST))
                            redirect('/forgot-password')
                        }
                    }
                }
                post {
                    Form form = parse(Form)

                    EmailAccount account = accountService.findByPasswordChangeRequestHash(context.pathTokens['id'])
                    if (account) {
                        log.info(LogUtil.toLog('ChangingPassword', [
                            accountId: account.id
                        ]))
                        account.password = form.password
                        account.passwordConfirm = form.passwordConfirm

                        Validator validator = validatorFactory.validator
                        Set<ConstraintViolation<EmailAccount>> accountViolations = validator.validate(account)
                        boolean passwordsMatch = account.password == account.passwordConfirm
                        if (accountViolations.size() == 0 && passwordsMatch) {


                            blocking {
                                accountService.changePassword(account, context.pathTokens['id'])
                            } onError { Throwable t ->
                                log.error(LogUtil.toLog('ForgotPasswordFailure', [
                                    exception: t.toString()
                                ]))

                                redirect('/forgot-password?error=' + Messages.UNEXPECTED_SERVER_ERROR)
                            } then {
                                SessionUtil.setFlash(
                                    request,
                                    FlashMessage.success(Messages.FORGOT_PASSWORD_LOGIN_WITH_NEW_PASSWORD))
                                redirect('/login')
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
                            redirect("/forgot-password/${context.pathTokens['id']}")
                        }
                    } else {
                        SessionUtil.setFlash(request, FlashMessage.error(Messages.FORGOT_PASSWORD_UNKNOWN_REQUEST))
                        redirect("/forgot-password/${context.pathTokens['id']}")
                    }
                }
            }
        }
    }
}
