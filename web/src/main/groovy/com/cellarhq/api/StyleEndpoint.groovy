package com.cellarhq.api

import com.cellarhq.api.services.StyleService
import com.cellarhq.domain.Style
import com.google.inject.Inject
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain

import static ratpack.jackson.Jackson.json

class StyleEndpoint implements Action<Chain> {

  private final StyleService styleService

  @Inject
  StyleEndpoint(StyleService styleService) {
    this.styleService = styleService
  }

  @Override
  void execute(Chain chain) throws Exception {
    Groovy.chain(chain) {
      get('styles/live-search') {
        styleService.search(request.queryParams.name, 20, 0).toList().subscribe { List<Style> styles ->
          render json(styles.collect {
            [
              id  : it.id,
              name: it.name
            ]
          })
        }
      }
      put('styles/validate-name') {
        styleService.search(request.queryParams.name, 1, 0).toList().subscribe { List<Style> styles ->
          if (request.queryParams.exists) {
            render json(!styles.empty)
          } else {
            render json(styles.empty)
          }
        }
      }
    }
  }
}
