package com.cellarhq.liquibase

import com.google.inject.AbstractModule
import com.google.inject.Scopes

/**
 * Enables the application to run migrations, even when it's in Elastic Beanstalk.
 */
class LiquibaseModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LiquibaseService).toProvider(LiquibaseProvider).in(Scopes.SINGLETON)
    }
}
