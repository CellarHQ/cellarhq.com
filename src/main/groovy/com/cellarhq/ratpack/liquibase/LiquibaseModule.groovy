package com.cellarhq.ratpack.liquibase

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import groovy.transform.CompileStatic

/**
 * Enables the application to run migrations, even when it's in Elastic Beanstalk.
 */
@CompileStatic
class LiquibaseModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LiquibaseService).toProvider(LiquibaseProvider).in(Scopes.SINGLETON)
    }
}
