package com.cellarhq

import com.cellarhq.dao.CellarDAO
import com.cellarhq.services.CellarService
import com.google.inject.AbstractModule
import com.google.inject.Scopes
import groovy.transform.CompileStatic

/**
 * The main Guice module for CellarHQ.
 */
@CompileStatic
class CellarHQModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CellarDAO).in(Scopes.SINGLETON)
        bind(CellarService).in(Scopes.SINGLETON)
    }
}
