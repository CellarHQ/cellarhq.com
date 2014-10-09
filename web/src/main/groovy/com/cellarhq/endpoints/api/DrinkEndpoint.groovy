package com.cellarhq.endpoints.api

import com.cellarhq.domain.Drink
import com.cellarhq.services.DrinkService
import com.google.inject.Inject
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain

import static ratpack.jackson.Jackson.json

class DrinkEndpoint implements Action<Chain> {

    private final DrinkService drinkService

    @Inject
    DrinkEndpoint(DrinkService drinkService) {
        this.drinkService = drinkService
    }

    @Override
    void execute(Chain chain) throws Exception {
        Groovy.chain(chain) {
            handler {
                put('drinks/validate-name') {
                    drinkService.search(request.queryParams.name, null, 1, 0).toList().subscribe { List<Drink> drinks ->
                        render json(drinks.empty)
                    }
                }
                get('drinks/live-search') {
                    rx.Observable observable
                    if (request.queryParams.organizationId) {
                        observable = drinkService.searchByOrganizationId(
                                Long.valueOf(request.queryParams.organizationId),
                                request.queryParams.name, 5, 0)
                    } else {
                        observable = drinkService.search(request.queryParams.name, null, 5, 0)
                    }
                    observable.toList().subscribe { List<Drink> drinks ->
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
    }
}
