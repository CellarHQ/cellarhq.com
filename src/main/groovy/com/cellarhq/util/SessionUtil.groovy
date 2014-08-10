package com.cellarhq.util

import groovy.transform.CompileStatic
import org.pac4j.core.profile.CommonProfile

/**
 * Provides utility functions for session operations.
 */
@CompileStatic
abstract class SessionUtil {

    static boolean isLoggedIn(CommonProfile profile) {
        return profile?.username != null
    }
}
