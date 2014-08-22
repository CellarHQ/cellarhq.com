package com.cellarhq.endpoints.auth

import static ratpack.handlebars.Template.handlebarsTemplate

import com.cellarhq.Messages
import com.cellarhq.domain.jooq.EmailAccount
import com.cellarhq.services.JooqAccountService
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

@SuppressWarnings(['AbcMetric', 'NestedBlockDepth'])
@Slf4j
class ChangePasswordEndpoint extends GroovyHandler {

    private final JooqAccountService accountService
    private final ValidatorFactory validatorFactory

    @Inject
    ChangePasswordEndpoint(JooqAccountService accountService, ValidatorFactory validatorFactory) {
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

                        redirect(500, '/forgot-password?error=' + Messages.UNEXPECTED_SERVER_ERROR)
                    } then { EmailAccount emailAccount ->
                        if (emailAccount) {
                            render handlebarsTemplate('change-password.html',
                                    title: 'Change Password',
                                    action: "/forgot-password/${context.pathTokens['id']}",
                                    method: 'post',
                                    error: request.queryParams.error ?: '',
                                    errorMessages: SessionUtil.getFlashMessages(request).collect { [message: it] },
                                    pageId: 'change-password',
                                    loggedIn: false)
                        } else {
                            redirect('/forgot-password?error=' + Messages.FORGOT_PASSWORD_UNKNOWN_REQUEST)
                        }
                    }
                }
                post {
                    Form form = parse(Form)

                    blocking {
                        Map result = [
                                success: false,
                                message: ''
                        ]

                        EmailAccount account = accountService.findByPasswordChangeRequestHash(context.pathTokens['id'])
                        if (account) {
                            account.password = form.password
                            account.passwordConfirm = form.passwordConfirm

                            Validator validator = validatorFactory.validator
                            Set<ConstraintViolation<EmailAccount>> accountViolations = validator.validate(account)
                            boolean passwordsMatch = account.password == account.passwordConfirm
                            if (accountViolations.size() == 0 && passwordsMatch) {
                                accountService.changePassword(account, context.pathTokens['id'])
                                result.success = true
                            }  else {
                                List<String> messages = []
                                accountViolations.each { messages << "${it.propertyPath.toString()} ${it.message}" }
                                if (!passwordsMatch) {
                                    messages << 'passwords do not match'
                                }

                                SessionUtil.setFlashMessages(request, messages)

                                result.message = Messages.FORM_VALIDATION_ERROR
                            }
                        } else {
                            result.message = Messages.FORGOT_PASSWORD_UNKNOWN_REQUEST
                        }

                        return result
                    } onError { Throwable t ->
                        log.error(LogUtil.toLog('ForgotPasswordFailure', [
                                exception: t.toString()
                        ]))

                        redirect(500, '/forgot-password?error=' + Messages.UNEXPECTED_SERVER_ERROR)
                    } then { Map result ->
                        if (result.success) {
                            redirect('/login?success=' + Messages.FORGOT_PASSWORD_LOGIN_WITH_NEW_PASSWORD)
                        } else {
                            redirect("/forgot-password/${context.pathTokens['id']}?error=" + (String) result.message)
                        }
                    }
                }
            }
        }
    }
}
