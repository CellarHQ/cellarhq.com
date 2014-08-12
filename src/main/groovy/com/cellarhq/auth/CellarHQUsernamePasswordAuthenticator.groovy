package com.cellarhq.auth

import com.cellarhq.services.AccountService
import com.google.inject.Inject
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.pac4j.http.credentials.UsernamePasswordAuthenticator
import org.pac4j.http.credentials.UsernamePasswordCredentials

@Slf4j
@CompileStatic
class CellarHQUsernamePasswordAuthenticator implements UsernamePasswordAuthenticator {

    AccountService accountService

    @Inject
    CellarHQUsernamePasswordAuthenticator(AccountService accountService) {
        this.accountService = accountService
    }

    @Override
    void validate(UsernamePasswordCredentials credentials) {
        // GOOD NEWS: We're in a blocking thread here... we just need to inject the AccountService to verify the user
        // actually exists.
        log.info('Hello this is validate')
        accountService.findByCredentials(credentials.username, credentials.password)
    }
}
