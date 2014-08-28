package com.cellarhq.endpoints.api

import static ratpack.jackson.Jackson.json

import com.cellarhq.domain.Drink
import com.cellarhq.services.DrinkService
import com.google.inject.Inject
import ratpack.groovy.handling.GroovyChainAction


class DrinkEndpoint extends GroovyChainAction {

    private final DrinkService drinkService

    @Inject
    DrinkEndpoint(DrinkService drinkService) {
        this.drinkService = drinkService
    }

    @Override
    protected void execute() throws Exception {
        put('drinks/validate-name') {
            drinkService.search(request.queryParams.name, 1, 0).toList().subscribe { List<Drink> drinks ->
                render json(drinks.empty)
            }
        }
        put('drinks/live-search') {
            drinkService.search(request.queryParams.name, 5, 0).toList().subscribe { List<Drink> drinks ->
                render json(drinks.collect {
                    [
                            id: it.id,
                            name: it.name
                    ]
                })
            }
        }
    }
}
