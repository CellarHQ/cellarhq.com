package com.cellarhq.api

import com.cellarhq.api.services.GlasswareService
import com.cellarhq.domain.Glassware
import com.google.inject.Inject
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain

import static ratpack.jackson.Jackson.json

class GlasswareEndpoint implements Action<Chain> {

  GlasswareService glasswareService

  @Inject
  GlasswareEndpoint(GlasswareService glasswareService) {
    this.glasswareService = glasswareService
  }

  @Override
  void execute(Chain chain) throws Exception {
    Groovy.chain(chain) {
      get('glassware/live-search') {
        glasswareService.search(request.queryParams.name, 20, 0).then { List<Glassware> glassware ->

          render json(glassware.collect {
            [
              id  : it.id,
              name: it.name
            ]
          })
        }
      }
      put('glassware/validate-name') {
        glasswareService.search(request.queryParams.name, 1, 0).then { List<Glassware> glassware ->

          if (request.queryParams.exists) {
            render json(!glassware.empty)
          } else {
            render json(glassware.empty)
          }
        }
      }
    }
  }
}
