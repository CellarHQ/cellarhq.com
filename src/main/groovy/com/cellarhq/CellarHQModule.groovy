package com.cellarhq

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.BasicAWSCredentials
import com.cellarhq.dao.*
import com.cellarhq.services.*
import com.cellarhq.services.email.AmazonEmailService
import com.cellarhq.services.email.EmailService
import com.cellarhq.services.email.LogEmailService
import com.google.inject.AbstractModule
import com.google.inject.Scopes
import groovy.transform.CompileStatic

import javax.validation.Validation
import javax.validation.ValidatorFactory

/**
 * The main Guice module for CellarHQ.
 */
@SuppressWarnings('AbcMetric')
@CompileStatic
class CellarHQModule extends AbstractModule {

    final static String ENV_DEPLOYMENT = 'deploymentEnv'
    final static String ENV_DEPLOYMENT_PRODUCTION = 'production'

    @Override
    protected void configure() {
        bind(EmailAccountDAO).in(Scopes.SINGLETON)
        bind(OAuthAccountDAO).in(Scopes.SINGLETON)
        bind(AccountService).in(Scopes.SINGLETON)
        bind(CellarDAO).in(Scopes.SINGLETON)
        bind(CellarService).in(Scopes.SINGLETON)
        bind(DrinkDAO).in(Scopes.SINGLETON)
        bind(DrinkService).in(Scopes.SINGLETON)
        bind(OrganizationDAO).in(Scopes.SINGLETON)
        bind(OrganizationService).in(Scopes.SINGLETON)
        bind(StyleDAO).in(Scopes.SINGLETON)
        bind(StyleService).in(Scopes.SINGLETON)
        bind(GlasswareDAO).in(Scopes.SINGLETON)
        bind(GlasswareService).in(Scopes.SINGLETON)
        bind(DrinkCategoryDAO).in(Scopes.SINGLETON)
        bind(DrinkCategoryService).in(Scopes.SINGLETON)
        bind(PasswordChangeRequestDAO).in(Scopes.SINGLETON)

        bind(JooqCellarService).in(Scopes.SINGLETON)

        if (System.getenv(ENV_DEPLOYMENT) == ENV_DEPLOYMENT_PRODUCTION) {
            // TODO: This should get put into the ratpack configuration file...
            bind(AWSCredentials).toInstance(new BasicAWSCredentials(
                    'AKIAIXBP2ORLESIX5CIQ',
                    'DHinN9Eg3uz/Nbo3hQIvVXxK9hImzxdE04I3dHz3'
            ))
            bind(EmailService).to(AmazonEmailService).in(Scopes.SINGLETON)
        } else {
            bind(EmailService).to(LogEmailService).in(Scopes.SINGLETON)
        }

        bind(ValidatorFactory).toInstance(Validation.buildDefaultValidatorFactory())
    }
}
