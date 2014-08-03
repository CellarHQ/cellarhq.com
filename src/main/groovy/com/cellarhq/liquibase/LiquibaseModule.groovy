package com.cellarhq.liquibase

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
        throw new UnsupportedOperationException(
                'LiquibaseModule usage is not possible until Ratpack supports startup lifecycles')
//        bind(LiquibaseService).toProvider(LiquibaseProvider).in(Scopes.SINGLETON)
    }
}
