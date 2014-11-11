package com.cellarhq

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.BasicAWSCredentials
import com.cellarhq.auth.PasswordService
import com.cellarhq.handlebars.HandlebarsTemplateRendererImpl
import com.cellarhq.handlebars.helpers.DataTableSortingHelper
import com.cellarhq.handlebars.helpers.PaginationHelper
import com.cellarhq.handlebars.helpers.SelectedOptionHelper
import com.cellarhq.handlebars.helpers.BottledDateHelper
import com.cellarhq.handler.RequestLoggingHandler
import com.cellarhq.services.*
import com.cellarhq.services.email.AmazonEmailService
import com.cellarhq.services.email.EmailService
import com.cellarhq.services.email.LogEmailService
import com.cellarhq.services.photo.writer.AmazonPhotoWriteStrategy
import com.cellarhq.services.photo.writer.PhotoWriteStrategy
import com.google.inject.*
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import ratpack.guice.HandlerDecoratingModule
import ratpack.handlebars.internal.HandlebarsTemplateRenderer
import ratpack.handling.Handler

import javax.validation.Validation
import javax.validation.ValidatorFactory

/**
 * The main Guice module for CellarHQ.
 */
@SuppressWarnings('AbcMetric')
@CompileStatic
@Slf4j
class CellarHQModule extends AbstractModule implements HandlerDecoratingModule {

    final static String ENV_DEPLOYMENT = 'CHQ_DEPLOYMENT'
    final static String ENV_HOSTNAME = 'CHQ_HOSTNAME'
    final static String ENV_GA_TRACKING_CODE = 'CHQ_GA_ID'

    final static String ENV_DEPLOYMENT_PRODUCTION = 'production'
    final static String ENV_HOSTNAME_PRODUCTION = 'www.cellarhq.com'

    private final String awsAccessKey
    private final String awsSecretKey

    CellarHQModule(String awsAccessKey, String awsSecretKey) {
        this.awsAccessKey = awsAccessKey
        this.awsSecretKey = awsSecretKey
    }

    static boolean isProductionEnv() {
        return System.getenv(ENV_DEPLOYMENT) == ENV_DEPLOYMENT_PRODUCTION
    }

    static String getHostname() {
        String hostname = System.getenv(ENV_HOSTNAME)
        return hostname ?: ENV_HOSTNAME_PRODUCTION
    }

    @Override
    protected void configure() {
        bind(AccountService).in(Scopes.SINGLETON)
        bind(CellarService).in(Scopes.SINGLETON)
        bind(CellaredDrinkService).in(Scopes.SINGLETON)
        bind(OrganizationService).in(Scopes.SINGLETON)
        bind(StatsService).in(Scopes.SINGLETON)
        bind(PasswordService).in(Scopes.SINGLETON)

        bind(AWSCredentials).toInstance(new BasicAWSCredentials(awsAccessKey, awsSecretKey))

        if (isProductionEnv()) {
            log.info('Binding amazon email service')
            bind(EmailService).to(AmazonEmailService).in(Scopes.SINGLETON)
        } else {
            log.info('Binding log email service')
            bind(EmailService).to(LogEmailService).in(Scopes.SINGLETON)
        }

        bind(PhotoWriteStrategy).to(AmazonPhotoWriteStrategy).in(Scopes.SINGLETON)

        bind(ValidatorFactory).toInstance(Validation.buildDefaultValidatorFactory())

        bind(PaginationHelper).in(Scopes.SINGLETON)
        bind(DataTableSortingHelper).in(Scopes.SINGLETON)
        bind(SelectedOptionHelper).in(Scopes.SINGLETON)
        bind(BottledDateHelper).in(Scopes.SINGLETON)
        bind(HandlebarsTemplateRenderer).to(HandlebarsTemplateRendererImpl).in(Scopes.SINGLETON)
    }

    @Singleton
    @Provides
    S3Service s3Service(AWSCredentials credentials) {
        if (isProductionEnv()) {
            return new S3Service(credentials, S3Service.BUCKET_PRODUCTION)
        }
        return new S3Service(credentials, S3Service.BUCKET_DEVELOPMENT)
    }

    @Override
    public Handler decorate(Injector injector, Handler handler) {
        return new RequestLoggingHandler(handler)
    }
}
