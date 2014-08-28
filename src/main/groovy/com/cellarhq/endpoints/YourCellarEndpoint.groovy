package com.cellarhq.endpoints

import static ratpack.handlebars.Template.handlebarsTemplate

import com.cellarhq.auth.SecurityModule
import com.cellarhq.domain.Cellar
import com.cellarhq.services.CellarService
import com.cellarhq.services.CellaredDrinkService
import com.cellarhq.util.LogUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler
import ratpack.session.store.SessionStorage

@Slf4j
class YourCellarEndpoint extends GroovyHandler {

    CellarService cellarService
    CellaredDrinkService cellaredDrinkService

    @Inject
    YourCellarEndpoint(CellarService cellarService,
                    CellaredDrinkService cellaredDrinkService) {
        this.cellarService = cellarService
        this.cellaredDrinkService = cellaredDrinkService
    }

    @Override
    protected void handle(GroovyContext context) {
        context.with {
            byMethod {
                get {
                    Long cellarId = (Long) request.get(SessionStorage).get(SecurityModule.SESSION_CELLAR_ID)

                    rx.Observable.zip(
                            cellarService.find(cellarId).single(),
                            cellaredDrinkService.all(cellarId).toList()
                    ) { Cellar cellar, List cellaredDrinks ->
                        [
                                cellar: cellar,
                                cellaredDrinks: cellaredDrinks
                        ]
                    }.subscribe { Map map ->
                        if (map.cellar == null) {
                            log.error(LogUtil.toLog('YourCellar', [
                                    msg: 'Could not locate a cellar by user session id',
                                    id: profile.id
                            ]))
                            clientError 404
                        } else {
                            render handlebarsTemplate('cellars/show.html',
                                    cellar: map.cellar,
                                    cellaredDrinks: map.cellaredDrinks,
                                    self: true,
                                    title: 'CellarHQ : Your Cellar',
                                    pageId: 'yourcellar'
                            )
                        }
                    }
                }
            }
        }
    }
}
