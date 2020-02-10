package com.cellarhq

import com.cellarhq.common.CellarHQConfig
import ratpack.handling.Context
import ratpack.handling.Handler

class ForceSslHandler implements Handler {
  @Override
  void handle(Context ctx) throws Exception {
    // For production, we want to force SSL on all requests.
    String forwardedProto = 'X-Forwarded-Proto'
    if (ctx.get(CellarHQConfig).isProductionEnv()
      && ctx.request.headers.contains(forwardedProto)
      && ctx.request.headers.get(forwardedProto) != 'https') {
      ctx.redirect(301, "https://${ctx.request.headers.get('Host')}${ctx.request.rawUri}")
    } else {
      ctx.next()
    }

  }
}
