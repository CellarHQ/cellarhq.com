package com.cellarhq.util

import com.cellarhq.session.FlashMessage
import groovy.transform.CompileStatic
import org.pac4j.core.profile.CommonProfile
import ratpack.http.Request
import ratpack.session.store.SessionStorage

/**
 * Provides utility functions for session operations.
 */
@CompileStatic
abstract class SessionUtil {

    static boolean isLoggedIn(CommonProfile profile) {
        return profile?.username != null
    }

    // There's probably a better way, via implementing our own SessionManager, but I don't want to do that until we're
    // getting in and creating a cookie session store as well. Would be super nice to just "session.setFlash('blah')"

    static void setFlash(Request request, FlashMessage flashMessage) {
        SessionStorage session = request.get(SessionStorage)
        session.put(flashMessage.sessionKey, flashMessage)
    }
}
