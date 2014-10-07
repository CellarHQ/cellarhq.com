package com.cellarhq.endpoints

import com.cellarhq.auth.SecurityModule
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.Photo
import com.cellarhq.jooq.SortCommand
import com.cellarhq.services.CellarService
import com.cellarhq.services.CellaredDrinkService
import com.cellarhq.services.photo.PhotoService
import com.cellarhq.util.LogUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler
import ratpack.session.store.SessionStorage

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class YourCellarEndpoint extends GroovyHandler {

    private final CellarService cellarService
    private final CellaredDrinkService cellaredDrinkService
    private final PhotoService photoService

    @Inject
    YourCellarEndpoint(CellarService cellarService,
                       CellaredDrinkService cellaredDrinkService,
                       PhotoService photoService) {
        this.cellarService = cellarService
        this.cellaredDrinkService = cellaredDrinkService
        this.photoService = photoService
    }

    @Override
    protected void handle(GroovyContext context) {
        context.byMethod {
            get {
                Long cellarId = (Long) request.get(SessionStorage).get(SecurityModule.SESSION_CELLAR_ID)

                rx.Observable.zip(
                    cellarService.get(cellarId).single(),
                    cellaredDrinkService.all(cellarId, SortCommand.fromRequest(request)).toList(),
                    photoService.findByCellarId(cellarId).single()
                ) { Cellar cellar, List cellaredDrinks, Photo photo ->
                    [
                        cellar        : cellar,
                        cellaredDrinks: cellaredDrinks,
                        photo         : photo
                    ]
                }.subscribe { Map map ->
                    if (map.cellar == null) {
                        log.error(LogUtil.toLog('YourCellar', [
                            msg: 'Could not locate a cellar by user session id',
                            id : cellarId
                        ]))
                        clientError 404
                    } else {
                        render handlebarsTemplate('cellars/show-cellar.html',
                            [cellar        : map.cellar,
                             photo         : map.photo,
                             cellaredDrinks: map.cellaredDrinks,
                             self          : true,
                             title         : 'CellarHQ : Your Cellar',
                             pageId        : 'yourcellar']
                        )
                    }
                }
            }
        }
    }
}
