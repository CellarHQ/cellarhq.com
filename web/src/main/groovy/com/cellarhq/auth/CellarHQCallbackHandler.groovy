package com.cellarhq.auth

import com.cellarhq.session.FlashMessage
import com.cellarhq.util.SessionUtil
import groovy.transform.CompileStatic
import org.pac4j.core.client.Client
import org.pac4j.core.client.Clients
import org.pac4j.core.credentials.Credentials
import org.pac4j.core.exception.RequiresHttpAction
import org.pac4j.core.exception.TechnicalException
import org.pac4j.core.profile.UserProfile
import org.pac4j.http.profile.HttpProfile
import org.pac4j.oauth.profile.twitter.TwitterProfile
import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.http.Request
import ratpack.pac4j.internal.RatpackWebContext
import ratpack.session.store.SessionStorage

/**
 * It looks like our auth endpoints don't get called when the user explicitly tries to login (as opposed to being
 * redirected to the login pages). This handler is exactly mostly the same as Pac4jCallbackHandler, except that it
 * takes the client into consideration when doing the final redirect.
 *
 * Without the requests being passed to our "auth-*" endpoints, a user is never completely logged in, or in the case
 * of Twitter, never completely registered.
 */
@SuppressWarnings('VariableName')
@CompileStatic
class CellarHQCallbackHandler implements Handler {
    static final String USER_PROFILE = 'ratpack.pac4j-user-profile'

    @Override
    void handle(Context context) throws Exception {
        final Request request = context.request
        final SessionStorage sessionStorage = request.get(SessionStorage)
        final Clients clients = request.get(Clients)
        final RatpackWebContext webContext = new RatpackWebContext(context)
        context.blocking {
            Client<Credentials, UserProfile> client = clients.findClient(webContext)
            Credentials credentials = client.getCredentials(webContext)
            return client.getUserProfile(credentials, webContext)
        } onError { Throwable ex ->
            if (ex instanceof RequiresHttpAction) {
                webContext.sendResponse((RequiresHttpAction) ex)
            } else {
                SessionUtil.setFlash(request, FlashMessage.error(ex.message))
                throw new TechnicalException('Failed to get user profile', ex)
            }
        } then { UserProfile profile ->
            saveUserProfileInSession(sessionStorage, profile)
            context.redirect(getAuthHandlerUri(profile))
        }
    }

    private static void saveUserProfileInSession(SessionStorage sessionStorage, UserProfile profile) {
        if (profile != null) {
            sessionStorage.put(USER_PROFILE, profile)
        }
    }

    private static String getAuthHandlerUri(UserProfile profile) {
        if (profile instanceof HttpProfile) {
            return '/auth-form'
        } else if (profile instanceof TwitterProfile) {
            return '/auth-twitter'
        }
        throw new TechnicalException("Could not determine auth handler for profile '${profile?.class?.simpleName}")
    }
}
