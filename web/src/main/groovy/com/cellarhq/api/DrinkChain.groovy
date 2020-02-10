package com.cellarhq.api

import com.cellarhq.api.services.DrinkService
import com.cellarhq.domain.Drink
import com.google.inject.Inject
import groovy.transform.CompileStatic
import ratpack.exec.Promise
import ratpack.groovy.handling.GroovyChainAction

import static ratpack.jackson.Jackson.json

@CompileStatic
class DrinkChain extends GroovyChainAction {

  private final DrinkService drinkService

  @Inject
  DrinkChain(DrinkService drinkService) {
    this.drinkService = drinkService
  }

  @Override
  void execute() throws Exception {
    put('drinks/validate-name') {
      drinkService.searchByOrganizationId(
        request.queryParams.organizationId?.toLong(),
        request.queryParams.name,
        1,
        0
      ).then { List<Drink> drinks ->
        if (request.queryParams.exists) {
          render json(!drinks.empty)
        } else {
          render json(drinks.empty)
        }
      }
    }

    get('drinks/live-search') {
      Promise<List<Drink>> drinkPromise
      if (request.queryParams.organizationId) {
        drinkPromise = drinkService.searchByOrganizationId(
          Long.valueOf(request.queryParams.organizationId),
          request.queryParams.name, 20, 0)
      } else {
        drinkPromise = drinkService.search(request.queryParams.name, null, 20, 0)
      }
      drinkPromise.then { List<Drink> drinks ->
        render json(drinks.collect {
          [
            id  : it.id,
            name: it.name
          ]
        })
      }
    }
  }
}
