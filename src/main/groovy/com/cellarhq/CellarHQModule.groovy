package com.cellarhq

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.BasicAWSCredentials
import com.cellarhq.handlebars.HandlebarsTemplateRendererImpl
import com.cellarhq.handlebars.helpers.DataTableSortingHelper
import com.cellarhq.handlebars.helpers.PaginationHelper
import com.cellarhq.services.*
import com.cellarhq.services.email.AmazonEmailService
import com.cellarhq.services.email.EmailService
import com.cellarhq.services.email.LogEmailService
import com.cellarhq.services.photo.writer.AmazonPhotoWriteStrategy
import com.cellarhq.services.photo.writer.PhotoWriteStrategy
import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Scopes
import com.google.inject.Singleton
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

    private final String awsAccessKey
    private final String awsSecretKey

    CellarHQModule(String awsAccessKey, String awsSecretKey) {
        this.awsAccessKey = awsAccessKey
        this.awsSecretKey = awsSecretKey
    }

    static boolean isProductionEnv() {
        return System.getenv(ENV_DEPLOYMENT) == ENV_DEPLOYMENT_PRODUCTION
    }

    @Override
    protected void configure() {
        bind(AccountService).in(Scopes.SINGLETON)
        bind(CellarService).in(Scopes.SINGLETON)
        bind(CellaredDrinkService).in(Scopes.SINGLETON)
        bind(OrganizationService).in(Scopes.SINGLETON)
        bind(StatsService).in(Scopes.SINGLETON)
        bind(PaginationHelper).in(Scopes.SINGLETON)
        bind(DataTableSortingHelper).in(Scopes.SINGLETON)

        bind(AWSCredentials).toInstance(new BasicAWSCredentials(awsAccessKey, awsSecretKey))

        if (isProductionEnv()) {
            bind(EmailService).to(AmazonEmailService).in(Scopes.SINGLETON)
        } else {
            bind(EmailService).to(LogEmailService).in(Scopes.SINGLETON)
        }

        bind(PhotoWriteStrategy).to(AmazonPhotoWriteStrategy).in(Scopes.SINGLETON)

        bind(ValidatorFactory).toInstance(Validation.buildDefaultValidatorFactory())
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
}
