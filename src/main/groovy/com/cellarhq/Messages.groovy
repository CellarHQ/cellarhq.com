package com.cellarhq

import groovy.transform.CompileStatic

/**
 * Global error message constants.
 */
@SuppressWarnings('LineLength')
@CompileStatic
abstract class Messages {
    final static String UNEXPECTED_SERVER_ERROR = 'Oh no! Something unexpected happened; try again or let us know.'
    final static String FORM_VALIDATION_ERROR = 'Whoops! Looks like you might need to fix something.'
    final static String UNAUTHORIZED_ERROR = "Tsk tsk. That's not something you're allowed to do. Try logging in with more power."

    final static String FORGOT_PASSWORD_ERROR = "Bummer. We couldn't find an account associated with that email; try again or let us know."
    final static String FORGOT_PASSWORD_EMAIL_SENT_NOTICE = "Check your email! We've sent you a link to change your password"
    final static String FORGOT_PASSWORD_UNKNOWN_REQUEST = "Hmm. We couldn't find your password recovery request. Did it expire - you only have 24 hours; try again or let us know."
    final static String FORGOT_PASSWORD_LOGIN_WITH_NEW_PASSWORD = "Lookin' good. You can now login with your new password."
}
