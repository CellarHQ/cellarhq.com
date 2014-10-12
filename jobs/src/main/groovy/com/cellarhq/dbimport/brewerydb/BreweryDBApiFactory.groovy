package com.cellarhq.dbimport.brewerydb

import retrofit.RequestInterceptor
import retrofit.RestAdapter


class BreweryDBApiFactory {
    static BreweryDbApi buildBreqeryDbApi() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade request) {
                request.addQueryParam("key", "7271871fa894f8f377714607a2997f5d")
            }
        }


        RestAdapter restAdapter = new RestAdapter.Builder()
            //.setLogLevel(RestAdapter.LogLevel.FULL)
            .setEndpoint("http://api.brewerydb.com/v2/")
            .setRequestInterceptor(requestInterceptor)
            .build()


        return restAdapter.create(BreweryDbApi)
    }
}
