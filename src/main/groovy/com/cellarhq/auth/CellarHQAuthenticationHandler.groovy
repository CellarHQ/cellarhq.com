package com.cellarhq.auth

import groovy.transform.CompileStatic
import org.pac4j.core.client.Clients
import org.pac4j.core.exception.RequiresHttpAction
import org.pac4j.core.exception.TechnicalException
import org.pac4j.core.profile.UserProfile
import ratpack.handling.Context
import ratpack.http.Request
import ratpack.pac4j.Authorizer
import ratpack.pac4j.internal.Pac4jProfileHandler
import ratpack.pac4j.internal.RatpackWebContext
import ratpack.session.store.SessionStorage

/**
 * Filters requests to apply authentication and authorization as required.
 *
 * Unlike the ratpack-pac4j authentication handler, this handler will derive the client used for authentication on the
 * request URI. If an authentication client cannot be determined, a TechnicalException will be thrown.
 */
@SuppressWarnings('VariableName')
@CompileStatic
class CellarHQAuthenticationHandler extends Pac4jProfileHandler {

    // From the ratpack-pac4j internals class SessionConstants
    private final static String SAVED_URI = 'ratpack.pac4j-saved-uri'

    private final Authorizer authorizer

    CellarHQAuthenticationHandler(Authorizer authorizer) {
        this.authorizer = authorizer
    }

    @Override
    void handle(final Context context) {
        UserProfile userProfile = getUserProfile(context)
        if (authorizer.isAuthenticationRequired(context) && userProfile == null) {
            initiateAuthentication(context)
        } else {
            if (userProfile != null) {
                registerUserProfile(context, userProfile)
                authorizer.handleAuthorization(context, userProfile)
            } else {
                context.next()
            }
        }
    }

    private void initiateAuthentication(final Context context) throws Exception {
        final Request request = context.request
        request.get(SessionStorage).put(SAVED_URI, request.uri)
        final Clients clients = request.get(Clients)
        final RatpackWebContext webContext = new RatpackWebContext(context)

        context.blocking {
            clients.findClient(deriveClient(request.path, webContext)).redirect(webContext, true, false)
        } onError { Throwable ex ->
            if (ex instanceof RequiresHttpAction) {
                webContext.sendResponse((RequiresHttpAction) ex)
            } else {
                throw new TechnicalException('Failed to redirect', ex)
            }
        } then {
            webContext.sendResponse()
        }
    }

    private String deriveClient(String path, RatpackWebContext webContext) throws RequiresHttpAction {
        if (path.startsWith('auth-twitter')) {
            return 'TwitterClient'
        } else if (path.startsWith('auth-form')) {
            return 'FormClient'
        }
        throw RequiresHttpAction.redirect('Unauthorized to view this page', webContext, '/login')
    }
}
