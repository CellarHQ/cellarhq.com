package com.cellarhq.auth.rememberme

import groovy.util.logging.Slf4j
import org.pac4j.core.profile.UserProfile
import ratpack.handling.Context
import ratpack.pac4j.internal.Pac4jProfileHandler

@Slf4j
class RememberMeHandler extends Pac4jProfileHandler {
    private final RememberMeService rememberMeService

    public RememberMeHandler(RememberMeService rememberMeService) {
        this.rememberMeService = rememberMeService
    }

    @Override
    public void handle(final Context context) throws Exception {
        UserProfile userProfile = getUserProfile(context)

        context.blocking {
            if (userProfile == null) {
                rememberMeService.autoLogin(context)
            }
        }.then {
            context.next()
        }
    }
}
