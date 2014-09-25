package com.cellarhq.brewerydb


class BreweryDbBeerRetreiver {
    void withEachBrewery(Closure itemHandler) {
        BreweryDbApi api = BreweryDBApiFactory.buildBreqeryDbApi()

        Map firstPageOfResults = api.listBeers(1, 'y')
        Integer totalPages = firstPageOfResults.numberOfPages

        (1..totalPages).each { Integer currentPage ->

            Map results = api.listBeers(currentPage, 'y')

            results.data.each { itemHandler(it) }
        }
    }
}
