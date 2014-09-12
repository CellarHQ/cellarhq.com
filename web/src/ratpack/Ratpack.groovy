import static ratpack.groovy.Groovy.ratpack
import static ratpack.handlebars.Template.handlebarsTemplate
import static ratpack.jackson.Jackson.json

import com.cellarhq.CellarHQModule
import com.cellarhq.ClientErrorHandlerImpl
import com.cellarhq.ErrorHandler
import com.cellarhq.auth.SecurityModule
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.CellaredDrink
import com.cellarhq.domain.views.HomepageStatistics
import com.cellarhq.endpoints.api.DrinkEndpoint
import com.cellarhq.endpoints.BreweryEndpoint
import com.cellarhq.endpoints.CellarsEndpoint
import com.cellarhq.endpoints.OrganizationEndpoint
import com.cellarhq.endpoints.SettingsEndpoint
import com.cellarhq.endpoints.YourCellarEndpoint
import com.cellarhq.endpoints.api.CellarEndpoint
import com.cellarhq.endpoints.api.CellaredDrinkEndpoint
import com.cellarhq.endpoints.auth.*
import com.cellarhq.health.DatabaseHealthcheck
import com.cellarhq.services.CellarService
import com.cellarhq.services.CellaredDrinkService
import com.cellarhq.services.StatsService
import com.cellarhq.util.SessionUtil
import com.codahale.metrics.health.HealthCheckRegistry
import org.pac4j.core.profile.CommonProfile
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
        add new CellarHQModule(
                getConfig(launchConfig, 'other.aws.accessKey', 'AKIAIXBP2ORLESIX5CIQ'),
                getConfig(launchConfig, 'other.aws.secretKey', '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')
        )

        init {
            RxRatpack.initialize()
        }
    }

    handlers {
        get { StatsService statsService ->
            if (SessionUtil.isLoggedIn(request.maybeGet(CommonProfile))) {
                redirect(302, '/cellars')
            } else {
                statsService.homepageStatistics().single().subscribe { HomepageStatistics stats ->
                    render handlebarsTemplate('index.html',
                            stats: stats,
                            action: '/register',
                            title: 'CellarHQ',
                            pageId: 'home')
                }
            }
        }

        handler chain(registry.get(BreweryEndpoint))
        handler chain(registry.get(CellarsEndpoint))

        /**
         * Alias to /cellars/:id, auto-loads authenticated user.
         */
        get('yourcellar', registry.get(YourCellarEndpoint))

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

        get('about') {
            render handlebarsTemplate('about.html',
                    title: 'About',
                    pageId: 'about')
        }
        get('privacy-policy') {
            render handlebarsTemplate('privacy.html',
                    title: 'Privacy Policy',
                    pageId: 'privacy')
        }
        get('terms-of-service') {
            // TODO: The following articles still need to be written (once features for them have been completed).
            // Add API article
            // Add Cancellation and Termination article
            render handlebarsTemplate('terms-of-service.html',
                    title: 'Terms of Service',
                    pageId: 'terms')
        }

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

        prefix('api') {
            handler('cellars/:slug?', registry.get(CellarEndpoint))
            get('cellars') { CellarService cellarService ->
                cellarService.all().toList().subscribe { List<Cellar> cellar ->
                    render json(cellar)
                }
            }

            handler('cellars/:cellarSlug/drinks/:id?', registry.get(CellaredDrinkEndpoint))
            get('cellars/:cellarSlug/drinks') { CellaredDrinkService cellaredDrinkService ->
                cellaredDrinkService.all(pathTokens['cellarSlug']).toList().subscribe { List<CellaredDrink> cellaredDrinks ->
                    render json(cellaredDrinks)
                }
            }

            handler chain(registry.get(OrganizationEndpoint))
            handler chain(registry.get(DrinkEndpoint))
        }

        get('health-checks', { HealthCheckRegistry healthCheckRegistry ->
            render json(healthCheckRegistry.runHealthChecks())
        })

        assets "public"
    }
}
