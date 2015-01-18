package com.cellarhq.auth

import com.cellarhq.auth.callbacks.AuthFailCallback
import com.cellarhq.auth.callbacks.AuthSuccessCallback
import com.cellarhq.auth.callbacks.HttpCallback
import com.cellarhq.auth.callbacks.TwitterCallback
import com.cellarhq.auth.endpoints.ChangePasswordEndpoint
import com.cellarhq.auth.endpoints.ForgotPasswordEndpoint
import com.cellarhq.auth.endpoints.LinkAccountEndpoint
import com.cellarhq.auth.endpoints.LinkEmailAccountEndpoint
import com.cellarhq.auth.endpoints.LinkTwitterAccountEndpoint
import com.cellarhq.auth.endpoints.RegisterEndpoint
import com.cellarhq.auth.endpoints.SettingsEndpoint
import com.cellarhq.auth.rememberme.RememberMeHandler
import com.cellarhq.auth.rememberme.RememberMeService
import com.cellarhq.common.CellarHQConfig
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
import ratpack.guice.ConfigurableModule
import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.handling.HandlerDecorator
import ratpack.handling.Handlers
import ratpack.pac4j.Authorizer
import ratpack.pac4j.Pac4jCallbackHandlerBuilder
import ratpack.pac4j.internal.Pac4jClientsHandler
import ratpack.registry.Registry

import static com.google.inject.Scopes.SINGLETON

@Slf4j
class AuthenticationModule extends ConfigurableModule<CellarHQConfig> {

    static final String SESSION_CELLAR_ID = 'cellarId'
    static final String SESSION_CELLAR = 'cellar'

    private static final String DEFAULT_CALLBACK_PATH = 'pac4j-callback'

    @Override
    protected void configure() {
        bind(UsernamePasswordAuthenticator).to(UsernamePasswordAuthenticatorImpl)
        bind(Authorizer).to(AuthPathAuthorizer)
        bind(new TypeLiteral<Client<UsernamePasswordCredentials, HttpProfile>>() {}).to(FormClient)
        bind(new TypeLiteral<Client<OAuthCredentials, TwitterProfile>>() {}).to(TwitterClient)
        bind(HttpCallback)
        bind(TwitterCallback)
        bind(RememberMeService)

        [
            ForgotPasswordEndpoint,
            ChangePasswordEndpoint,
            SettingsEndpoint,
            LinkEmailAccountEndpoint,
            LinkTwitterAccountEndpoint,
            LinkAccountEndpoint,
            RegisterEndpoint
        ].each {
            bind(it).in(SINGLETON)
        }
    }

    @Singleton
    @Provides
    TwitterClient providesTwitterClient(CellarHQConfig cellarHQConfig) {
        new TwitterClient(
                cellarHQConfig.twitterApiKey ?: 'BAD_KEY',
                cellarHQConfig.twitterApiSecret ?: 'BAD_SECRET'
        )
    }

    @Singleton
    @Provides
    FormClient formClient(UsernamePasswordAuthenticator authenticator) {
        new CellarHQFormClient('/login', authenticator)
    }

    @Provides
    protected Pac4JHandlerDecorator pac4JHandlerDecorator(CellarHQConfig config, Injector injector) {
        return new Pac4JHandlerDecorator(config)
    }

    private static class Pac4JHandlerDecorator implements HandlerDecorator {

        private final CellarHQConfig config


        public Pac4JHandlerDecorator(CellarHQConfig config) {
            this.config = config
        }

        @SuppressWarnings(['VariableName'])
        @Override
        public Handler decorate(Registry serverRegistry, Handler rest) {
            final String callbackPath = DEFAULT_CALLBACK_PATH
            final Authorizer authorizer = serverRegistry.get(Authorizer)
            final TwitterClient twitterClient = serverRegistry.get(TwitterClient)
            final FormClient formClient = serverRegistry.get(FormClient)
            final Pac4jClientsHandler clientsHandler = new Pac4jClientsHandler(callbackPath, twitterClient, formClient)
            final RememberMeService rememberMeService = serverRegistry.get(RememberMeService)
            final Handler callbackHandler = new Pac4jCallbackHandlerBuilder()
                    .onSuccess(new AuthSuccessCallback<Context, UserProfile>())
                    .onError(new AuthFailCallback<Context,
Throwable>(rememberMeService))
                    .build()

            final CellarHQAuthenticationHandler authenticationHandler = new CellarHQAuthenticationHandler(authorizer)
            final RememberMeHandler rememberMeHandler = new RememberMeHandler(rememberMeService)

            return Handlers.chain(clientsHandler,
                    Handlers.path(callbackPath, callbackHandler),
                    rememberMeHandler,
                    authenticationHandler,
                    rest)
        }
    }
}
