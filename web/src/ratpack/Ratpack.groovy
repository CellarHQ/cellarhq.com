import com.cellarhq.api.*
import com.cellarhq.auth.AuthenticationModule
import com.cellarhq.auth.endpoints.*
import com.cellarhq.auth.profiles.CellarHQProfile
import com.cellarhq.common.CellarHQConfig
import com.cellarhq.common.ClientErrorHandlerImpl
import com.cellarhq.common.CommonModule
import com.cellarhq.common.ServerErrorHandlerImpl
import com.cellarhq.domain.views.HomepageStatistics
import com.cellarhq.health.DatabaseHealthcheck
import com.cellarhq.webapp.*
import com.codahale.metrics.health.HealthCheckRegistry
import com.zaxxer.hikari.HikariConfig
import org.pac4j.http.client.FormClient
import org.pac4j.oauth.client.TwitterClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ratpack.config.ConfigData
import ratpack.error.ClientErrorHandler
import ratpack.error.ServerErrorHandler
import ratpack.handlebars.HandlebarsModule
import ratpack.handling.Context
import ratpack.handling.RequestLogger
import ratpack.hikari.HikariModule
import ratpack.pac4j.RatpackPac4j
import ratpack.rx.RxRatpack
import ratpack.server.Service
import ratpack.server.StartEvent
import ratpack.session.SessionModule
import ratpack.session.clientside.ClientSideSessionModule

import static ratpack.groovy.Groovy.ratpack
import static ratpack.handlebars.Template.handlebarsTemplate
import static ratpack.jackson.Jackson.json

final Logger log = LoggerFactory.getLogger(this.class)

ratpack {
  bindings {
    String workingDirectory = System.getProperty("user.dir")

    log.info("${workingDirectory}/app.properties")
    ConfigData configData = ConfigData.of { d ->
      d
        .props("${workingDirectory}/app.properties")
        .env()
        .sysProps()
    }

    CellarHQConfig cellarHqConfig = configData.get(CellarHQConfig)
    bindInstance(CellarHQConfig, cellarHqConfig)
    moduleConfig(CommonModule, cellarHqConfig)

    module(HikariModule) { HikariConfig hikariConfig ->
      hikariConfig.addDataSourceProperty('serverName', cellarHqConfig.databaseServerName)
      hikariConfig.addDataSourceProperty('portNumber', cellarHqConfig.databasePortNumber)
      hikariConfig.addDataSourceProperty('databaseName', cellarHqConfig.databaseName)
      hikariConfig.addDataSourceProperty('user', cellarHqConfig.databaseUser)
      hikariConfig.addDataSourceProperty('password', cellarHqConfig.databasePassword)
      hikariConfig.dataSourceClassName = 'org.postgresql.ds.PGSimpleDataSource'
    }

    moduleConfig(CommonModule, cellarHqConfig)
    module AuthenticationModule
    module CommonModule
    module ApiModule
    module WebappModule
    module HandlebarsModule
    module SessionModule
    module(ClientSideSessionModule, { config ->
      config.setSessionCookieName("cellarhq_session");
      config.setSecretToken("your token for signing");
    })

    bindInstance Service, new Service() {
      @Override
      void onStart(StartEvent event) throws Exception {
        RxRatpack.initialize()
      }
    }

    bind ServerErrorHandler, ServerErrorHandlerImpl
    bind ClientErrorHandler, ClientErrorHandlerImpl
    bind DatabaseHealthcheck
  }

  handlers {
    all RequestLogger.ncsa(log)

    insert(registry.get(CellarEndpoint))


    all({ ctx ->
      RatpackPac4j.userProfile(ctx).then { opUp ->
        if (opUp.isPresent()) {
          ctx.next(single(opUp.get()))
        } else {
          ctx.next()
        }
      }
    })

    all { CellarHQConfig cellarHQConfig ->
      // For production, we want to force SSL on all requests.
      String forwardedProto = 'X-Forwarded-Proto'
      if (cellarHQConfig.isProductionEnv()
        && request.headers.contains(forwardedProto)
        && request.headers.get(forwardedProto) != 'https') {
        redirect(301, "https://${request.headers.get('Host')}${request.rawUri}")
      }
      next()
    }

    all(RatpackPac4j.authenticator(registry.get(FormClient), registry.get(TwitterClient)))

    get("twitterLogin") { ctx ->
      RatpackPac4j.login(ctx, TwitterClient).then {
        ctx.redirect(302, "/auth")
      }
    }

    get('logout') { Context ctx ->
      RatpackPac4j.logout(ctx).then {
        ctx.redirect(302, '/')
      }
    }

    fileSystem("public") { f ->
      f.files()
    }

    get('health-checks', { HealthCheckRegistry healthCheckRegistry ->
      render json(healthCheckRegistry.runHealthChecks())
    })


    get { Context ctx, StatsService statsService ->
      RatpackPac4j.userProfile(ctx).then { Optional<CellarHQProfile> profile ->
        if (profile.isPresent()) {
          ctx.redirect(302, '/yourcellar')
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

    path('register', RegisterEndpoint)

    get('login') {
      render handlebarsTemplate('login.html',
        callBackUrl: registry.get(FormClient).callbackUrl,
        title: 'Login',
        pageId: 'login')
    }

    path('forgot-password', registry.get(ForgotPasswordEndpoint))
    path('forgot-password/:id', registry.get(ChangePasswordEndpoint))

    /**
     * Backwards compatibility endpoints:
     */
    path('signup') {
      redirect(301, '/register')
    }
    path('signin') {
      redirect(301, '/login')
    }
    path('forgotpassword') {
      redirect(301, '/forgot-password')
    }
    path('cellar/:slug') {
      redirect(301, "/cellars/${pathTokens['slug']}")
    }
    path('brewery/:slug') {
      redirect(301, "/breweries/${pathTokens['slug']}")
    }
    path('brewery/:brewery/beer/:beer') {
      redirect(301, "/breweries/${pathTokens['brewery']}/beers/${pathTokens['beer']}")
    }

    insert BreweryEndpoint
    insert BeerEndpoint
    insert CellarsEndpoint

    // everything after this requires login
    all({ ctx ->
      RatpackPac4j.userProfile(ctx).then { opUp ->
        if (!opUp.isPresent()) {
          ctx.redirect(302, "/login")
        } else {
          ctx.next()
        }
      }
    })

    /**
     * Alias to /cellars/:id, auto-loads authenticated user.
     */
    path('settings', SettingsEndpoint)
    insert YourCellarEndpoint
    insert LinkEmailAccountEndpoint
    insert LinkTwitterAccountEndpoint
    insert LinkAccountEndpoint

    /**************************************************************************************************************
     * API
     */

    prefix('api') {
      insert(registry.get(CellaredDrinkEndpoint))
      insert(registry.get(OrganizationEndpoint))
      insert(registry.get(DrinkEndpoint))
      insert(registry.get(StyleEndpoint))
      insert(registry.get(GlasswareEndpoint))
    }
  }
}
