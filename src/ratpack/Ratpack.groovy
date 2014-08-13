import static com.cellarhq.ratpack.hibernate.HibernateDSL.transaction
import static org.jooq.impl.DSL.val
import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

import com.cellarhq.CellarHQModule
import com.cellarhq.ErrorHandler
import com.cellarhq.auth.SecurityModule
import com.cellarhq.domain.*
import com.cellarhq.endpoints.RegisterEndpoint
import com.cellarhq.endpoints.TwitterLoginEndpoint
import com.cellarhq.endpoints.YourCellarEndpoint
import com.cellarhq.generated.Sequences
import com.cellarhq.generated.Tables
import com.cellarhq.generated.tables.records.CategoryRecord
import com.cellarhq.generated.tables.records.CellarRecord
import com.cellarhq.generated.tables.records.DrinkRecord
import com.cellarhq.generated.tables.records.GlasswareRecord
import com.cellarhq.generated.tables.records.OrganizationRecord
import com.cellarhq.generated.tables.records.StyleRecord
import com.cellarhq.ratpack.hibernate.HibernateModule
import com.cellarhq.ratpack.hibernate.SessionFactoryHealthCheck
import com.cellarhq.services.*
import com.cellarhq.util.SessionUtil
import org.jooq.DSLContext
import org.jooq.Result
import org.jooq.SQLDialect
import org.jooq.exception.DataAccessException
import org.jooq.impl.DSL
import org.jooq.tools.jdbc.JDBCUtils
import org.pac4j.core.profile.CommonProfile
import ratpack.codahale.metrics.CodaHaleMetricsModule
import ratpack.error.ServerErrorHandler
import ratpack.groovy.markuptemplates.MarkupTemplatingModule
import ratpack.hikari.HikariModule
import ratpack.pac4j.internal.Pac4jCallbackHandler
import ratpack.pac4j.internal.SessionConstants
import ratpack.remote.RemoteControlModule
import ratpack.session.SessionModule
import ratpack.session.store.MapSessionsModule
import ratpack.session.store.SessionStorage

import javax.sql.DataSource
import java.sql.Connection
import java.time.LocalDateTime

ratpack {
    bindings {
//        bind SessionFactoryHealthCheck
        bind Pac4jCallbackHandler

        add new CodaHaleMetricsModule().healthChecks()
        // TODO: Functional tests may have to use an actual PG database, since sequences aren't compatible with H2. The
        //       alternative is we can have jOOQ mock the connection. Not pretty:
        //       http://www.jooq.org/doc/3.2/manual-single-page/#jdbc-mocking
        //       We may need to take a further look at this, but we don't have many functional tests, so it may not be
        //       a big deal...
        add new HikariModule(
                serverName: 'localhost',
                portNumber: '15432',
                databaseName: 'cellarhq',
                user: 'cellarhq',
                password: 'cellarhq',
                'org.postgresql.ds.PGSimpleDataSource')
        add new RemoteControlModule()

        add new SessionModule()
        add new MapSessionsModule(10, 5)
        add new SecurityModule()

        add new MarkupTemplatingModule()

        add new CellarHQModule()

        bind ServerErrorHandler, ErrorHandler
    }

    handlers { DataSource dataSource ->

        get {
            blocking {
                Connection conn = dataSource.connection
                DSLContext create = DSL.using(conn, SQLDialect.POSTGRES)

                // I'm thinking we could use the DSL directly from services and avoid the use of DAO classes.
                try {
                    create.insertInto(Tables.CELLAR, Tables.CELLAR.ID,
                                                     Tables.CELLAR.SCREEN_NAME,
                                                     Tables.CELLAR.DISPLAY_NAME)
                            .values(Sequences.CELLAR_ID_SEQ.nextval(), val('someone'), val('Someone'))
                            .execute()
                } catch (DataAccessException e) {
                    // Already created 'someone', just going to ignore for the purposes of this example.
                }

                // TODO: I'm sure there's a better way... although fetchLazy is what we'd probably use for Rx.
                create.selectFrom(Tables.CELLAR).fetchMap().collect { it.value }
            } then { List<CellarRecord> cellarList ->
                render groovyMarkupTemplate(
                        'index.gtpl',
                        cellars: cellarList,
                        loggedIn: SessionUtil.isLoggedIn(request.maybeGet(CommonProfile))
                )
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
//                            render groovyMarkupTemplate('beers/show.gtpl',
//                                    drink: drink,
//                                    loggedIn: SessionUtil.isLoggedIn(request.maybeGet(CommonProfile)))
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
        get('yourcellar', registry.get(YourCellarEndpoint))

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

//        handler('register', registry.get(RegisterEndpoint))

        /**
         * Auth pages
         */

//        handler('auth-twitter', registry.get(TwitterLoginEndpoint))

//        get('login') {
//            render groovyMarkupTemplate('login.gtpl',
//                    title: 'Login',
//                    action: '/pac4j-callback',
//                    method: 'post',
//                    buttonText: 'Login',
//                    error: request.queryParams.error ?: '',
//                    loggedIn: SessionUtil.isLoggedIn(request.maybeGet(CommonProfile)))
//        }
//
//        get('logout') {
//            // TODO Accessing pac4j internals...
//            request.get(SessionStorage).remove(SessionConstants.USER_PROFILE)
//
//            if (request.queryParams.error) {
//                redirect("/?error=${request.queryParams.error}")
//            } else {
//                redirect('/')
//            }
//        }

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
