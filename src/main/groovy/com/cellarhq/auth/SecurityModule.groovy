package com.cellarhq.auth

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import com.google.inject.TypeLiteral
import groovy.transform.CompileStatic
import org.pac4j.core.client.Client
import org.pac4j.http.client.FormClient
import org.pac4j.http.credentials.UsernamePasswordAuthenticator
import org.pac4j.http.credentials.UsernamePasswordCredentials
import org.pac4j.http.profile.HttpProfile
import ratpack.pac4j.Authorizer

@CompileStatic
class SecurityModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UsernamePasswordAuthenticator).to(CellarHQUsernamePasswordAuthenticator)
        bind(Authorizer).to(AuthPathAuthorizer)
        bind(new TypeLiteral<Client<UsernamePasswordCredentials, HttpProfile>>() {}).to(FormClient)
    }

    @Singleton
    @Provides
    FormClient formClient(UsernamePasswordAuthenticator authenticator) {
        new FormClient('/login', authenticator)
    }
}
