package com.cellarhq.webapp

import com.cellarhq.common.CellarHQConfig
import com.cellarhq.webapp.handlers.AddBeersHtmlHandler
import com.cellarhq.webapp.handlers.BeersHtmlHandler
import ratpack.guice.ConfigurableModule

import static com.google.inject.Scopes.SINGLETON

class WebappModule extends ConfigurableModule<CellarHQConfig> {
  @Override
  protected void configure() {
    bind(BinStatsService)
    bind(AddBeersHtmlHandler).in(SINGLETON)
    bind(BeersHtmlHandler).in(SINGLETON)
    bind(BreweryChain).in(SINGLETON)
    bind(BeerHtmlChain).in(SINGLETON)
    bind(CellarsEndpoint).in(SINGLETON)
    bind(YourCellarEndpoint).in(SINGLETON)
  }
}
