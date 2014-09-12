package com.cellarhq.endpoints

import com.cellarhq.domain.Cellar
import com.cellarhq.domain.views.CellaredDrinkDetails
import com.cellarhq.services.CellarService
import com.cellarhq.services.CellaredDrinkService
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.pac4j.core.profile.UserProfile
import ratpack.groovy.handling.GroovyChainAction

import static ratpack.handlebars.Template.handlebarsTemplate

import java.time.LocalDate

@Slf4j
class CellarsEndpoint extends GroovyChainAction {

    CellarService cellarService
    CellaredDrinkService cellaredDrinkService

    @Inject
    CellarsEndpoint(CellarService cellarService,
                    CellaredDrinkService cellaredDrinkService) {
        this.cellarService = cellarService
        this.cellaredDrinkService = cellaredDrinkService
    }

    @Override
    protected void execute() throws Exception {
        get('cellars') {
            Integer requestedPage = request.queryParams.page?.toInteger() ?: 1
            Integer pageSize = 20
            Integer offset = (requestedPage - 1) * pageSize
            String searchTerm = request.queryParams.search

            rx.Observable<Integer> totalCount = cellarService.count(searchTerm).single()
            rx.Observable cellars = searchTerm ?
                    cellarService.search(searchTerm, pageSize, offset).toList() :
                    cellarService.all(pageSize, offset).toList()

            rx.Observable.zip(cellars, totalCount) { List list, Integer count ->
                [
                        cellars: list,
                        totalCount: count
                ]
            }.subscribe({ Map map ->
                Integer pageCount = (map.totalCount / pageSize) + (map.totalCount % pageSize)

                render handlebarsTemplate('cellars/list.html',
                        cellars: map.cellars,
                        currentPage: requestedPage,
                        totalPageCount: pageCount,
                        title: 'CellarHQ : Cellars',
                        pageId: 'cellars.list')
            }, {
                clientError 500
            })
        }

        get('cellars/:slug') {
            String slug = pathTokens['slug']
            boolean isSelf = request.maybeGet(UserProfile)?.username == slug

            rx.Observable.zip(
                    cellarService.findBySlug(slug).single(),
                    cellaredDrinkService.all(slug).toList()
            ) { Cellar cellar, List cellaredDrinks ->
                [
                        cellar: cellar,
                        cellaredDrinks: cellaredDrinks,
                ]
            }.subscribe { Map map ->
                if (map.cellar == null) {
                    clientError 404
                } else {
                    render handlebarsTemplate('cellars/show.html',
                            cellar: map.cellar,
                            photo: map.cellar.photo,
                            cellaredDrinks: map.cellaredDrinks?:[
                                    new CellaredDrinkDetails(
                                            id: 1234,
                                            organizationSlug: 'brewwery',
                                            organizationName: 'Brwerey',
                                            drinkSlug: 'beer',
                                            drinkName: 'Beer',
                                            styleName: 'Style',
                                            size: 'big',
                                            quantity: 2,
                                            bottleDate: LocalDate.now(),
                                            notes: 'blah blah',
                                            binIdentifier: 213,
                                            dateAcquired: LocalDate.now(),
                                            drinkByDate: LocalDate.now(),
                                            tradeable: true,
                                            numTradeable: 1
                                    )
                            ],
                            self: isSelf,
                            title: "CellarHQ : ${map.cellar.displayName}",
                            pageId: 'cellars.show')
                }
            }
        }
    }
}
