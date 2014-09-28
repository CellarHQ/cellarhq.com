package com.cellarhq.endpoints.api

import static ratpack.jackson.Jackson.fromJson
import static ratpack.jackson.Jackson.json

import com.cellarhq.Messages
import com.cellarhq.domain.CellaredDrink
import com.cellarhq.services.CellaredDrinkService
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.pac4j.core.profile.UserProfile
import ratpack.groovy.handling.GroovyChainAction
import ratpack.handling.Context

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

                    requireSelf(context, pathTokens['cellarSlug']) {
                        cellaredDrinkService.findById(slug, id).single().subscribe { CellaredDrink drink ->
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

                    requireSelf(context, pathTokens['cellarSlug']) {
                        cellaredDrinkService.save(parse(fromJson(CellaredDrink))
                        ).single().flatMap {
                            cellaredDrinkService.findById(slug, it.id).single()
                        } subscribe { CellaredDrink createdDrink ->
                            render json(createdDrink)
                        }
                    }
                }

                delete {
                    String slug = pathTokens['cellarSlug']
                    Long id = Long.valueOf(pathTokens['id'])

                    requireSelf(context, pathTokens['cellarSlug']) {
                        cellaredDrinkService.delete(slug, id).subscribe {
                            response.status(204).send()
                        }
                    }
                }
            }
        }

        put('cellars/:cellarSlug/drinks/:id/drink') {
            String slug = pathTokens['cellarSlug']
            Long id = Long.valueOf(pathTokens['id'])

            requireSelf(context, pathTokens['cellarSlug']) {
                cellaredDrinkService.drink(slug, id).single().subscribe { CellaredDrink drink ->
                    if (drink == null) {
                        clientError 404
                    } else {
                        render json(drink)
                    }
                }
            }
        }
    }

    void requireSelf(Context context, String cellarSlug, Closure operation) {
        context.with {
            boolean isSelf = request.maybeGet(UserProfile)?.username == cellarSlug
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
