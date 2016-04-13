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
import librato.HerokuLibratoConfigUtility
import librato.LibratoConfig
import librato.LibratoModule
import org.pac4j.http.client.indirect.FormClient
import org.pac4j.oauth.client.TwitterClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ratpack.dropwizard.metrics.DropwizardMetricsConfig
import ratpack.dropwizard.metrics.DropwizardMetricsModule
import ratpack.error.ClientErrorHandler
import ratpack.error.ServerErrorHandler
import ratpack.handlebars.HandlebarsModule
import ratpack.handling.Context
import ratpack.handling.RequestLogger
import ratpack.hikari.HikariModule
import ratpack.pac4j.RatpackPac4j
import ratpack.rx.RxRatpack
import ratpack.server.BaseDir
import ratpack.server.Service
import ratpack.server.StartEvent
import ratpack.session.SessionModule
import ratpack.session.clientside.ClientSideSessionConfig
import ratpack.session.clientside.ClientSideSessionModule
import util.HerokuUtils

import java.time.Duration

import static ratpack.groovy.Groovy.ratpack
import static ratpack.handlebars.Template.handlebarsTemplate
import static ratpack.jackson.Jackson.json

final Logger log = LoggerFactory.getLogger(this.class)

ratpack {
  List<String> programArgs = HerokuUtils.extractDbProperties
    .apply(System.getenv("DATABASE_URL"))

  serverConfig {
    config -> config
      .baseDir(BaseDir.find())
      .props("app.properties")
      .yaml("db.yaml")
      .env()
      .sysProps()
      .props(HerokuLibratoConfigUtility.libratoProperties)
      .args(programArgs.stream().toArray() as String[])
      .require("/cellarhq", CellarHQConfig)
      .require("/db", HikariConfig)
      .require("/metrics", DropwizardMetricsConfig)
      .require("/cookie", ClientSideSessionConfig)
      .require("/librato", LibratoConfig)
  }

  bindings {
    module CommonModule
    module HikariModule
    module AuthenticationModule
    module CommonModule
    module ApiModule
    module WebappModule
    module HandlebarsModule
    module SessionModule, { c->
      c.expires(Duration.ofDays(30))
    }
    module ClientSideSessionModule, { c ->
      c.setMaxInactivityInterval(Duration.ofDays(30))
    }

    module DropwizardMetricsModule
    module LibratoModule

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
      } else {
        next()
      }
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
      log.warn('backwards-compatible-url=signup')
      redirect(301, '/register')
    }
    path('signin') {
      log.warn('backwards-compatible-url=signin')
      redirect(301, '/login')
    }
    path('forgotpassword') {
      log.warn('backwards-compatible-url=forgotpassword')
      redirect(301, '/forgot-password')
    }
    path('cellar/:slug') {
      log.warn('backwards-compatible-url=cellar')
      redirect(301, "/cellars/${pathTokens['slug']}")
    }
    path('brewery/:slug') {
      log.warn('backwards-compatible-url=brewery')
      redirect(301, "/breweries/${pathTokens['slug']}")
    }
    path('brewery/:brewery/beer/:beer') {
      log.warn('backwards-compatible-url=beer')
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
