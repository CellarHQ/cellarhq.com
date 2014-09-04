import com.cellarhq.CellarHQModule
import com.cellarhq.ClientErrorHandlerImpl
import com.cellarhq.ErrorHandler
import com.cellarhq.auth.SecurityModule
import com.cellarhq.endpoints.BreweryEndpoint
import com.cellarhq.endpoints.OrganizationEndpoint
import com.cellarhq.endpoints.SettingsEndpoint
import com.cellarhq.endpoints.YourCellarEndpoint
import com.cellarhq.endpoints.auth.*
import com.cellarhq.health.DatabaseHealthcheck
import com.codahale.metrics.health.HealthCheckRegistry
import ratpack.codahale.metrics.CodaHaleMetricsModule
import ratpack.error.ClientErrorHandler
import ratpack.error.ServerErrorHandler
import ratpack.handlebars.HandlebarsModule
import ratpack.hikari.HikariModule
import ratpack.jackson.JacksonModule
import ratpack.launch.LaunchConfig
import ratpack.pac4j.internal.SessionConstants
import ratpack.remote.RemoteControlModule
import ratpack.rx.RxRatpack
import ratpack.session.SessionModule
import ratpack.session.store.MapSessionsModule
import ratpack.session.store.SessionStorage

import static ratpack.groovy.Groovy.ratpack
import static ratpack.handlebars.Template.handlebarsTemplate

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
        bind ServerErrorHandler, ErrorHandler
        bind ClientErrorHandler, ClientErrorHandlerImpl
        bind DatabaseHealthcheck

        add new CodaHaleMetricsModule().healthChecks()
        add new CodaHaleMetricsModule().websocket()

        String serverName = getConfig(launchConfig, 'dataSource.serverName', 'localhost')
        String portNumber =  getConfig(launchConfig, 'dataSource.portNumber', '15432')
        String databaseName = getConfig(launchConfig, 'dataSource.databaseName', 'cellarhq')
        String user = getConfig(launchConfig, 'dataSource.user', 'cellarhq')
        String password = getConfig(launchConfig, 'dataSource.password', 'cellarhq')

        add new HikariModule(
                serverName: serverName,
                portNumber: portNumber,
                databaseName: databaseName,
                user: user,
                password: password,
                'org.postgresql.ds.PGSimpleDataSource')

        add new JacksonModule()
        add new RemoteControlModule()

        add new SessionModule()
        add new MapSessionsModule(10, 5)
        add new SecurityModule()

        add new HandlebarsModule()

        // IMPORTANT: Our module must be last, so we can override whatever we need to created by the other modules.
        add new CellarHQModule()

        init {
            RxRatpack.initialize()
        }
    }

    handlers {
        get {
            render handlebarsTemplate('index.html',
                    cellars: [],
                    title: 'CellarHQ',
                    pageId: 'home')
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

        handler chain(registry.get(BreweryEndpoint))


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
                    pageId: 'login')
        }

        get('logout') {
            // TODO Accessing pac4j internals...
            request.get(SessionStorage).remove(SessionConstants.USER_PROFILE)
            redirect('/')
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
            handler chain(registry.get(OrganizationEndpoint))
        }

        get('health-checks', { HealthCheckRegistry healthCheckRegistry ->
            render json(healthCheckRegistry.runHealthChecks())
        })

        assets "public"
    }
}
