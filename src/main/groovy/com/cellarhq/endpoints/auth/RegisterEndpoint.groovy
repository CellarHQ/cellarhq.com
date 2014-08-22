package com.cellarhq.endpoints.auth

import static ratpack.handlebars.Template.handlebarsTemplate

import com.cellarhq.Messages
import com.cellarhq.auth.Role
import com.cellarhq.domain.jooq.Cellar
import com.cellarhq.domain.jooq.EmailAccount
import com.cellarhq.services.JooqAccountService
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.pac4j.core.profile.CommonProfile
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

    private final JooqAccountService accountService
    private final ValidatorFactory validatorFactory

    @Inject
    RegisterEndpoint(JooqAccountService accountService, ValidatorFactory validatorFactory) {
        this.accountService = accountService
        this.validatorFactory = validatorFactory
    }

    @SuppressWarnings('AbcMetric')
    @Override
    protected void handle(GroovyContext context) {
        context.with {
            byMethod {
                get {
                    render handlebarsTemplate('register.html',
                            title: 'Register',
                            action: '/register',
                            method: 'post',
                            error: request.queryParams.error ?: '',
                            errorMessages: SessionUtil.getFlashMessages(request).collect { [message: it] },
                            pageId: 'register',
                            loggedIn: SessionUtil.isLoggedIn(request.maybeGet(CommonProfile)))
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
                        addRole(Role.MEMBER)
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
                        // Rx would actually work pretty well here...
                        blocking {
                            accountService.create(emailAccount)
                        }.onError { Throwable e ->
                            log.error(LogUtil.toLog('RegistrationFailure'), e)

                            // TODO... if we get a conflict on username, it isn't unexpected.
                            redirect(500, '/register?error=' + Messages.UNEXPECTED_SERVER_ERROR)
                        } then {
                            // TODO Is there a way to do this without calling into ratpack's internals?
                            UserProfile userProfile = makeUserProfile(emailAccount)

                            request.register(userProfile)
                            request.register(UserProfile, userProfile)
                            request.get(SessionStorage).put(SessionConstants.USER_PROFILE, userProfile)

                            redirect('/yourcellar')
                        }
                    } else {
                        // TODO Create a little helper class to do this instead of using a static method. Should be able
                        //      to take in constraint violations and convert them properly.
                        List<String> messages = []
                        cellarViolations.each { messages << "${it.propertyPath.toString()} ${it.message}" }
                        accountViolations.each { messages << "${it.propertyPath.toString()} ${it.message}" }
                        if (!passwordsMatch) {
                            messages << 'passwords do not match'
                        }

                        SessionUtil.setFlashMessages(request, messages)

                        redirect('/register?error=' + Messages.FORM_VALIDATION_ERROR)
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
}
