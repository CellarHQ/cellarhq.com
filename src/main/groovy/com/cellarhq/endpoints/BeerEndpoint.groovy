package com.cellarhq.endpoints

import ratpack.groovy.handling.GroovyChainAction


class BeerEndpoint extends GroovyChainAction {
    @Override
    protected void execute() throws Exception {
        handler('beers') {  ->
            byMethod {
                /**
                 * Paginated list of beers, has search.
                 */
                get {}

                /**
                 * Create a new beer.
                 */
                post {}
            }
        }

        /**
         * HTML page for adding a new beer.
         */
        get('beers/add') {}

        handler('beers/:id') {  ->
            byMethod {
                /**
                 * Get an individual beer
                 */
                get {
//                    transaction(context, {
//                         drinkService.get(pathTokens["id"].toLong())
//                    }).then { Drink drink ->
//                        if (drink) {
//                            render handlebarsTemplate('beers/show.html',
//                                [
//                                    drink: drink,
//                                    pageId: 'beers-single',
//                                    loggedIn: SessionUtil.isLoggedIn(request.maybeGet(CommonProfile))
//                                ])
//                        } else {
//                            clientError(404)
//                        }
//                    }
                }

                /**
                 * Update an existing beer
                 */
                post {}

                /**
                 * Delete an existing beer.
                 */
                delete {}
            }
        }
        /**
         * HTML page for editing an existing beer.
         */
        get('beers/:id/edit') {}
    }
}
