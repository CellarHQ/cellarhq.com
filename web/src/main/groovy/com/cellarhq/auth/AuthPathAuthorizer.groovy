package com.cellarhq.auth

import com.cellarhq.Messages
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.pac4j.core.profile.UserProfile
import ratpack.handling.Context
import ratpack.pac4j.AbstractAuthorizer

/**
 * Simple path-based authorizer that will blacklist anything not in the anonymous whitelist.
 *
 * @todo Add consideration of the HTTP method... M<S, L<HttpMethod>>
 */
@CompileStatic
@Slf4j
class AuthPathAuthorizer extends AbstractAuthorizer {

    final static List<String> ANONYMOUS_WHITELIST = [
            '',
            'cellars',
            /cellars\/[a-zA-Z0-9\-_]+/,
            /cellar\/[a-zA-Z0-9\-_]+/,
            'beers',
            /breweries\/(?!add$)[a-zA-Z0-9\-_]+\/beers\/(?!add$)[a-zA-Z0-9\-_]+/,
            /brewery\/(?!add$)[a-zA-Z0-9\-_]+\/beer\/(?!add$)[a-zA-Z0-9\-_]+/,
            'breweries',
            /breweries\/(?!add$)[a-zA-Z0-9\-_]+/,
            'about',
            'terms-of-service',
            'privacy-policy',
            'register',
            'login',
            'logout',
            'signin',
            'signup',
            'forgot-password',
            /forgot-password\/.*/,
            'forgotpassword',
            /styles\/.*/,
            /images\/.*/,
            /scripts\/.*/,
            /pac4j.*/,
            /favicon\.ico/,
            /api.*/,
            'health-checks',
            /admin\/.*/
    ]

    final static List<String> ADMIN_ROLE_REQUIRED = [
        'admin/secrets'
    ]

    @Override
    boolean isAuthenticationRequired(Context context) {
        return !matchesAnyPath(context.request.path, ANONYMOUS_WHITELIST)
    }

    @Override
    void handleAuthorization(Context context, UserProfile userProfile) throws Exception {
        if (matchesAnyPath(context.request.path, ADMIN_ROLE_REQUIRED) && !userHasRole(userProfile, Role.ADMIN)) {
            context.redirect('/login?error=' + Messages.UNAUTHORIZED_ERROR)
            return
        }
        context.next()
    }

    private boolean matchesAnyPath(String subject, List<String> patterns) {
        return matchesAny(subject, patterns.collect { String pattern -> (String) "^${pattern}/?\$"})
    }

    private boolean matchesAny(String subject, List<String> patterns) {
        return patterns.any { String pattern -> subject.matches(pattern) }
    }

    private boolean userHasRole(UserProfile userProfile, Role role) {
        return userProfile.roles.contains(role.toString())
    }
}
