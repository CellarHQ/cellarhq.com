/**
 * This was run one time to generate the initial style data by importing it from BreweryDB API.
 *
 * groovy scripts/build_styles_sql.groovy >> styles.sql
 *
 * Resulting SQL is dependent on categories being loaded first
 *
 */

@Grab(group='com.squareup.retrofit', module='retrofit', version='1.6.1' )

import groovy.json.JsonOutput

import retrofit.*
import retrofit.http.*

public interface BreweryDBApi {
    @GET("/glassware")
    Map listGlassware()
}


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


BreweryDBApi breweryDB = restAdapter.create(BreweryDBApi)

breweryDB.listGlassware().data.each {
    println "INSERT INTO glassware (name, brewery_db_id, brewery_db_last_updated, data) VALUES ('${it.name}', '${it.id}', now(), '${escape(new JsonOutput().toJson(it))}');"
}

String escape(String string) {
    return string.replaceAll("'", "''")
}


