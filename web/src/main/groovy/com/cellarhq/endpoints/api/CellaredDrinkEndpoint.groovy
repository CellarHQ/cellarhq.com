package com.cellarhq.endpoints.api

import static ratpack.jackson.Jackson.fromJson
import static ratpack.jackson.Jackson.json

import com.cellarhq.domain.CellaredDrink
import com.cellarhq.services.CellaredDrinkService
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler

/**
 * @todo Add validation
 */
@Slf4j
class CellaredDrinkEndpoint extends GroovyHandler {

    CellaredDrinkService cellaredDrinkService

    @Inject
    CellaredDrinkEndpoint(CellaredDrinkService cellaredDrinkService) {
        this.cellaredDrinkService = cellaredDrinkService
    }

    @Override
    protected void handle(GroovyContext context) {
        context.with {
            String slug = pathTokens['cellarSlug']
            Long id = pathTokens['id']

            byMethod {
                get {
                    cellaredDrinkService.findById(slug, id).single().subscribe { CellaredDrink drink ->
                        if (drink == null) {
                            clientError 404
                        } else {
                            render json(drink)
                        }
                    }
                }

                post {
                    // TODO: Confirm cellar exists
                    cellaredDrinkService.save(parse(fromJson(CellaredDrink))
                    ).single().flatMap {
                        cellaredDrinkService.findById(slug, it.id).single()
                    } subscribe { CellaredDrink createdDrink ->
                        render json(createdDrink)
                    }
                }

                delete {
                    cellaredDrinkService.delete(slug, id).subscribe {
                        response.send()
                    }
                }
            }
        }
    }
}
