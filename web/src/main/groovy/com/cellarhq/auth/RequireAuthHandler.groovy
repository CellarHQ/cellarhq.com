package com.cellarhq.auth

import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.pac4j.RatpackPac4j

class RequireAuthHandler implements Handler {
  @Override
  void handle(Context ctx) throws Exception {
    RatpackPac4j.userProfile(ctx).then { opUp ->
      if (!opUp.isPresent()) {
        ctx.redirect(302, "/login")
      } else {
        ctx.next()
      }
    }
  }
}
