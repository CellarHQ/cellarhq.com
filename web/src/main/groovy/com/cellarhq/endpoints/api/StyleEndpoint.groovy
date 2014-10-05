package com.cellarhq.endpoints.api

import static ratpack.jackson.Jackson.json

import com.cellarhq.domain.Style
import com.cellarhq.services.StyleService
import com.google.inject.Inject
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain

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
                styleService.search(request.queryParams.name, 5, 0).subscribe { List<Style> styles ->
                    render json(styles.collect {
                        [
                                id: it.id,
                                name: it.name
                        ]
                    })
                }
            }
        }
    }
}
