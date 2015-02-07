package com.cellarhq.auth

import com.cellarhq.CellarHQConfig
import com.cellarhq.auth.callbacks.AuthFailCallback
import com.cellarhq.auth.callbacks.AuthSuccessCallback
import com.cellarhq.auth.callbacks.HttpCallback
import com.cellarhq.auth.callbacks.TwitterCallback
import com.google.inject.AbstractModule
import com.google.inject.Injector
import com.google.inject.Provides
import com.google.inject.Singleton
import com.google.inject.TypeLiteral
import groovy.util.logging.Slf4j
import org.pac4j.core.client.Client
import org.pac4j.core.profile.UserProfile
import org.pac4j.http.client.FormClient
import org.pac4j.http.credentials.UsernamePasswordAuthenticator
import org.pac4j.http.credentials.UsernamePasswordCredentials
import org.pac4j.http.profile.HttpProfile
import org.pac4j.oauth.client.TwitterClient
import org.pac4j.oauth.credentials.OAuthCredentials
import org.pac4j.oauth.profile.twitter.TwitterProfile
import ratpack.guice.HandlerDecoratingModule
import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.handling.Handlers
import ratpack.pac4j.Authorizer
import ratpack.pac4j.Pac4jCallbackHandlerBuilder
import ratpack.pac4j.internal.Pac4jClientsHandler

@Slf4j
class SecurityModule extends AbstractModule implements HandlerDecoratingModule {

    static final String SESSION_CELLAR_ID = 'cellarId'
    static final String SESSION_CELLAR = 'cellar'

    private static final String DEFAULT_CALLBACK_PATH = 'pac4j-callback'

    final String twitterApiKey
    final String twitterApiSecret

    SecurityModule(CellarHQConfig cellarHQConfig) {
        twitterApiKey = cellarHQConfig.twitterApiKey ?: 'BAD_KEY'
        twitterApiSecret = cellarHQConfig.twitterApiSecret ?: 'BAD_SECRET'
    }

    @Override
    protected void configure() {
        bind(TwitterClient).toInstance(new TwitterClient(
                twitterApiKey,
                twitterApiSecret
        ))
        bind(UsernamePasswordAuthenticator).to(UsernamePasswordAuthenticatorImpl)
        bind(Authorizer).to(AuthPathAuthorizer)
        bind(new TypeLiteral<Client<UsernamePasswordCredentials, HttpProfile>>() {}).to(FormClient)
        bind(new TypeLiteral<Client<OAuthCredentials, TwitterProfile>>() {}).to(TwitterClient)
        bind(HttpCallback)
        bind(TwitterCallback)
    }

    @Singleton
    @Provides
    FormClient formClient(UsernamePasswordAuthenticator authenticator) {
        new CellarHQFormClient('/login', authenticator)
    }

    @SuppressWarnings('VariableName')
    @Override
    Handler decorate(Injector injector, Handler handler) {
        final String callbackPath = DEFAULT_CALLBACK_PATH
        final Authorizer authorizer = injector.getInstance(Authorizer)
        final TwitterClient twitterClient = injector.getInstance(TwitterClient)
        final FormClient formClient = injector.getInstance(FormClient)
        final Pac4jClientsHandler clientsHandler = new Pac4jClientsHandler(callbackPath, twitterClient, formClient)
        final Handler callbackHandler = new Pac4jCallbackHandlerBuilder()
                .onSuccess(new AuthSuccessCallback<Context, UserProfile>())
                .onError(new AuthFailCallback<Context, Throwable>())
                .build()

        final CellarHQAuthenticationHandler authenticationHandler = new CellarHQAuthenticationHandler(authorizer)

        return Handlers.chain(clientsHandler,
                              Handlers.path(callbackPath, callbackHandler),
                              authenticationHandler,
                              handler)
    }
}
