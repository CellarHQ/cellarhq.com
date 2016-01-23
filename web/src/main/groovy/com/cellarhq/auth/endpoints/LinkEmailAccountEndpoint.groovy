package com.cellarhq.auth.endpoints

import com.cellarhq.auth.AuthenticationModule
import com.cellarhq.auth.services.EmailAccountVerificationService
import com.cellarhq.auth.services.VerificationResult
import com.cellarhq.common.Messages
import com.cellarhq.common.session.FlashMessage
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.EmailAccount
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.form.Form
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain
import ratpack.session.Session

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory

import static ratpack.handlebars.Template.handlebarsTemplate

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
      path('settings/link-email/:token') {
        Cellar cellar = (Cellar) request.get(Session).get(AuthenticationModule.SESSION_CELLAR)

        byMethod {
          get {
            verificationService.verify(cellar, pathTokens['token']).subscribe { Boolean allowed ->
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
                SessionUtil.setFlash(context, FlashMessage.error(Messages.ACCOUNT_LINK_TOKEN_UNKNOWN)).then {
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
                  .verifyAndCommit(cellar, pathTokens['token'], form.password).subscribe { VerificationResult result ->
                  if (result.success) {
                    log.info(LogUtil.toLog(request, 'Linked email to existing account', [
                      cellar: cellar.id
                    ]))
                    SessionUtil.setFlash(context, FlashMessage.success(Messages.ACCOUNT_LINK_EMAIL_SUCCESS)).then {
                      redirect('/yourcellar')
                    }
                  } else {
                    log.warn(LogUtil.toLog(request, 'Failed linking email to existing account', [
                      cellar: cellar.id,
                      error : result.message
                    ]))
                    SessionUtil.setFlash(context, FlashMessage.error(result.message)).then {
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

                SessionUtil.setFlash(
                  request,
                  FlashMessage.error(Messages.FORM_VALIDATION_ERROR, messages)).then {
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
                  SessionUtil.setFlash(context, FlashMessage.success(
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
                  SessionUtil.setFlash(context, FlashMessage.error(result.message)).then {
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
}
