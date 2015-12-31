package com.cellarhq.auth

import groovy.transform.CompileStatic

/**
 * Thrown when a user request exceeds their privileges.
 */
@CompileStatic
class AccessControlException extends RuntimeException {

  AccessControlException(String message) {
    super(message)
  }
}
