package com.cellarhq.api

import com.cellarhq.common.CellarHQConfig
import ratpack.guice.ConfigurableModule

import static com.google.inject.Scopes.SINGLETON


class ApiModule extends ConfigurableModule<CellarHQConfig> {
    @Override
    protected void configure() {
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

    }
}
