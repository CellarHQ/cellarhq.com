import com.cellarhq.CellarHQModule
import com.cellarhq.ClientErrorHandlerImpl
import com.cellarhq.ServerErrorHandlerImpl
import com.cellarhq.auth.SecurityModule
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.views.HomepageStatistics
import com.cellarhq.endpoints.*
import com.cellarhq.endpoints.api.CellarEndpoint
import com.cellarhq.endpoints.api.CellaredDrinkEndpoint
import com.cellarhq.endpoints.api.DrinkEndpoint
import com.cellarhq.endpoints.api.GlasswareEndpoint
import com.cellarhq.endpoints.api.OrganizationEndpoint
import com.cellarhq.endpoints.api.StyleEndpoint
import com.cellarhq.endpoints.auth.*
import com.cellarhq.health.DatabaseHealthcheck
import com.cellarhq.services.CellarService
import com.cellarhq.services.StatsService
import com.cellarhq.util.SessionUtil
import com.codahale.metrics.health.HealthCheckRegistry
import org.pac4j.core.profile.CommonProfile
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
import static ratpack.jackson.Jackson.json

String getConfig(LaunchConfig launchConfig, String key, String defaultValue) {
    String value = System.getenv(key)
    if (value == null) {
        value = System.getProperty(key, defaultValue)
        value = launchConfig.getOther(key, value)
    }

    return value
}

ratpack {
    bindings {
        //add new CodaHaleMetricsModule().metrics().jvmMetrics().jmx().websocket().healthChecks()

        bind ServerErrorHandler, ServerErrorHandlerImpl
        bind ClientErrorHandler, ClientErrorHandlerImpl
        bind DatabaseHealthcheck

        String serverName = getConfig(launchConfig, 'dataSource.serverName', 'localhost')
        String portNumber =  getConfig(launchConfig, 'dataSource.portNumber', '15432')
        String databaseName = getConfig(launchConfig, 'dataSource.databaseName', 'cellarhq')
        String user = getConfig(launchConfig, 'dataSource.user', 'cellarhq')
        String password = getConfig(launchConfig, 'dataSource.password', 'cellarhq')

        add HikariModule, { hikariConfig ->
            hikariConfig.addDataSourceProperty('serverName', serverName)
            hikariConfig.addDataSourceProperty('portNumber', portNumber)
            hikariConfig.addDataSourceProperty('databaseName', databaseName)
            hikariConfig.addDataSourceProperty('user', user)
            hikariConfig.addDataSourceProperty('password', password)
            hikariConfig.dataSourceClassName = 'org.postgresql.ds.PGSimpleDataSource'
        }

        add new JacksonModule()
        add new RemoteControlModule()

        add new SessionModule()
        add new MapSessionsModule(500, 60)
        add new SecurityModule()

        add new HandlebarsModule()

        // IMPORTANT: Our module must be last, so we can override whatever we need to created by the other modules.
        // TODO: Remove AWS keys from codebase, generate new ones and inject via env vars.
        add new CellarHQModule(
                getConfig(launchConfig, 'other.aws.accessKey', 'AKIAJE3VS4JUNXZODG2Q'),
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
        handler chain(registry.get(BeerEndpoint))
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
            request.get(SessionStorage).remove(SecurityModule.SESSION_CELLAR_ID)
            redirect('/')
        }

        handler('forgot-password', chain(registry.get(ForgotPasswordEndpoint)))
        handler('forgot-password/:id', registry.get(ChangePasswordEndpoint))
        handler('settings', chain(registry.get(SettingsEndpoint)))

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
        handler('cellar/:slug') {
            redirect(301, "/cellars/${pathTokens['slug']}")
        }
        handler('brewery/:slug') {
            redirect(301, "/breweries/${pathTokens['slug']}")
        }
        handler('brewery/:brewery/beer/:beer') {
            redirect(301, "/breweries${pathTokens['brewery']}/beers/${pathTokens['beer']}")
        }

        /**************************************************************************************************************
         * API
         */

        prefix('api') {
            handler chain(registry.get(CellarEndpoint))
            handler chain(registry.get(CellaredDrinkEndpoint))
            handler chain(registry.get(OrganizationEndpoint))
            handler chain(registry.get(DrinkEndpoint))
            handler chain(registry.get(StyleEndpoint))
            handler chain(registry.get(GlasswareEndpoint))
        }

        get('health-checks', { HealthCheckRegistry healthCheckRegistry ->
            render json(healthCheckRegistry.runHealthChecks())
        })

//        prefix("admin") {
//            get("metrics-report", new MetricsWebsocketBroadcastHandler())
//
//            get("metrics") {
//                render handlebarsTemplate("metrics.html", title: "Metrics")
//            }
//        }

        assets "public"
    }
}
