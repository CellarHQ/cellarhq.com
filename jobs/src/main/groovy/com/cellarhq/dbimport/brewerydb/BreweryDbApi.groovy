package com.cellarhq.dbimport.brewerydb

import retrofit.http.GET
import retrofit.http.Query

/**
 * Retrofit interface for all brewery db api calls
 */
interface BreweryDbApi {
    @GET("/glassware")
    Map listGlassware()

    @GET("/breweries")
    Map listBreweries(@Query('p') Integer p,
                      @Query('withSocialAccounts') String withSocialAccounts,
                      @Query('withLocations') String withLocations)

    @GET("/beers")
    Map listBeers(@Query('p') Integer p, @Query('withBreweries') String withBreweries)


}
