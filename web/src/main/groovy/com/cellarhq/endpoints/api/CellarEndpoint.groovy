package com.cellarhq.endpoints.api

import static ratpack.jackson.Jackson.fromJson
import static ratpack.jackson.Jackson.json

import com.cellarhq.domain.Cellar
import com.cellarhq.services.CellarService
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler

/**
 * @todo Add validation
 */
@Slf4j
class CellarEndpoint extends GroovyHandler {

    CellarService cellarService

    @Inject
    CellarEndpoint(CellarService cellarService) {
        this.cellarService = cellarService
    }

    @Override
    protected void handle(GroovyContext context) {
        context.with {
            String slug = pathTokens['slug']

            byMethod {
                get {
                    cellarService.findBySlug(slug).single().subscribe { Cellar cellar ->
                        if (cellar == null) {
                            clientError 404
                        } else {
                            render json(cellar)
                        }
                    }
                }

                post {
                    cellarService.save(parse(fromJson(Cellar))
                    ).single().flatMap {
                        cellarService.findBySlug(it.slug).single()
                    } subscribe { Cellar createdCellar ->
                        render json(createdCellar)
                    }
                }

                put {
                    cellarService.save(parse(fromJson(Cellar))
                    ).single().flatMap {
                        cellarService.findBySlug(it.slug).single()
                    } subscribe { Cellar createdCellar ->
                        render json(createdCellar)
                    }
                }
            }
        }
    }
}
