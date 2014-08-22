package com.cellarhq.auth

import com.cellarhq.services.JooqAccountService
import com.google.inject.Inject
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.pac4j.core.exception.CredentialsException
import org.pac4j.http.credentials.UsernamePasswordAuthenticator
import org.pac4j.http.credentials.UsernamePasswordCredentials

@Slf4j
@CompileStatic
class CellarHQUsernamePasswordAuthenticator implements UsernamePasswordAuthenticator {

    private final JooqAccountService accountService

    @Inject
    CellarHQUsernamePasswordAuthenticator(JooqAccountService accountService) {
        this.accountService = accountService
    }

    @Override
    void validate(UsernamePasswordCredentials credentials) {
        if (credentials == null) {
            throwsException('No credentials')
        }

        if (!accountService.findByCredentials(credentials.username, credentials.password)) {
            throwsException('Email and/or password did not match')
        }
    }

    protected void throwsException(final String message) {
        throw new CredentialsException(message)
    }
}
