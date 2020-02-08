package com.cellarhq.api

import com.cellarhq.api.services.CellarService
import com.cellarhq.domain.Cellar
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain

import static ratpack.jackson.Jackson.json

@Slf4j
class CellarEndpoint implements Action<Chain> {

  CellarService cellarService

  @Inject
  CellarEndpoint(CellarService cellarService) {
    this.cellarService = cellarService
  }

  @Override
  void execute(Chain chain) throws Exception {
    Groovy.chain(chain) {
      put('api/cellars/validate-name') {
        if (!request.queryParams.name) {
          clientError 400
        }

        cellarService.validateScreenName(request.queryParams.name).then { Cellar cellar ->
          log.info(request.queryParams.name)
          log.info(cellar?.toString())
          render json(cellar == null)
        }
      }
    }
  }
}
