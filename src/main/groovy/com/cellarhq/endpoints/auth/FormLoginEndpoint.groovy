package com.cellarhq.endpoints.auth

import static com.cellarhq.ratpack.hibernate.HibernateDSL.transaction

import com.cellarhq.Messages
import com.cellarhq.domain.EmailAccount
import com.cellarhq.services.AccountService
import com.cellarhq.services.CellarService
import com.cellarhq.util.LogUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.pac4j.core.exception.TechnicalException
import org.pac4j.http.profile.HttpProfile
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler
import ratpack.pac4j.internal.SessionConstants
import ratpack.session.store.SessionStorage

@Slf4j
class FormLoginEndpoint extends GroovyHandler {

    private final AccountService accountService
    private final CellarService cellarService

    @Inject
    FormLoginEndpoint(AccountService accountService, CellarService cellarService) {
        this.accountService = accountService
        this.cellarService = cellarService
    }

    @Override
    protected void handle(GroovyContext context) {
        context.with {
            byMethod {
                get {
                    HttpProfile httpProfile = request.get(HttpProfile)
                    transaction(context) {
                        EmailAccount account = accountService.findByEmail(httpProfile.username)
                        if (!account) {
                            throw new TechnicalException(
                                    "Session User found '${httpProfile.username}' without matching account")
                        }
                        cellarService.updateLoginStats(account.cellar)
                        account
                    } onError { Throwable e ->
                        log.error(LogUtil.toLog('LoginFormEndpoint', [
                                exception: e.toString()
                        ]))
                        redirect(500, "/logout?error=${Messages.UNEXPECTED_SERVER_ERROR}")
                    } then { EmailAccount emailAccount ->
                        httpProfile.id = emailAccount.cellar.id
                        request.get(SessionStorage).put(SessionConstants.USER_PROFILE, httpProfile)

                        redirect('/yourcellar')
                    }
                }
            }
        }
    }
}
