package com.cellarhq

import com.cellarhq.dao.*
import com.cellarhq.services.*
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
        bind(DrinkDAO).in(Scopes.SINGLETON)
        bind(DrinkService).in(Scopes.SINGLETON)
        bind(OrganizationDAO).in(Scopes.SINGLETON)
        bind(OrganizationService).in(Scopes.SINGLETON)
        bind(StyleDAO).in(Scopes.SINGLETON)
        bind(StyleService).in(Scopes.SINGLETON)
        bind(GlasswareDAO).in(Scopes.SINGLETON)
        bind(GlasswareService).in(Scopes.SINGLETON)
        bind(DrinkCategoryDAO).in(Scopes.SINGLETON)
        bind(DrinkCategoryService).in(Scopes.SINGLETON)
    }
}
