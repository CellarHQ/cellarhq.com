package com.cellarhq.api

import com.cellarhq.api.services.CellaredDrinkService
import com.cellarhq.auth.profiles.CellarHQProfile
import com.cellarhq.common.Messages
import com.cellarhq.domain.CellaredDrink
import com.cellarhq.jooq.SortCommand
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain
import ratpack.handling.Context

import static ratpack.jackson.Jackson.fromJson
import static ratpack.jackson.Jackson.json

@Slf4j
class CellaredDrinkEndpoint implements Action<Chain> {

  CellaredDrinkService cellaredDrinkService

  @Inject
  CellaredDrinkEndpoint(CellaredDrinkService cellaredDrinkService) {
    this.cellaredDrinkService = cellaredDrinkService
  }

  @Override
  void execute(Chain chain) throws Exception {
    Groovy.chain(chain) {
      get('cellars/:cellarSlug/drinks') {
        cellaredDrinkService.all(
          pathTokens['cellarSlug'],
          SortCommand.fromRequest(request))
          .toList()
          .subscribe { List<CellaredDrink> drinks ->
          render json(drinks)
        }
      }

      put('cellars/:cellarSlug/drinks/:id/drink') { CellarHQProfile profile ->
        String slug = pathTokens['cellarSlug']
        Long id = Long.valueOf(pathTokens['id'])

        cellaredDrinkService.findById(id).single().subscribe { CellaredDrink drink ->
          requireSelf(context, profile, drink) {
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

      path('cellars/:cellarSlug/drinks/:id') { CellarHQProfile profile ->
        byMethod {
          get {
            Long id = Long.valueOf(pathTokens['id'])

            cellaredDrinkService.findById(id).single().subscribe { CellaredDrink drink ->
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
                .single().flatMap {
                cellaredDrinkService.findById(it.id).single()
              } subscribe { CellaredDrink createdDrink ->
                render json(createdDrink)
              }
            }
          }

          delete {
            Long id = Long.valueOf(pathTokens['id'])

            cellaredDrinkService.findById(id).single().subscribe { CellaredDrink drink ->
              requireSelf(context, profile, drink) {
                cellaredDrinkService.delete(id).subscribe {
                  response.status(204).send()
                }
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
