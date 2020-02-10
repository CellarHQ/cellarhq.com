package com.cellarhq.api

import com.cellarhq.api.services.CellarService
import com.cellarhq.domain.Cellar
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.groovy.handling.GroovyChainAction

import static ratpack.jackson.Jackson.json

@Slf4j
class CellarChain extends GroovyChainAction {

  CellarService cellarService

  @Inject
  CellarChain(CellarService cellarService) {
    this.cellarService = cellarService
  }

  @Override
  void execute() throws Exception {
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
