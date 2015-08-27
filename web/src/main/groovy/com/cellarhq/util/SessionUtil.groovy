package com.cellarhq.util

import com.cellarhq.common.session.FlashMessage
import groovy.util.logging.Slf4j
import ratpack.handling.Context
import ratpack.session.Session

/**
 * Provides utility functions for session operations.
 */
@Slf4j
abstract class SessionUtil {

  // There's probably a better way, via implementing our own SessionManager, but I don't want to do that until we're
  // getting in and creating a cookie session store as well. Would be super nice to just "session.setFlash('blah')"

  static void setFlash(Context context, FlashMessage flashMessage) {
    context.get(Session).getData().then { sessionData ->
      log.debug("Setting flash ${flashMessage.sessionKey} - ${flashMessage.message}")
      sessionData.set(flashMessage.sessionKey, flashMessage)
    }
  }
}
