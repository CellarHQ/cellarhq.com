package com.cellarhq.api

import com.cellarhq.api.services.CellaredDrinkService
import com.cellarhq.auth.profiles.CellarHQProfile
import com.cellarhq.common.Messages
import com.cellarhq.domain.CellaredDrink
import com.cellarhq.jooq.SortCommand
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.groovy.handling.GroovyChainAction
import ratpack.handling.Context

import static ratpack.jackson.Jackson.fromJson
import static ratpack.jackson.Jackson.json

@Slf4j
class CellaredDrinkChain extends GroovyChainAction {

  CellaredDrinkService cellaredDrinkService

  @Inject
  CellaredDrinkChain(CellaredDrinkService cellaredDrinkService) {
    this.cellaredDrinkService = cellaredDrinkService
  }

  @Override
  void execute() throws Exception {
    get('cellars/:cellarSlug/drinks') {
      cellaredDrinkService.all(pathTokens['cellarSlug'], SortCommand.fromRequest(request))
                          .then { List<CellaredDrink> drinks ->
        render json(drinks)
      }
    }

    put('cellars/:cellarSlug/drinks/:id/drink') { CellarHQProfile profile ->
      String slug = pathTokens['cellarSlug']
      Long id = Long.valueOf(pathTokens['id'])

      cellaredDrinkService.findById(id).then { CellaredDrink drink ->
        requireSelf(context, profile, drink) {
          cellaredDrinkService.drink(slug, id).then { CellaredDrink drankDrink ->
            if (drankDrink == null) {
              clientError 404
            } else {
              render json(drankDrink)
            }
          }
        }
      }
    }

    path('cellars/:cellarSlug/drinks/:id') { CellarHQProfile profile ->
      byMethod {
        get {
          Long id = Long.valueOf(pathTokens['id'])

          cellaredDrinkService.findById(id).then { CellaredDrink drink ->
            requireSelf(context, profile, drink) {
              if (drink) {
                render json(drink)
              } else {
                clientError 404
              }
            }
          }
        }

        post {
          CellaredDrink cellaredDrink = parse(fromJson(CellaredDrink))
          requireSelf(context, profile, cellaredDrink) {
            cellaredDrinkService.save(cellaredDrink)
                                .flatMap {
              cellaredDrinkService.findById(it.id)
            }.then { CellaredDrink createdDrink ->
              render json(createdDrink)
            }
          }
        }

        delete {
          Long id = Long.valueOf(pathTokens['id'])

          cellaredDrinkService.findById(id).then { CellaredDrink drink ->
            requireSelf(context, profile, drink) {
              cellaredDrinkService.delete(id).then {
                response.status(204).send()
              }
            }
          }
        }
      }
    }
  }

  void requireSelf(Context context, CellarHQProfile profile, CellaredDrink cellaredDrink, Closure operation) {
    context.with {
      boolean isSelf = cellaredDrink?.cellarId == profile.cellarId

      if (isSelf) {
        operation()
      } else {
        response.status(403)
        render json([
          message: Messages.UNAUTHORIZED_ERROR
        ])
      }
    }
  }
}
