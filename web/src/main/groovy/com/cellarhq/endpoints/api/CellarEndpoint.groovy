package com.cellarhq.endpoints.api

import static ratpack.jackson.Jackson.json

import com.cellarhq.domain.Cellar
import com.cellarhq.services.CellarService
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain

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
            put('cellars/validate-name') {
                if (!request.queryParams.name) {
                    clientError 400
                }

                cellarService.validateScreenName(request.queryParams.name).single().subscribe { Cellar cellar ->
                    log.info(request.queryParams.name)
                    log.info(cellar?.toString())
                    render json(cellar == null)
                }
            }
        }
    }
}
