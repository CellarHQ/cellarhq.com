package com.cellarhq.auth.endpoints

import com.cellarhq.auth.AuthenticationModule
import com.cellarhq.auth.services.EmailAccountVerificationService
import com.cellarhq.auth.services.VerificationResult
import com.cellarhq.common.Messages
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.EmailAccount
import com.cellarhq.util.LogUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.form.Form
import ratpack.groovy.handling.GroovyChainAction
import ratpack.session.Session

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory

import static com.cellarhq.common.Messages.FORM_VALIDATION_ERROR
import static com.cellarhq.common.session.FlashMessage.error
import static com.cellarhq.common.session.FlashMessage.success
import static com.cellarhq.util.SessionUtil.setFlash
import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class LinkEmailAccountChain extends GroovyChainAction {

  EmailAccountVerificationService verificationService

  ValidatorFactory validatorFactory

  @Inject
  LinkEmailAccountChain(ValidatorFactory validatorFactory,
                        EmailAccountVerificationService emailAccountVerificationService) {
    this.verificationService = emailAccountVerificationService
    this.validatorFactory = validatorFactory
  }

  @Override
  void execute() throws Exception {
    path('settings/link-email/:token') {
      Cellar cellar = (Cellar) request.get(Session).get(AuthenticationModule.SESSION_CELLAR)

      byMethod {
        get {
          verificationService.verify(cellar, pathTokens['token']).then { Boolean allowed ->
            if (allowed) {
              render handlebarsTemplate('settings/link-email-account-password.html', [
                title : 'Link Email Account',
                pageId: 'link-email-account-password',
                action: "/settings/link-email/${pathTokens['token']}"
              ])
            } else {
              log.warn(LogUtil.toLog(request, 'Failed linking email to existing account', [
                cellar: cellar.id,
                error : Messages.ACCOUNT_LINK_TOKEN_UNKNOWN
              ]))
              setFlash(context, error(Messages.ACCOUNT_LINK_TOKEN_UNKNOWN)).then {
                redirect('/settings/link-email')
              }
            }
          }
        }

        post {
          parse(Form).then { Form form ->

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
                .verifyAndCommit(cellar, pathTokens['token'], form.password).then { VerificationResult result ->
                if (result.success) {
                  log.info(LogUtil.toLog(request, 'Linked email to existing account', [
                    cellar: cellar.id
                  ]))
                  setFlash(context, success(Messages.ACCOUNT_LINK_EMAIL_SUCCESS)).then {
                    redirect('/yourcellar')
                  }
                } else {
                  log.warn(LogUtil.toLog(request, 'Failed linking email to existing account', [
                    cellar: cellar.id,
                    error : result.message
                  ]))
                  setFlash(context, error(result.message)).then {
                    redirect('/settings/link-email')
                  }
                }
              }
            } else {
              List<String> messages = []
              accountViolations.each { messages << "${it.propertyPath.toString()} ${it.message}" }
              if (!passwordsMatch) {
                messages << 'passwords do not match'
              }

              setFlash(context, error(FORM_VALIDATION_ERROR, messages)).then {
                redirect('/settings/link-email/:token')
              }
            }
          }
        }
      }
    }

    path('settings/link-email') {
      Cellar cellar = (Cellar) request.get(Session).get(AuthenticationModule.SESSION_CELLAR)

      byMethod {
        get {
          render handlebarsTemplate('settings/link-email-account.html', [
            title : 'Link Email Account',
            pageId: 'link-email-account'
          ])
        }

        post {
          parse(Form).then { Form form ->

            EmailAccount pendingAccount = new EmailAccount(email: form.email)

            verificationService
              .sendVerification(cellar, pendingAccount).subscribe { VerificationResult result ->
              if (result.success) {
                log.info(LogUtil.toLog(request, 'Attempting email account link', [
                  cellar: cellar.id,
                  email : form.email
                ]))
                setFlash(context, success(
                  Messages.ACCOUNT_LINK_EMAIL_VERIFICATION_SENT
                )).then {
                  redirect('/yourcellar')
                }
              } else {
                log.warn(LogUtil.toLog(request, 'Failed attempting to link email account', [
                  error : result.message,
                  cellar: cellar.id,
                  email : form.email
                ]))
                setFlash(context, error(result.message)).then {
                  redirect('/settings/link-email')
                }
              }
            }
          }
        }
      }
    }
  }
}

