package com.cellarhq.webapp

import com.cellarhq.common.CellarHQConfig
import ratpack.guice.ConfigurableModule

import static com.google.inject.Scopes.SINGLETON


class WebappModule extends ConfigurableModule<CellarHQConfig> {
    @Override
    protected void configure() {
        // web app - to be moved to its own module
        bind(BreweryEndpoint).in(SINGLETON)
        bind(BeerEndpoint).in(SINGLETON)
        bind(CellarsEndpoint).in(SINGLETON)
        bind(YourCellarEndpoint).in(SINGLETON)
    }
}
