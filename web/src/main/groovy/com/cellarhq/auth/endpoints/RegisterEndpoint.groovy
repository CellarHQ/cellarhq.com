package com.cellarhq.auth.endpoints


import com.cellarhq.auth.profiles.CellarHQProfileCreator
import com.cellarhq.auth.services.AccountService
import com.cellarhq.common.Messages
import com.cellarhq.common.session.FlashMessage
import com.cellarhq.common.validation.ValidationErrorMapper
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.EmailAccount
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.pac4j.http.profile.HttpProfile
import ratpack.exec.Blocking
import ratpack.exec.Operation
import ratpack.form.Form
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler
import ratpack.pac4j.internal.Pac4jSessionKeys
import ratpack.session.Session

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory
import java.time.LocalDateTime

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class RegisterEndpoint extends GroovyHandler {

  private final AccountService accountService
  private final ValidatorFactory validatorFactory
  private final CellarHQProfileCreator cellarHQProfileCreator

  @Inject
  RegisterEndpoint(AccountService accountService,
                   ValidatorFactory validatorFactory,
                   CellarHQProfileCreator cellarHQProfileCreator) {
    this.accountService = accountService
    this.validatorFactory = validatorFactory
    this.cellarHQProfileCreator = cellarHQProfileCreator
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
          parse(Form).then { Form form ->

            Cellar cellar = new Cellar().with { Cellar self ->
              screenName = form.screenName
              displayName = screenName
              contactEmail = form.email
              lastLogin = LocalDateTime.now()
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
              Blocking.get {
                // TODO: Add uploaded file support for settings page
                accountService.create(emailAccount, null)
              }.onError { Throwable e ->
                Operation flashOperation

                if (messageIsForDuplicateCellar(e)) {
                  flashOperation = SessionUtil.setFlash(
                    context,
                    FlashMessage.error(Messages.REGISTER_SCREEN_NAME_TAKEN))
                } else if (e.message.contains('unq_account_email_email')) {
                  flashOperation = SessionUtil.setFlash(
                    context,
                    FlashMessage.error(Messages.REGISTER_EMAIL_ACCOUNT_ALREADY_EXISTS)
                  )
                } else {
                  log.error(LogUtil.toLog(request, 'RegistrationFailure'), e)
                  flashOperation = SessionUtil.setFlash(context, FlashMessage.error(Messages.UNEXPECTED_SERVER_ERROR))
                }

                flashOperation.then {
                  redirect('/register')
                }
              }.then {
                context.get(Session).data.then { sessionData ->
                  HttpProfile profile = cellarHQProfileCreator.create(emailAccount.email)
                  sessionData.set(Pac4jSessionKeys.USER_PROFILE, profile)
                  context.redirect('/yourcellar')
                }
              }
            } else {
              List<String> messages = new ValidationErrorMapper().
                buildMessages(cellarViolations, accountViolations)

              if (!passwordsMatch) {
                messages << 'passwords do not match'
              }

              SessionUtil.setFlash(context, FlashMessage.error(Messages.FORM_VALIDATION_ERROR, messages)).then {
                redirect('/register')
              }
            }
          }
        }
      }
    }
  }

  private boolean messageIsForDuplicateCellar(Throwable e) {
    e.message.contains('unq_cellar_screen_name') || e.message.contains('cellar_slug_unique_constraint')
  }
}
