package com.cellarhq.util

import com.cellarhq.common.session.FlashMessage
import groovy.util.logging.Slf4j
import ratpack.exec.Operation
import ratpack.handling.Context
import ratpack.session.Session

/**
 * Provides utility functions for session operations.
 */
@Slf4j
abstract class SessionUtil {
  static Operation setFlash(Context context, FlashMessage flashMessage) {
    context.get(Session).set(flashMessage.sessionKey, flashMessage)
  }
}
