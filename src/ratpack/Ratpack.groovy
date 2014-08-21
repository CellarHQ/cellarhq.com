import com.cellarhq.domain.jooq.Organization
import ratpack.jackson.JacksonModule
import ratpack.rx.RxRatpack

import static com.cellarhq.ratpack.hibernate.HibernateDSL.transaction
import static ratpack.groovy.Groovy.ratpack

import com.cellarhq.CellarHQModule
import com.cellarhq.ErrorHandler
import com.cellarhq.auth.SecurityModule
import com.cellarhq.domain.*
import com.cellarhq.endpoints.OrganizationEndpoint
import com.cellarhq.endpoints.SettingsEndpoint
import com.cellarhq.endpoints.auth.ChangePasswordEndpoint
import com.cellarhq.endpoints.auth.ForgotPasswordEndpoint
import com.cellarhq.endpoints.auth.FormLoginEndpoint
import com.cellarhq.endpoints.auth.RegisterEndpoint
import com.cellarhq.endpoints.auth.TwitterLoginEndpoint
import com.cellarhq.endpoints.YourCellarEndpoint
import com.cellarhq.ratpack.hibernate.HibernateModule
import com.cellarhq.ratpack.hibernate.SessionFactoryHealthCheck
import com.cellarhq.services.*
import com.cellarhq.util.SessionUtil
import org.pac4j.core.profile.CommonProfile
import ratpack.codahale.metrics.CodaHaleMetricsModule
import ratpack.error.ServerErrorHandler
import ratpack.handlebars.HandlebarsModule
import static ratpack.handlebars.Template.handlebarsTemplate
import ratpack.hikari.HikariModule
import ratpack.launch.LaunchConfig
import ratpack.pac4j.internal.Pac4jCallbackHandler
import ratpack.pac4j.internal.SessionConstants
import ratpack.remote.RemoteControlModule
import ratpack.session.SessionModule
import ratpack.session.store.MapSessionsModule
import ratpack.session.store.SessionStorage

import static ratpack.jackson.Jackson.json

String getConfig(LaunchConfig launchConfig, String key, String defaultValue) {
    String value = System.getenv(key)
    if (value == null) {
        if (System.hasProperty(key)) {
            value = System.getProperty(key)
        } else {
            value = defaultValue
        }
        value = launchConfig.getOther(key, value)
    }

    return value
}

ratpack {
    bindings {
        bind SessionFactoryHealthCheck
        bind Pac4jCallbackHandler

        add new CodaHaleMetricsModule().healthChecks()
        add new HikariModule(
                serverName: getConfig(launchConfig, 'other.hikari.dataSourceProperties.serverName', 'localhost'),
                portNumber: getConfig(launchConfig, 'other.hikari.dataSourceProperties.portNumber', '15432'),
                databaseName: getConfig(launchConfig, 'other.hikari.dataSourceProperties.databaseName', 'cellarhq'),
                user: getConfig(launchConfig, 'other.hikari.dataSourceProperties.user', 'cellarhq'),
                password: getConfig(launchConfig, 'other.hikari.dataSourceProperties.password', 'cellarhq'),
                getConfig(launchConfig, 'other.hikari.dataSourceClassName', 'org.postgresql.ds.PGSimpleDataSource')
        )
        add new JacksonModule()
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
                PasswordChangeRequest,
                Photo,
                Style)
        add new RemoteControlModule()

        add new SessionModule()
        add new MapSessionsModule(10, 5)
        add new SecurityModule()

        add new HandlebarsModule()

        add new CellarHQModule()

        bind ServerErrorHandler, ErrorHandler

        init {
            RxRatpack.initialize()
        }
    }

    handlers { CellarService cellarService, 
               DrinkService drinkService, 
               StyleService styleService, 
               GlasswareService glasswareService,
               DrinkCategoryService drinkCategoryService,
               OrganizationService organizationService ->

        get {
            transaction(context, {
                [cellarService.save(new Cellar(screenName: 'someone'))]
            }).then { List<Cellar> cellarList ->
                render handlebarsTemplate('index.html', 
                        cellars: cellarList,
                        title: 'CellarHQ',
                        pageId: 'home',
                        success: request.queryParams.success ?: '',
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
                            render handlebarsTemplate('beers/show.html', 
                                [
                                    drink: drink,
                                    pageId: 'beers-single',
                                    loggedIn: SessionUtil.isLoggedIn(request.maybeGet(CommonProfile))
                                ])
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

        handler('breweries/:slug') {
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

        /**
         * Auth pages
         */

        handler('register', registry.get(RegisterEndpoint))
        handler('auth-twitter', registry.get(TwitterLoginEndpoint))
        handler('auth-form', registry.get(FormLoginEndpoint))
        get('login') {
            render handlebarsTemplate('login.html',
                    title: 'Login',
                    action: '/pac4j-callback',
                    method: 'post',
                    buttonText: 'Login',
                    info: request.queryParams.info ?: '',
                    error: request.queryParams.error ?: '',
                    pageId: 'login',
                    loggedIn: SessionUtil.isLoggedIn(request.maybeGet(CommonProfile)))
        }

        get('logout') {
            // TODO Accessing pac4j internals...
            request.get(SessionStorage).remove(SessionConstants.USER_PROFILE)

            if (request.queryParams.error) {
                redirect("/?error=${request.queryParams.error}")
            } else {
                redirect('/')
            }
        }

        handler('forgot-password', registry.get(ForgotPasswordEndpoint))
        handler('forgot-password/:id', registry.get(ChangePasswordEndpoint))
        handler('settings', registry.get(SettingsEndpoint))

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

        /**************************************************************************************************************
         * API
         */

        prefix("api") {
            get("organizations") {
                organizationService.all().toList().subscribe { List<Organization> organizations ->
                    render json(organizations)
                }
            }

            handler("organizations/:slug?", registry.get(OrganizationEndpoint))
        }

        assets "public"
    }
}
