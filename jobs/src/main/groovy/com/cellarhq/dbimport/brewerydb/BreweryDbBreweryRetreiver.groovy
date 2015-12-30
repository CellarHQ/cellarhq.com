package com.cellarhq.dbimport.brewerydb

class BreweryDbBreweryRetreiver {
  void withEachBrewery(Closure itemHandler) {
    BreweryDbApi api = BreweryDBApiFactory.buildBreqeryDbApi()

    Map firstPageOfResults = api.listBreweries(1, 'Y', 'Y')
    Integer totalPages = firstPageOfResults.numberOfPages

    int breweryCount = 0
    (1..totalPages).each { Integer currentPage ->

      Map results = api.listBreweries(currentPage, 'Y', 'Y')


      results.data.each {
        itemHandler(it)
        breweryCount++
      }
      println "Done with ${currentPage} page processed ${breweryCount} total breweries"
    }
  }
}
