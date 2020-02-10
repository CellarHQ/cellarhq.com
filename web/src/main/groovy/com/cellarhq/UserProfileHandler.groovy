package com.cellarhq

import com.cellarhq.auth.profiles.CellarHQProfile
import org.pac4j.core.profile.UserProfile
import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.pac4j.RatpackPac4j

class UserProfileHandler implements Handler {
  @Override
  void handle(Context ctx) throws Exception {
    RatpackPac4j.userProfile(ctx).then { Optional<UserProfile> opUp ->
      if (opUp.isPresent()) {
        UserProfile userProfile = opUp.get()
        CellarHQProfile cellarHQProfile = new CellarHQProfile()
        cellarHQProfile.cellarId = userProfile.getAttribute("CELLARID")
        ctx.next(ctx.single(cellarHQProfile))
      } else {
        ctx.next()
      }
    }

  }
}
