package com.cellarhq.webapp

import com.cellarhq.auth.AuthenticationModule
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.Photo
import com.cellarhq.jooq.SortCommand
import com.cellarhq.api.services.CellarService
import com.cellarhq.api.services.CellaredDrinkService
import com.cellarhq.auth.services.photo.PhotoService
import com.cellarhq.util.LogUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain
import ratpack.session.store.SessionStorage

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class YourCellarEndpoint implements Action<Chain> {

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
    void execute(Chain chain) throws Exception {
        Groovy.chain(chain) {
            get('yourcellar') {
                Long cellarId = (Long) request.get(SessionStorage).get(AuthenticationModule.SESSION_CELLAR_ID)

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
                }.subscribe( { Map map ->
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
                }, { Throwable t ->
                    log.error(LogUtil.toLog('YourCellarError   '), t)
                    clientError 500
                })
            }


            get('yourarchive') {
                Long cellarId = (Long) request.get(SessionStorage).get(AuthenticationModule.SESSION_CELLAR_ID)

                rx.Observable.zip(
                        cellarService.get(cellarId).single(),
                        cellaredDrinkService.archive(cellarId, SortCommand.fromRequest(request)).toList(),
                        photoService.findByCellarId(cellarId).single()
                ) { Cellar cellar, List cellaredDrinks, Photo photo ->
                    [
                            cellar : cellar,
                            archive: cellaredDrinks,
                            photo  : photo
                    ]
                }.subscribe { Map map ->
                    if (map.cellar == null) {
                        log.error(LogUtil.toLog('YourCellar', [
                                msg: 'Could not locate a cellar by user session id',
                                id : cellarId
                        ]))
                        clientError 404
                    } else {
                        render handlebarsTemplate('cellars/show-archive.html',
                                [cellar : map.cellar,
                                 photo  : map.photo,
                                 archive: map.archive,
                                 self   : true,
                                 title  : 'CellarHQ : Your Cellar',
                                 pageId : 'yourarchive']
                        )
                    }
                }
            }
        }
    }
}
