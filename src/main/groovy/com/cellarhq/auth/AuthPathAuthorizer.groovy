package com.cellarhq.auth

import groovy.transform.CompileStatic
import ratpack.handling.Context
import ratpack.pac4j.AbstractAuthorizer

/**
 * Simple path-based authorizer that will blacklist anything not in the anonymous whitelist.
 *
 * @todo Add consideration of the HTTP method... M<S, L<HttpMethod>>
 */
@CompileStatic
class AuthPathAuthorizer extends AbstractAuthorizer {

    final static List<String> ANONYMOUS_WHITELIST = [
            '/',
            '/cellars',
            /\/cellars\/[a-zA-Z0-9\-_]+/,
            '/beers',
            /\/beers\/[a-zA-Z0-9\-_]+/,
            '/breweries',
            /\/breweries\/[a-zA-Z0-9\-_]+/,
            '/about',
            '/register',
            '/login',
            '/forgot-password'
    ]

    @Override
    boolean isAuthenticationRequired(Context context) {
        return !ANONYMOUS_WHITELIST.any { String regex ->
            context.request.path.matches("^${regex}/?\$")
        }
    }
}
