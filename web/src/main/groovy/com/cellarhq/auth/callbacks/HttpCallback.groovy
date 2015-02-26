package com.cellarhq.auth.callbacks

import com.cellarhq.common.Messages
import com.cellarhq.auth.AuthenticationModule
import com.cellarhq.domain.EmailAccount
import com.cellarhq.auth.services.AccountService
import com.cellarhq.api.services.CellarService
import com.cellarhq.common.session.FlashMessage
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.pac4j.core.exception.TechnicalException
import org.pac4j.http.profile.HttpProfile
import ratpack.handling.Context
import ratpack.http.Request
import ratpack.session.store.SessionStorage

import java.util.function.BiConsumer

@Slf4j
@CompileStatic
class HttpCallback<C extends Context, P extends HttpProfile> implements BiConsumer<C, P> {

    private final AccountService accountService
    private final CellarService cellarService

    @Inject
    HttpCallback(AccountService accountService, CellarService cellarService) {
        this.accountService = accountService
        this.cellarService = cellarService
    }

    @Override
    void accept(C context, P profile) {
        Request request = context.request

        log.info(LogUtil.toLog(request, 'Form Login Attempt', [
                httpProfile: profile.username
        ]))

        context.blocking {
            EmailAccount account = accountService.findByEmailWithCellar(profile.username)
            if (!account) {
                throw new TechnicalException(
                        "Session User found '${profile.username}' without matching account")
            }
            cellarService.updateLoginStats(account.cellar)

            return account
        } onError { Throwable e ->
            log.error(LogUtil.toLog(request, 'LoginFormEndpoint', [
                    exception: e.toString()
            ]), e)
            SessionUtil.setFlash(request, FlashMessage.error(Messages.UNEXPECTED_SERVER_ERROR))
            context.redirect('/logout')
        } then { EmailAccount emailAccount ->
            request.get(SessionStorage).put(AuthenticationModule.SESSION_CELLAR_ID, emailAccount.cellarId)
            new DefaultSuccessCallback().defaultBehavior(context, profile, emailAccount.cellar)
        }
    }
}
