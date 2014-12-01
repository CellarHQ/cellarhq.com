package com.cellarhq.auth.callbacks

import com.cellarhq.auth.SecurityModule
import com.cellarhq.domain.Cellar
import groovy.transform.CompileStatic
import org.pac4j.core.profile.UserProfile
import ratpack.handling.Context
import ratpack.http.Request
import ratpack.session.store.SessionStorage

@CompileStatic
class DefaultSuccessCallback<C extends Context, P extends UserProfile> {

    protected final static String USER_PROFILE = 'ratpack.pac4j-user-profile'
    protected final static String SAVED_URI = 'ratpack.pac4j-saved-uri'
    protected final static String DEFAULT_REDIRECT_URI = '/yourcellar'

    void defaultBehavior(C context, P profile, Cellar cellar) {
        Request request = context.request
        SessionStorage sessionStorage = request.get(SessionStorage)
        if (profile) {
            sessionStorage.put(USER_PROFILE, profile)
        }

        if (cellar) {
            sessionStorage.put(SecurityModule.SESSION_CELLAR, cellar)
        }

        String originalUri = (String) sessionStorage.remove(SAVED_URI)
        if (!originalUri) {
            originalUri = DEFAULT_REDIRECT_URI
        }
        context.redirect(originalUri)
    }
}
