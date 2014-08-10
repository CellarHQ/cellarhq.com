import static com.cellarhq.ratpack.hibernate.HibernateDSL.transaction
import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

import com.cellarhq.CellarHQModule
import com.cellarhq.auth.AuthPathAuthorizer
import com.cellarhq.ErrorHandler
import com.cellarhq.domain.*
import com.cellarhq.endpoints.RegisterEndpoint
import com.cellarhq.endpoints.TwitterLoginEndpoint
import com.cellarhq.ratpack.hibernate.HibernateModule
import com.cellarhq.ratpack.hibernate.SessionFactoryHealthCheck
import com.cellarhq.services.*
import com.cellarhq.util.SessionUtil
import org.pac4j.core.profile.CommonProfile
import org.pac4j.oauth.client.TwitterClient
import ratpack.codahale.metrics.CodaHaleMetricsModule
import ratpack.error.ServerErrorHandler
import ratpack.groovy.markuptemplates.MarkupTemplatingModule
import ratpack.hikari.HikariModule
import ratpack.pac4j.Pac4jModule
import ratpack.pac4j.internal.Pac4jCallbackHandler
import ratpack.remote.RemoteControlModule
import ratpack.session.SessionModule
import ratpack.session.store.MapSessionsModule
import ratpack.session.store.SessionStorage

import java.time.LocalDateTime

ratpack {
    bindings {
        bind SessionFactoryHealthCheck
        bind Pac4jCallbackHandler

        add new CodaHaleMetricsModule().healthChecks()
        add new HikariModule([URL: 'jdbc:h2:mem:;INIT=CREATE SCHEMA IF NOT EXISTS cellarhq'], 'org.h2.jdbcx.JdbcDataSource')
        add new HibernateModule(
                Activity,
                Drink,
                Organization,
                DrinkCategory,
                Cellar,
                CellaredDrink,
                CellarRole,
                EmailAccount,
                Glassware,
                OAuthAccount,
                Photo,
                Style)
        add new RemoteControlModule()

        add new SessionModule()
        add new MapSessionsModule(10, 5)
        add new Pac4jModule<>(
//                new FormClient('/login', new SimpleTestUsernamePasswordAuthenticator()),
                new TwitterClient('jnvxx2qjluMFdJN5dt4xRw', 'IPRGbYPFlEqfSHFdaNxQtOc755HnGVIGrqpOHWXmI'),
                new AuthPathAuthorizer())

        add new MarkupTemplatingModule()

        add new CellarHQModule()

        bind ServerErrorHandler, ErrorHandler
    }

    handlers { CellarService cellarService, 
               DrinkService drinkService, 
               StyleService styleService, 
               GlasswareService glasswareService,
               DrinkCategoryService drinkCategoryService,
               OrganizationService organizationService ->
        get {
            transaction(context, {
                DrinkCategory drinkCategory = drinkCategoryService.save(
                    new DrinkCategory(
                        name: 'North American Origin Ales'
                        ))
                Style style = styleService.save(new Style(
                        category: drinkCategory, 
                        name: 'IPA',
                        searchable: true))

                Glassware glassware = glasswareService.save(new Glassware(
                        name: 'Pint',
                        searchable: true
                    ))

                Organization org = organizationService.save(new Organization(
                        type: OrganizationType.BREWERY,
                        slug: 'http://',
                        name: 'Surly'
                    ))


                Drink drink = drinkService.save(new Drink(
                        type: DrinkType.BEER,
                        photo: null,
                        style: style,
                        glassware: glassware,
                        organization: org,
                        name: 'Furious',
                        description: 'A tempest on the tongue, or a moment of pure hop bliss? Brewed with a dazzling blend of American hops and Scottish malt, this crimson-hued ale delivers waves of citrus, pine and caramel-toffee. For those who favor flavor, Furious has the hop-fire your taste buds have been screeching for.',
                        slug: 'http://kyleboon.com',
                        srm: 27,
                        ibu: 65,
                        abv: 6.5,
                        availability: Availability.YEAR_ROUND,
                        locked: true,
                        needsModeration: false,
                        searchable: true,
                        breweryDbId: 'dsfasdgdgdfvc',
                        breweryDbLastUpdated: LocalDateTime.now()
                    ))

                [cellarService.save(new Cellar(screenName: 'someone'))]
            }).then { List<Cellar> cellarList ->
                render groovyMarkupTemplate(
                        'index.gtpl',
                        cellars: cellarList,
                        loggedIn: SessionUtil.isLoggedIn(request.maybeGet(CommonProfile)))
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
                    transaction(context, {
                         drinkService.get(pathTokens["id"].toLong())
                    }).then { Drink drink ->
                        if (drink) {
                            render groovyMarkupTemplate('beers/show.gtpl',
                                    drink: drink,
                                    loggedIn: SessionUtil.isLoggedIn(request.maybeGet(CommonProfile)))
                        } else {
                            clientError(404)
                        }
                    }
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
        get('yourcellar') {
            CommonProfile profile = request.get(CommonProfile)
            render groovyMarkupTemplate('yourcellar.gtpl',
                    username: profile.username,
                    title: 'Your Cellar',
                    loggedIn: true)
        }

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

        handler('register', registry.get(RegisterEndpoint))

        /**
         * Auth pages
         */
        get('login') {
            render groovyMarkupTemplate('login.gtpl',
                    title: 'Login',
                    action: '/pac4j-callback',
                    method: 'post',
                    buttonText: 'Login',
                    error: request.queryParams.error ?: '',
                    loggedIn: SessionUtil.isLoggedIn(request.maybeGet(CommonProfile)))
        }
        handler('login-twitter', registry.get(TwitterLoginEndpoint))

        get('logout') {
            request.get(SessionStorage).clear()

            if (request.queryParams.error) {
                redirect("/?error=${request.queryParams.error}")
            } else {
                redirect('/')
            }
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
