package com.cellarhq.auth.callbacks

import com.cellarhq.auth.RememberMeFlag
import com.cellarhq.auth.AuthenticationModule
import com.cellarhq.domain.Cellar
import com.cellarhq.auth.rememberme.RememberMeService
import groovy.transform.CompileStatic
import org.pac4j.core.profile.CommonProfile
import ratpack.form.Form
import ratpack.handling.Context
import ratpack.http.Request
import ratpack.session.store.SessionStorage

@CompileStatic
class DefaultSuccessCallback<C extends Context, P extends CommonProfile> {

    protected final static String USER_PROFILE = 'ratpack.pac4j-user-profile'
    protected final static String SAVED_URI = 'ratpack.pac4j-saved-uri'
    protected final static String DEFAULT_REDIRECT_URI = '/yourcellar'

    RememberMeService rememberMeService

    DefaultSuccessCallback(RememberMeService rememberMeService) {
        this.rememberMeService = rememberMeService
    }

    void defaultBehavior(C context, P profile, Cellar cellar) {
        Request request = context.request
        SessionStorage sessionStorage = request.get(SessionStorage)
        if (profile) {
            sessionStorage.put(USER_PROFILE, profile)
        }

        if (cellar) {
            sessionStorage.put(AuthenticationModule.SESSION_CELLAR, cellar)
        }

        String originalUri = (String) sessionStorage.remove(SAVED_URI)
        if (!originalUri) {
            originalUri = DEFAULT_REDIRECT_URI
        }

        boolean shouldRememberMe = false
        if (context.request.headers['content-type'] == 'application/x-www-form-urlencoded') {
            final Form FORM = context.parse(Form)
            shouldRememberMe = FORM.get('remember-me')
        }

        RememberMeFlag rememberMe = new RememberMeFlag(rememberMe: shouldRememberMe as boolean)

        rememberMeService.onLoginSuccess(context.request, context.response, rememberMe, profile)
        context.redirect(originalUri)
    }
}
