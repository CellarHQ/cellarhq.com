package com.cellarhq

import com.cellarhq.services.*
import com.google.inject.AbstractModule
import com.google.inject.Scopes
import groovy.transform.CompileStatic

import javax.validation.Validation
import javax.validation.ValidatorFactory

/**
 * The main Guice module for CellarHQ.
 */
@CompileStatic
class CellarHQModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AccountService).in(Scopes.SINGLETON)

        bind(ValidatorFactory).toInstance(Validation.buildDefaultValidatorFactory())
    }
}
