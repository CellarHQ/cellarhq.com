package com.cellarhq.util

import groovy.transform.CompileStatic
import org.pac4j.core.profile.CommonProfile
import ratpack.http.Request
import ratpack.session.store.SessionStorage

/**
 * Provides utility functions for session operations.
 */
@CompileStatic
abstract class SessionUtil {

    private final static String FLASH_MESSAGES = 'flash'

    static boolean isLoggedIn(CommonProfile profile) {
        return profile?.username != null
    }

    // There's probably a better way, via implementing our own SessionManager, but I don't want to do that until we're
    // getting in and creating a cookie session store as well. Would be super nice to just "session.setFlash('blah')"

    static void setFlashMessages(Request request, List<String> messages) {
        SessionStorage session = request.get(SessionStorage)
        session.put(FLASH_MESSAGES, messages)
    }

    static List<String> getFlashMessages(Request request) {
        SessionStorage session = request.get(SessionStorage)
        List<String> messages = (List<String>) session.getOrDefault(FLASH_MESSAGES, [])
        session.put(FLASH_MESSAGES, [])
        return messages
    }
}
