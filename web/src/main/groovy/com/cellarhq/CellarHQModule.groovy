package com.cellarhq

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.BasicAWSCredentials
import com.cellarhq.auth.PasswordService
import com.cellarhq.endpoints.*
import com.cellarhq.endpoints.api.*
import com.cellarhq.endpoints.auth.ChangePasswordEndpoint
import com.cellarhq.endpoints.auth.ForgotPasswordEndpoint
import com.cellarhq.endpoints.auth.RegisterEndpoint
import com.cellarhq.endpoints.settings.LinkEmailAccountEndpoint
import com.cellarhq.endpoints.settings.LinkTwitterAccountEndpoint
import com.cellarhq.handlebars.HandlerbarsRenderableDecorator
import com.cellarhq.handlebars.helpers.BottledDateHelper
import com.cellarhq.handlebars.helpers.DataTableSortingHelper
import com.cellarhq.handlebars.helpers.PaginationHelper
import com.cellarhq.handlebars.helpers.SelectedOptionHelper
import com.cellarhq.handlers.CorrelationIdHandler
import com.cellarhq.handlers.RequestLoggingHandler
import com.cellarhq.services.*
import com.cellarhq.services.email.AmazonEmailService
import com.cellarhq.services.email.EmailService
import com.cellarhq.services.email.LogEmailService
import com.cellarhq.services.photo.writer.AmazonPhotoWriteStrategy
import com.cellarhq.services.photo.writer.PhotoWriteStrategy
import com.google.inject.Injector
import com.google.inject.Provides
import groovy.util.logging.Slf4j
import ratpack.guice.ConfigurableModule
import ratpack.guice.HandlerDecoratingModule
import ratpack.handling.Handler

import javax.validation.Validation
import javax.validation.ValidatorFactory

import static com.google.inject.Scopes.SINGLETON

/**
 * The main Guice module for CellarHQ.
 */
@SuppressWarnings('AbcMetric')
@Slf4j
class CellarHQModule extends ConfigurableModule<CellarHQConfig> implements HandlerDecoratingModule {
    @Override
    protected void configure() {
        bind(S3Service).in(SINGLETON)

        // common, to kept in cellarhq module
        bind(PhotoWriteStrategy).to(AmazonPhotoWriteStrategy).in(SINGLETON)
        bind(ValidatorFactory).toInstance(Validation.buildDefaultValidatorFactory())
        bind(PaginationHelper).in(SINGLETON)
        bind(DataTableSortingHelper).in(SINGLETON)
        bind(SelectedOptionHelper).in(SINGLETON)
        bind(BottledDateHelper).in(SINGLETON)
        bind(HandlerbarsRenderableDecorator).in(SINGLETON)


        bind(AccountService).in(SINGLETON)
        bind(CellarService).in(SINGLETON)
        bind(CellaredDrinkService).in(SINGLETON)
        bind(OrganizationService).in(SINGLETON)
        bind(StatsService).in(SINGLETON)
        bind(PasswordService).in(SINGLETON)

        // web app - to be moved to its own module
        bind(BreweryEndpoint).in(SINGLETON)
        bind(BeerEndpoint).in(SINGLETON)
        bind(CellarEndpoint).in(SINGLETON)
        bind(CellarsEndpoint).in(SINGLETON)
        bind(YourCellarEndpoint).in(SINGLETON)
        bind(CellaredDrinkEndpoint).in(SINGLETON)
        bind(RegisterEndpoint).in(SINGLETON)


        // api, to be moved to its own module
        [
            CellarEndpoint,
            CellaredDrinkEndpoint,
            OrganizationEndpoint,
            DrinkEndpoint,
            StyleEndpoint,
            GlasswareEndpoint
        ].each {
            bind(it).in(SINGLETON)
        }

        // user management, to be moved to its own module
        [
            ForgotPasswordEndpoint,
            ChangePasswordEndpoint,
            SettingsEndpoint,
            LinkEmailAccountEndpoint,
            LinkTwitterAccountEndpoint,
            LinkAccountEndpoint
        ].each {
            bind(it).in(SINGLETON)
        }
    }

    @Override
    public Handler decorate(Injector injector, Handler handler) {
        return new CorrelationIdHandler(new RequestLoggingHandler(handler))
    }


    @Provides
    public AWSCredentials provideAWSCredentials(CellarHQConfig config) {
        new BasicAWSCredentials(config.awsAccessKey, config.awsSecretKey)
    }

    @Provides
    public EmailService provideEmailService(AWSCredentials credentials, CellarHQConfig config) {
        if (config.isProductionEnv()) {
            log.info('Binding amazon email service')
            return new AmazonEmailService(credentials)
        }

        log.info('Binding log email service')
        return new LogEmailService()
    }
}
