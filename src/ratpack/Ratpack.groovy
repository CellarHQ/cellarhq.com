import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

import com.cellarhq.CellarHQModule
import com.cellarhq.auth.AuthPathAuthorizer
import com.cellarhq.ErrorHandler
import com.cellarhq.domain.Activity
import com.cellarhq.domain.Beer
import com.cellarhq.domain.BeerCategory
import com.cellarhq.domain.Brewery
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.CellarRole
import com.cellarhq.domain.CellaredBeer
import com.cellarhq.domain.EmailAccount
import com.cellarhq.domain.Glassware
import com.cellarhq.domain.OpenIdAccount
import com.cellarhq.domain.Photo
import com.cellarhq.domain.Style
import com.cellarhq.ratpack.hibernate.HibernateModule
import com.cellarhq.ratpack.hibernate.SessionFactoryHealthCheck
import com.cellarhq.services.CellarService
import org.pac4j.http.client.FormClient
import org.pac4j.http.credentials.SimpleTestUsernamePasswordAuthenticator
import ratpack.codahale.metrics.CodaHaleMetricsModule
import ratpack.error.ServerErrorHandler
import ratpack.groovy.markuptemplates.MarkupTemplatingModule
import ratpack.hikari.HikariModule
import ratpack.pac4j.Pac4jModule
import ratpack.session.SessionModule
import ratpack.session.store.MapSessionsModule
import rx.functions.Action1

ratpack {
    bindings {
        bind SessionFactoryHealthCheck

        add new CodaHaleMetricsModule().healthChecks()
        add new HikariModule()
        add new HikariModule([URL: 'jdbc:h2:mem:;INIT=CREATE SCHEMA IF NOT EXISTS cellarhq'], 'org.h2.jdbcx.JdbcDataSource')
        add new HibernateModule(
                Activity,
                Beer,
                Brewery,
                BeerCategory,
                Cellar,
                CellaredBeer,
                CellarRole,
                EmailAccount,
                Glassware,
                OpenIdAccount,
                Photo,
                Style)
        add new SessionModule()
        add new MapSessionsModule(10, 5)
        add new Pac4jModule<>(new FormClient('/login', new SimpleTestUsernamePasswordAuthenticator()),
                            new AuthPathAuthorizer())

        add new MarkupTemplatingModule()

        add new CellarHQModule()

        bind ServerErrorHandler, ErrorHandler
    }

    handlers { CellarService cellarService ->
        get {
            cellarService.save(new Cellar(screenName: 'someone')).subscribe { Cellar persistedCellar ->
                cellarService.all().toList().subscribe { List<Cellar> cellarList ->
                    render groovyMarkupTemplate('index.gtpl', cellars: cellarList)
                }
            }
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

        get('about') {}
        get('register') {}

        /**
         * The login page.
         */
        get('login') {
            render groovyMarkupTemplate('login.gtpl',
                    title: 'Login',
                    action: '/pac4j-callback',
                    method: 'post',
                    buttonText: 'Login',
                    error: request.queryParams.error ?: '')
        }

        get('forgot-password') {}

        /**
         * Backwards compatibility endpoints:
         */
        handler('signup') {
            redirect(301, '/register')
        }
        handler('signin') {
            redirect(301, '/login')
        }
        handler('forgotpassword') {
            redirect(301, '/forgot-password')
        }

        assets "public"
    }
}
