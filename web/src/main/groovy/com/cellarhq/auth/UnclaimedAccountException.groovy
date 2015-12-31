package com.cellarhq.auth

import groovy.transform.CompileStatic

/**
 * This exception is thrown when checking an email account's password and it matches the "unclaimed" token, meaning
 * the account has not had its password reset since the migration to CellarHQ 2.0.
 */
@CompileStatic
class UnclaimedAccountException extends RuntimeException {

  static final String UNCLAIMED_MESSAGE = 'The password on this account must be reset: Use the Forgot Password page.'

  UnclaimedAccountException() {
    super(UNCLAIMED_MESSAGE)
  }
}
