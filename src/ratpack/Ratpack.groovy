import static ratpack.groovy.Groovy.groovyTemplate
import static ratpack.groovy.Groovy.ratpack

import com.cellarhq.jdbi.JdbiModule
import ratpack.hikari.HikariModule

ratpack {
    bindings {
        // TODO: Need to add configuration for URL & Driver.
        add new HikariModule([URL: 'jdbc:h2:mem:dev;INIT=CREATE SCHEMA IF NOT EXISTS DEV'], 'org.h2.jdbcx.JdbcDataSource')
        add new JdbiModule()
    }

    handlers {
        get {
            render groovyTemplate("index.html", title: "My Ratpack App")
        }

        handler('cellars') {
            byMethod {
                /**
                 * Paginated list of all cellars; has basic search
                 */
                get {}

                /**
                 * Create a new cellar.
                 */
                post {}
            }
        }
        handler('cellars/:id') {
            byMethod {
                /**
                 * Get an individual cellar - the ID being the username someone selects.
                 */
                get {}

                /**
                 * Update a cellar.
                 */
                post {}
            }
        }
        handler('cellars/:id/beers') {
            byMethod {
                /**
                 * Add a new beer to the cellar
                 */
                post {}

                /**
                 * Remove a beer from the cellar.
                 */
                delete {}
            }
        }
        handler('cellars/:id/beers/:beerId') {
            byMethod {
                /**
                 * Update a cellared beer.
                 */
                post {}

                /**
                 * Remove a cellared beer.
                 */
                delete {}
            }
        }

        handler('beers') {
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

        handler('beers/:id') {
            byMethod {
                /**
                 * Get an individual beer
                 */
                get {}

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

        handler('breweries') {
            byMethod {
                /**
                 * List all breweries; has search.
                 */
                get {}

                /**
                 * Add a new brewery.
                 */
                post {}
            }
        }

        /**
         * HTML page for adding a new brewery.
         */
        get('breweries/add') {}

        handler('breweries/:id') {
            byMethod {
                /**
                 * Get an existing brewery.
                 */
                get {}

                /**
                 * Update an existing brewery
                 */
                post {}

                /**
                 * Delete an existing brewery.
                 */
                delete {}
            }
        }

        /**
         * HTML page for editing breweries.
         */
        get('breweries/:id/edit') {}

        /**
         * Alias to /cellars/:id, auto-loads authenticated user.
         */
        get('yourcellar') {}

        handler('account') {
            byMethod {
                /**
                 * Get the account settings page.
                 */
                get {}

                /**
                 * Update your account settings.
                 */
                post {}
            }
        }

        handler('import') {
            byMethod {
                /**
                 * Get the import page.
                 */
                get {}

                /**
                 * Starts the import process.
                 */
                post {}
            }
        }
        handler('import/:id') {
            byMethod {
                /**
                 * Get the status of an in-process import.
                 */
                get {}

                /**
                 * Update the in-progress state of an import (fix conficts, etc)
                 */
                post {}

                /**
                 * Cancel an import.
                 */
                delete {}
            }
        }

        handler('about') {}

        assets "public"
    }
}
