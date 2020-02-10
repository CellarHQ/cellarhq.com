package com.cellarhq.api

import com.cellarhq.common.CellarHQConfig
import ratpack.guice.ConfigurableModule

import static com.google.inject.Scopes.SINGLETON


class ApiModule extends ConfigurableModule<CellarHQConfig> {
  @Override
  protected void configure() {
    [
      CellarChain,
      CellaredDrinkChain,
      OrganizationChain,
      DrinkChain,
      StyleChain,
      GlasswareChain
    ].each {
      bind(it).in(SINGLETON)
    }

  }
}
