package com.cellarhq.webapp

import com.cellarhq.ForceSslHandler
import com.cellarhq.HomepageHandler
import com.cellarhq.StaticPageChain
import com.cellarhq.UserProfileHandler
import com.cellarhq.common.CellarHQConfig
import com.cellarhq.webapp.handlers.AddBeersHtmlHandler
import com.cellarhq.webapp.handlers.BeersHtmlHandler
import ratpack.guice.ConfigurableModule

import static com.google.inject.Scopes.SINGLETON

class WebappModule extends ConfigurableModule<CellarHQConfig> {
  @Override
  protected void configure() {
    bind(BinStatsService)
    bind(StaticPageChain).in(SINGLETON)
    bind(ForceSslHandler).in(SINGLETON)
    bind(HomepageHandler).in(SINGLETON)
    bind(UserProfileHandler).in(SINGLETON)
    bind(AddBeersHtmlHandler).in(SINGLETON)
    bind(BeersHtmlHandler).in(SINGLETON)
    bind(BreweryChain).in(SINGLETON)
    bind(BeerHtmlChain).in(SINGLETON)
    bind(CellarsChain).in(SINGLETON)
    bind(YourCellarChain).in(SINGLETON)
  }
}
