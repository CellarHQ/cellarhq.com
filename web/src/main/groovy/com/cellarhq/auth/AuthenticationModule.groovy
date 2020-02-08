package com.cellarhq.auth

import com.cellarhq.api.services.CellarService
import com.cellarhq.auth.endpoints.*
import com.cellarhq.auth.profiles.CellarHQProfileCreator
import com.cellarhq.auth.services.AccountService
import com.cellarhq.common.CellarHQConfig
import com.google.inject.Provides
import com.google.inject.Singleton
import groovy.util.logging.Slf4j
import org.pac4j.http.client.indirect.FormClient
import org.pac4j.http.credentials.authenticator.UsernamePasswordAuthenticator
import ratpack.guice.ConfigurableModule

import static com.google.inject.Scopes.SINGLETON

@Slf4j
class AuthenticationModule extends ConfigurableModule<CellarHQConfig> {
  static String SESSION_CELLAR = "SESSION_CELLAR_KEY"
  static String SESSION_CELLAR_ID = "SESSION_CELLAR_ID_KEY"

  @Override
  protected void configure() {
    bind(UsernamePasswordAuthenticator).to(UsernamePasswordAuthenticatorImpl)
    bind(CellarHQProfileCreator)

    [
      ForgotPasswordEndpoint,
      ChangePasswordHandler,
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
  CustomTwitterClient providesTwitterClient(CellarHQConfig cellarHQConfig,
                                            CellarService cellarService,
                                            AccountService accountService) {
    new CustomTwitterClient(
      accountService,
      cellarService,
      cellarHQConfig.twitterApiKey ?: 'BAD_KEY',
      cellarHQConfig.twitterApiSecret ?: 'BAD_SECRET'
    )
  }

  @Singleton
  @Provides
  FormClient formClient(UsernamePasswordAuthenticator usernamePasswordAuthenticator,
                        CellarHQProfileCreator cellarHQProfileCreator) {
    new FormClient('login', usernamePasswordAuthenticator, cellarHQProfileCreator)
  }
}
