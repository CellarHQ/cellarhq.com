package com.cellarhq.auth

import groovy.transform.CompileStatic
import org.pac4j.core.exception.TechnicalException
import org.pac4j.http.client.FormClient
import org.pac4j.http.credentials.UsernamePasswordAuthenticator

@CompileStatic
class CellarHQFormClient extends FormClient {

    CellarHQFormClient(String loginUrl, UsernamePasswordAuthenticator usernamePasswordAuthenticator) {
        super(loginUrl, usernamePasswordAuthenticator)
        setName('FormClient')
    }

    @Override
    protected String computeErrorMessage(final TechnicalException e) {
        return e.message
    }
}
