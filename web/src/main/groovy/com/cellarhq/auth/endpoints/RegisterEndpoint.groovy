package com.cellarhq.auth.endpoints

import com.cellarhq.auth.AuthenticationModule
import com.cellarhq.common.validation.ValidationErrorMapper

import static ratpack.handlebars.Template.handlebarsTemplate

import com.cellarhq.common.Messages
import com.cellarhq.auth.Role
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.EmailAccount
import com.cellarhq.auth.services.AccountService
import com.cellarhq.common.session.FlashMessage
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.pac4j.core.profile.UserProfile
import org.pac4j.http.profile.HttpProfile
import ratpack.form.Form
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler
import ratpack.pac4j.internal.SessionConstants
import ratpack.session.store.SessionStorage

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory
import java.sql.Timestamp
import java.time.LocalDateTime

@Slf4j
class RegisterEndpoint extends GroovyHandler {

    private final AccountService accountService
    private final ValidatorFactory validatorFactory

    @Inject
    RegisterEndpoint(AccountService accountService, ValidatorFactory validatorFactory) {
        this.accountService = accountService
        this.validatorFactory = validatorFactory
    }

    @Override
    protected void handle(GroovyContext context) {
        context.with {
            byMethod {
                get {
                    render handlebarsTemplate('register.html',
                            title: 'Register',
                            action: '/register',
                            pageId: 'register')
                }

                /**
                 * Handles registration.
                 *
                 * Will validate the user form submission, and if valid save to the database and grant the user a
                 * session prior to redirecting to /yourcellar.
                 */
                post {
                    Form form = parse(Form)

                    Cellar cellar = new Cellar().with { Cellar self ->
                        screenName = form.screenName
                        displayName = screenName
                        contactEmail = form.email
                        lastLogin = Timestamp.valueOf(LocalDateTime.now())
                        return self
                    }
                    EmailAccount emailAccount = new EmailAccount().with { EmailAccount self ->
                        self.cellar = cellar
                        email = form.email
                        password = form.password
                        passwordConfirm = form.passwordConfirm
                        return self
                    }

                    Validator validator = validatorFactory.validator
                    Set<ConstraintViolation<Cellar>> cellarViolations = validator.validate(emailAccount.cellar)
                    Set<ConstraintViolation<EmailAccount>> accountViolations = validator.validate(emailAccount)

                    // A lot easier than creating a custom annotation...
                    boolean passwordsMatch = emailAccount.password == emailAccount.passwordConfirm

                    if (cellarViolations.size() == 0 && accountViolations.size() == 0 && passwordsMatch) {
                        blocking {
                            // TODO: Add uploaded file support for settings page
                            accountService.create(emailAccount, null)
                        }.onError { Throwable e ->
                            if (messageIsForDuplicateCellar(e)) {
                                SessionUtil.setFlash(request, FlashMessage.error(Messages.REGISTER_SCREEN_NAME_TAKEN))
                            } else if (e.message.contains('unq_account_email_email')) {
                                SessionUtil.setFlash(
                                        request,
                                        FlashMessage.error(Messages.REGISTER_EMAIL_ACCOUNT_ALREADY_EXISTS)
                                )
                            } else {
                                log.error(LogUtil.toLog(request, 'RegistrationFailure'), e)
                                SessionUtil.setFlash(request, FlashMessage.error(Messages.UNEXPECTED_SERVER_ERROR))
                            }

                            redirect('/register')
                        } then {
                            request.get(SessionStorage)
                                    .put(AuthenticationModule.SESSION_CELLAR_ID, emailAccount.cellarId)

                            // TODO Is there a way to do this without calling into ratpack's internals?
                            UserProfile userProfile = makeUserProfile(emailAccount)

                            request.add(userProfile)
                            request.add(UserProfile, userProfile)
                            request.get(SessionStorage).put(SessionConstants.USER_PROFILE, userProfile)

                            redirect('/yourcellar')
                        }
                    } else {
                        List<String> messages = new ValidationErrorMapper().
                                buildMessages(cellarViolations, accountViolations)

                        if (!passwordsMatch) {
                            messages << 'passwords do not match'
                        }

                        SessionUtil.setFlash(request, FlashMessage.error(Messages.FORM_VALIDATION_ERROR, messages))

                        redirect('/register')
                    }
                }
            }
        }
    }

    UserProfile makeUserProfile(EmailAccount emailAccount) {
        return new HttpProfile().with { HttpProfile self ->
            addAttribute('display_name', emailAccount.cellar.displayName)
            addAttribute('username', emailAccount.email)
            addAttribute('email', emailAccount.email)
            addRole(Role.MEMBER.toString())
            return self
        }
    }

    private boolean messageIsForDuplicateCellar(Throwable e) {
        e.message.contains('unq_cellar_screen_name') || e.message.contains('cellar_slug_unique_constraint')
    }
}
