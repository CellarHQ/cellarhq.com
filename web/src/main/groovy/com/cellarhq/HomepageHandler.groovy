package com.cellarhq

import com.cellarhq.auth.profiles.CellarHQProfile
import com.cellarhq.domain.views.HomepageStatistics
import com.cellarhq.webapp.StatsService
import com.google.inject.Inject
import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.pac4j.RatpackPac4j

import static ratpack.handlebars.Template.handlebarsTemplate

class HomepageHandler implements Handler {

  StatsService statsService

  @Inject
  HomepageHandler(StatsService statsService) {
    this.statsService = statsService
  }

  @Override
  void handle(Context ctx) throws Exception {
    RatpackPac4j.userProfile(ctx).then { Optional<CellarHQProfile> profile ->
      if (profile.isPresent()) {
        ctx.redirect(302, '/yourcellar')
      } else {
        statsService.homepageStatistics().then { HomepageStatistics stats ->
          ctx.render handlebarsTemplate('' +
            'index.html',
            stats: stats,
            action: '/register',
            title: 'CellarHQ',
            pageId: 'home')
        }
      }
    }
  }
}
