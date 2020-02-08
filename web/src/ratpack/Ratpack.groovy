import com.cellarhq.api.ApiModule
import com.cellarhq.api.CellarEndpoint
import com.cellarhq.api.CellaredDrinkEndpoint
import com.cellarhq.api.DrinkEndpoint
import com.cellarhq.api.GlasswareEndpoint
import com.cellarhq.api.OrganizationEndpoint
import com.cellarhq.api.StyleEndpoint
import com.cellarhq.auth.AuthenticationModule
import com.cellarhq.auth.CustomTwitterClient
import com.cellarhq.auth.endpoints.ChangePasswordEndpoint
import com.cellarhq.auth.endpoints.ForgotPasswordEndpoint
import com.cellarhq.auth.endpoints.LinkAccountEndpoint
import com.cellarhq.auth.endpoints.LinkEmailAccountEndpoint
import com.cellarhq.auth.endpoints.LinkTwitterAccountEndpoint
import com.cellarhq.auth.endpoints.RegisterEndpoint
import com.cellarhq.auth.endpoints.SettingsEndpoint
import com.cellarhq.auth.profiles.CellarHQProfile
import com.cellarhq.common.CellarHQConfig
import com.cellarhq.common.ClientErrorHandlerImpl
import com.cellarhq.common.CommonModule
import com.cellarhq.common.ServerErrorHandlerImpl
import com.cellarhq.domain.views.HomepageStatistics
import com.cellarhq.webapp.BeerHtmlChain
import com.cellarhq.webapp.BreweryChain
import com.cellarhq.webapp.CellarsEndpoint
import com.cellarhq.webapp.StatsService
import com.cellarhq.webapp.WebappModule
import com.cellarhq.webapp.YourCellarEndpoint
import com.zaxxer.hikari.HikariConfig
import org.pac4j.core.profile.UserProfile
import org.pac4j.http.client.indirect.FormClient
import org.pac4j.oauth.client.TwitterClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ratpack.error.ClientErrorHandler
import ratpack.error.ServerErrorHandler
import ratpack.handlebars.HandlebarsModule
import ratpack.handling.Context
import ratpack.handling.RequestLogger
import ratpack.hikari.HikariModule
import ratpack.pac4j.RatpackPac4j
import ratpack.server.BaseDir
import ratpack.session.SessionModule
import ratpack.session.clientside.ClientSideSessionConfig
import ratpack.session.clientside.ClientSideSessionModule
import util.HerokuUtils

import java.time.Duration

import static ratpack.groovy.Groovy.ratpack
import static ratpack.handlebars.Template.handlebarsTemplate

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
      .args(programArgs.stream().toArray() as String[])
      .require("/cellarhq", CellarHQConfig)
      .require("/cookie", ClientSideSessionConfig)
  }

  bindings {
    Map hikariConfigProperties = serverConfig.get("/db", Map)
    moduleConfig(HikariModule, new HikariConfig(hikariConfigProperties))

    module CommonModule
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

    bind ServerErrorHandler, ServerErrorHandlerImpl
    bind ClientErrorHandler, ClientErrorHandlerImpl
  }

  handlers {
    all RequestLogger.ncsa(log)

    insert(registry.get(CellarEndpoint))


    all({ ctx ->
      RatpackPac4j.userProfile(ctx).then { Optional<UserProfile> opUp ->
        if (opUp.isPresent()) {
          UserProfile userProfile = opUp.get()
          CellarHQProfile cellarHQProfile = new CellarHQProfile()
          cellarHQProfile.cellarId = userProfile.getAttribute("CELLARID")
          ctx.next(single(cellarHQProfile))
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

    all(RatpackPac4j.authenticator(registry.get(FormClient), registry.get(CustomTwitterClient)))

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


    get { Context ctx, StatsService statsService ->
      RatpackPac4j.userProfile(ctx).then { Optional<CellarHQProfile> profile ->
        if (profile.isPresent()) {
          ctx.redirect(302, '/yourcellar')
        } else {
          statsService.homepageStatistics().then { HomepageStatistics stats ->
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

    insert BreweryChain
    insert BeerHtmlChain
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
