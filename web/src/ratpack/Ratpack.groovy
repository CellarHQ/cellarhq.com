import com.cellarhq.ForceSslHandler
import com.cellarhq.HomepageHandler
import com.cellarhq.StaticPageChain
import com.cellarhq.UserProfileHandler
import com.cellarhq.api.ApiModule
import com.cellarhq.api.CellarChain
import com.cellarhq.api.CellaredDrinkChain
import com.cellarhq.api.DrinkChain
import com.cellarhq.api.GlasswareChain
import com.cellarhq.api.OrganizationChain
import com.cellarhq.api.StyleChain
import com.cellarhq.auth.AuthenticationModule
import com.cellarhq.auth.RequireAuthHandler
import com.cellarhq.auth.endpoints.AuthenticationChain
import com.cellarhq.auth.endpoints.LinkAccountChain
import com.cellarhq.auth.endpoints.LinkEmailAccountChain
import com.cellarhq.auth.endpoints.LinkTwitterAccountChain
import com.cellarhq.auth.endpoints.SettingsHandler
import com.cellarhq.common.CellarHQConfig
import com.cellarhq.common.ClientErrorHandlerImpl
import com.cellarhq.common.CommonModule
import com.cellarhq.common.ServerErrorHandlerImpl
import com.cellarhq.webapp.BeerHtmlChain
import com.cellarhq.webapp.BreweryChain
import com.cellarhq.webapp.CellarsChain
import com.cellarhq.webapp.WebappModule
import com.cellarhq.webapp.YourCellarChain
import com.zaxxer.hikari.HikariConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ratpack.error.ClientErrorHandler
import ratpack.error.ServerErrorHandler
import ratpack.handlebars.HandlebarsModule
import ratpack.handling.RequestLogger
import ratpack.hikari.HikariModule
import ratpack.server.BaseDir
import ratpack.session.SessionModule
import ratpack.session.clientside.ClientSideSessionConfig
import ratpack.session.clientside.ClientSideSessionModule
import util.HerokuUtils

import java.time.Duration

import static ratpack.groovy.Groovy.ratpack

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
    all(RequestLogger.ncsa(log))
    all(ForceSslHandler)
    all(UserProfileHandler)
    insert(AuthenticationChain)
    get(HomepageHandler)
    insert(StaticPageChain)
    insert(CellarChain)
    insert(BreweryChain)
    insert(BeerHtmlChain)
    insert(CellarsChain)

    // everything after this requires login
    all(RequireAuthHandler)

    path('settings', SettingsHandler)
    insert(YourCellarChain)
    insert(LinkEmailAccountChain)
    insert(LinkTwitterAccountChain)
    insert(LinkAccountChain)
    prefix('api') {
      insert(CellaredDrinkChain)
      insert(OrganizationChain)
      insert(DrinkChain)
      insert(StyleChain)
      insert(GlasswareChain)
    }
  }
}
