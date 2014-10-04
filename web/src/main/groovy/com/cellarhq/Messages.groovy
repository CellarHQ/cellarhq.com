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

    final static String AUTH_NO_CREDENTIALS = 'No credentials'
    final static String AUTH_CREDENTIALS_DO_NOT_MATCH = 'Email and/or password did not match'

    final static String REGISTER_SCREEN_NAME_TAKEN = 'Bummer. That username is already taken, try something else.'

    final static String SETTINGS_SAVED = "Lookin' good. We've updated your profile."

    final static String CELLARED_DRINK_SAVED = "Lookin' good. We've saved your drink."
    final static String CELLARED_DRINK_SAVED_SOCIAL = 'Added %1s by %2s to my beer cellar!'
}
