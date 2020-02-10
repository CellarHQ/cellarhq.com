package com.cellarhq.api

import com.cellarhq.api.services.GlasswareService
import com.cellarhq.domain.Glassware
import com.google.inject.Inject
import ratpack.groovy.handling.GroovyChainAction

import static ratpack.jackson.Jackson.json

class GlasswareChain extends GroovyChainAction {

  GlasswareService glasswareService

  @Inject
  GlasswareChain(GlasswareService glasswareService) {
    this.glasswareService = glasswareService
  }

  @Override
  void execute() throws Exception {
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
