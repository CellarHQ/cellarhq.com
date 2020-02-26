package com.cellarhq.api

import com.cellarhq.api.services.StyleService
import com.cellarhq.domain.Style
import com.google.inject.Inject
import ratpack.func.Action
import ratpack.handling.Chain

import static ratpack.jackson.Jackson.json

// TODO: javafy
class StyleChain implements Action<Chain> {

  private final StyleService styleService

  @Inject
  StyleChain(StyleService styleService) {
    this.styleService = styleService
  }

  @Override
  void execute(Chain chain) throws Exception {
    chain
    .get('styles/live-search') { ctx ->
      styleService.search(ctx.request.queryParams.name, 20, 0).then { List<Style> styles ->
        ctx.render json(styles)
      }
    }
    .put('styles/validate-name') { ctx ->
      styleService.search(ctx.request.queryParams.name, 1, 0).then { List<Style> styles ->
        if (ctx.request.queryParams.exists) {
          ctx.render json(!styles.empty)
        } else {
          ctx.render json(styles.empty)
        }
      }
    }
  }
}
