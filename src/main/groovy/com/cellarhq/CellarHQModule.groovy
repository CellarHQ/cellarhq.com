package com.cellarhq

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.BasicAWSCredentials
import com.cellarhq.handlebars.HandlebarsTemplateRendererImpl
import com.cellarhq.handlebars.helpers.PaginationHelper
import com.cellarhq.services.*
import com.cellarhq.services.email.AmazonEmailService
import com.cellarhq.services.email.EmailService
import com.cellarhq.services.email.LogEmailService
import com.google.inject.AbstractModule
import com.google.inject.Scopes
import groovy.transform.CompileStatic
import ratpack.handlebars.internal.HandlebarsTemplateRenderer

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

    static boolean isProductionEnv() {
        return System.getenv(ENV_DEPLOYMENT) == ENV_DEPLOYMENT_PRODUCTION
    }

    @Override
    protected void configure() {
        bind(AccountService).in(Scopes.SINGLETON)
        bind(CellarService).in(Scopes.SINGLETON)
        bind(OrganizationService).in(Scopes.SINGLETON)
        bind(PaginationHelper).in(Scopes.SINGLETON)

        if (isProductionEnv()) {
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
        bind(HandlebarsTemplateRenderer).to(HandlebarsTemplateRendererImpl).in(Scopes.SINGLETON)
    }
}
