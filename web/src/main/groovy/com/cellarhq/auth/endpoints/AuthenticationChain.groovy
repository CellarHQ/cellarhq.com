package com.cellarhq.auth.endpoints

import com.cellarhq.auth.CustomTwitterClient
import org.pac4j.http.client.indirect.FormClient
import org.pac4j.oauth.client.TwitterClient
import ratpack.func.Action
import ratpack.handling.Chain
import ratpack.handling.Context
import ratpack.pac4j.RatpackPac4j

import static ratpack.handlebars.Template.handlebarsTemplate

// TODO: javafy
class AuthenticationChain implements Action<Chain> {
  @Override
  void execute(Chain chain) throws Exception {
    chain.all(RatpackPac4j.authenticator(chain.registry.get(FormClient), chain.registry.get(CustomTwitterClient)))
         .get("twitterLogin") { ctx ->
      RatpackPac4j.login(ctx, TwitterClient).then {
        ctx.redirect(302, "/auth")
      }
    }.get('logout') { Context ctx ->
      RatpackPac4j.logout(ctx).then {
        ctx.redirect(302, '/')
      }
    }.path('register', RegisterHandler)
         .get('login') { ctx ->
      ctx.render handlebarsTemplate('login.html',
        callBackUrl: ctx.get(FormClient).callbackUrl,
        title: 'Login',
        pageId: 'login')
    }
         .path('forgot-password', chain.registry.get(ForgotPasswordHandler))
         .path('forgot-password/:id', chain.registry.get(ChangePasswordHandler))

  }
}
