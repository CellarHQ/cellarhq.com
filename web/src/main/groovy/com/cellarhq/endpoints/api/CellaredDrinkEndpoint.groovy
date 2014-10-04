package com.cellarhq.endpoints.api

import com.cellarhq.Messages
import com.cellarhq.auth.SecurityModule
import com.cellarhq.domain.CellaredDrink
import com.cellarhq.services.CellaredDrinkService
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.groovy.handling.GroovyChainAction
import ratpack.handling.Context
import ratpack.session.store.SessionStorage

import static ratpack.jackson.Jackson.fromJson
import static ratpack.jackson.Jackson.json

@Slf4j
class CellaredDrinkEndpoint extends GroovyChainAction {

    CellaredDrinkService cellaredDrinkService

    @Inject
    CellaredDrinkEndpoint(CellaredDrinkService cellaredDrinkService) {
        this.cellaredDrinkService = cellaredDrinkService
    }

    @Override
    protected void execute() throws Exception {
        get('cellars/:cellarSlug/drinks') {
            cellaredDrinkService.all(pathTokens['cellarSlug']).toList().subscribe { List<CellaredDrink> drinks ->
                render json(drinks)
            }
        }

        handler('cellars/:cellarSlug/drinks/:id') {
            byMethod {
                get {
                    String slug = pathTokens['cellarSlug']
                    Long id = Long.valueOf(pathTokens['id'])


                    cellaredDrinkService.findById(slug, id).single().subscribe { CellaredDrink drink ->
                        requireSelf(context, drink) {
                            if (drink) {
                                render json(drink)
                            } else {
                                clientError 404
                            }
                        }
                    }
                }


                post {
                    String slug = pathTokens['cellarSlug']

                    CellaredDrink cellaredDrink = parse(fromJson(CellaredDrink))
                    requireSelf(context, cellaredDrink) {
                        cellaredDrinkService.save(cellaredDrink)
                            .single().flatMap {
                            cellaredDrinkService.findById(slug, it.id).single()
                        } subscribe { CellaredDrink createdDrink ->
                            render json(createdDrink)
                        }
                    }
                }

                delete {
                    String slug = pathTokens['cellarSlug']
                    Long id = Long.valueOf(pathTokens['id'])

                    cellaredDrinkService.findById(slug, id).single().subscribe { CellaredDrink drink ->
                        requireSelf(context, drink) {
                            cellaredDrinkService.delete(slug, id).subscribe {
                                response.status(204).send()
                            }
                        }
                    }
                }

            }
        }

        put('cellars/:cellarSlug/drinks/:id/drink') {
            String slug = pathTokens['cellarSlug']
            Long id = Long.valueOf(pathTokens['id'])

            cellaredDrinkService.findById(slug, id).single().subscribe  { CellaredDrink drink ->
                requireSelf(context, drink) {
                    cellaredDrinkService.drink(slug, id).single().subscribe { CellaredDrink drankDrink ->
                        if (drankDrink == null) {
                            clientError 404
                        } else {
                            render json(drankDrink)
                        }
                    }
                }
            }
        }
    }

    void requireSelf(Context context, CellaredDrink cellaredDrink, Closure operation) {
        context.with {
            Long cellarId = (Long) request.get(SessionStorage).get(SecurityModule.SESSION_CELLAR_ID)
            boolean isSelf = cellaredDrink.cellarId == cellarId

            if (isSelf) {
                operation()
            } else {
                response.status(403)
                render json([
                    message: Messages.UNAUTHORIZED_ERROR
                ])
                response.send()
            }
        }
    }
}
