package com.cellarhq.endpoints.api

import static ratpack.jackson.Jackson.json

import com.cellarhq.domain.Glassware
import com.cellarhq.services.GlasswareService
import com.google.inject.Inject
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain

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
                glasswareService.search(request.queryParams.name, 5, 0)
                        .toList()
                        .subscribe { List<Glassware> glassware ->

                    render json(glassware.collect {
                        [
                                id: it.id,
                                name: it.name
                        ]
                    })
                }
            }
            put('glassware/validate-name') {
                glasswareService.search(request.queryParams.name, 1, 0)
                        .toList()
                        .subscribe { List<Glassware> glassware ->

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
