package com.cellarhq.common

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.BasicAWSCredentials
import com.cellarhq.api.services.CellarService
import com.cellarhq.api.services.CellaredDrinkService
import com.cellarhq.api.services.OrganizationService
import com.cellarhq.auth.PasswordService
import com.cellarhq.auth.services.AccountService
import com.cellarhq.auth.services.photo.writer.AmazonPhotoWriteStrategy
import com.cellarhq.auth.services.photo.writer.PhotoWriteStrategy
import com.cellarhq.common.handlebars.HandlerbarsRenderableDecorator
import com.cellarhq.common.handlebars.helpers.BottledDateHelper
import com.cellarhq.common.handlebars.helpers.DataTableSortingHelper
import com.cellarhq.common.handlebars.helpers.PaginationHelper
import com.cellarhq.common.handlebars.helpers.SelectedOptionHelper
import com.cellarhq.common.services.S3Service
import com.cellarhq.common.services.email.AmazonEmailService
import com.cellarhq.common.services.email.EmailService
import com.cellarhq.common.services.email.LogEmailService
import com.cellarhq.webapp.StatsService
import com.google.inject.Injector
import com.google.inject.Provides
import com.google.inject.Singleton
import groovy.util.logging.Slf4j
import ratpack.guice.ConfigurableModule
import ratpack.handling.Handler
import ratpack.handling.HandlerDecorator
import ratpack.handling.Handlers
import ratpack.handling.RequestId
import ratpack.registry.Registry

import javax.validation.Validation
import javax.validation.ValidatorFactory

import static com.google.inject.Scopes.SINGLETON

/**
 * The main Guice module for CellarHQ.
 */
@SuppressWarnings('AbcMetric')
@Slf4j
class CommonModule extends ConfigurableModule<CellarHQConfig> {
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
    }

    @Singleton
    @Provides
    public AWSCredentials provideAWSCredentials(CellarHQConfig config) {
        new BasicAWSCredentials(config.awsAccessKey, config.awsSecretKey)
    }

    @Singleton
    @Provides
    public EmailService provideEmailService(AWSCredentials credentials, CellarHQConfig config) {
        if (config.isProductionEnv()) {
            log.info('Binding amazon email service')
            return new AmazonEmailService(credentials)
        }

        log.info('Binding log email service')
        return new LogEmailService()
    }

    @Singleton
    @Provides
    protected CommonHandlerDecorator commonHandlerDecorator(CellarHQConfig config, Injector injector) {
        return new CommonHandlerDecorator(config)
    }

    private static class CommonHandlerDecorator implements HandlerDecorator {

        private final CellarHQConfig config


        public CommonHandlerDecorator(CellarHQConfig config) {
            this.config = config
        }

        @Override
        public Handler decorate(Registry serverRegistry, Handler rest) {
            return Handlers.chain(RequestId.bindAndLog(), rest)
        }
    }
}
