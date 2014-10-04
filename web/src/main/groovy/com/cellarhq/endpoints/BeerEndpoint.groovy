package com.cellarhq.endpoints

import static ratpack.handlebars.Template.handlebarsTemplate

import com.cellarhq.domain.Drink
import com.cellarhq.jooq.SortCommand
import com.cellarhq.services.DrinkService
import com.google.inject.Inject
import ratpack.groovy.handling.GroovyChainAction

import javax.validation.ValidatorFactory

class BeerEndpoint extends GroovyChainAction {
    ValidatorFactory validatorFactory
    DrinkService drinkService

    @Inject
    BeerEndpoint(ValidatorFactory validatorFactory, DrinkService drinkService) {
        this.validatorFactory = validatorFactory
        this.drinkService = drinkService
    }

    @Override
    protected void execute() throws Exception {
        handler('beers') {
            byMethod {
                /**
                 * List all breweries; has search.
                 */
                get {
                    Integer requestedPage = request.queryParams.page?.toInteger() ?: 1
                    Integer pageSize = 20
                    Integer offset = (requestedPage - 1) * pageSize
                    String searchTerm = request.queryParams.search

                    rx.Observable<Integer> totalCount = searchTerm ?
                        drinkService.searchCount(searchTerm).single() :
                        drinkService.count().single()

                    rx.Observable drinks = searchTerm ?
                        drinkService.search(searchTerm, SortCommand.fromRequest(request), pageSize, offset).toList() :
                        drinkService.all(SortCommand.fromRequest(request), pageSize, offset).toList()

                    rx.Observable.zip(drinks, totalCount) { List list, Integer count ->
                        [
                            drinks: list,
                            totalCount: count
                        ]
                    }.subscribe({ Map map ->
                        Integer pageCount = (map.totalCount / pageSize)
                        Boolean shouldShowPagination = pageCount != 0

                        render handlebarsTemplate('beer/list-beer.html',
                            [drinks: map.drinks,
                            currentPage: requestedPage,
                            totalPageCount: pageCount,
                            shouldShowPagination: shouldShowPagination,
                            title: 'CellarHQ : Beer',
                            pageId: 'beer.list'])
                    }, {
                        clientError 500
                    })
                }

                /**
                 * Add a new brewery.
                 */
                post {

                }
            }
        }

        /**
         * HTML page for adding a new brewery.
         */
        get('beers/add') {
        }

        /**
         * HTML page for editing breweries.
         */
        get('beers/:slug/edit') {
        }

        handler('beers/:slug') {
            byMethod {
                /**
                 * Get an existing brewery.
                 */
                get {
                    String slug = pathTokens['slug']

                    drinkService.findBySlug(slug).single().subscribe { Drink drink ->
                        render handlebarsTemplate('beer/show-beer.html',
                            [drink: drink,
                            title: "CellarHQ : ${drink.name}",
                            pageId: 'beer.show'])
                    }
                }
            }
        }
    }
}
