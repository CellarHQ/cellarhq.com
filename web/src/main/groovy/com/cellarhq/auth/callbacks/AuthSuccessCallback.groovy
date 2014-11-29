package com.cellarhq.auth.callbacks

import com.cellarhq.util.LogUtil
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.pac4j.core.profile.UserProfile
import org.pac4j.http.profile.HttpProfile
import org.pac4j.oauth.profile.twitter.TwitterProfile
import ratpack.handling.Context

import java.util.function.BiConsumer

@Slf4j
@CompileStatic
class AuthSuccessCallback<C extends Context, P extends UserProfile> implements BiConsumer<C, P> {

    @Override
    void accept(C context, P profile) {
        if (profile instanceof TwitterProfile) {
            context.get(TwitterCallback).accept(context, profile)
        } else if (profile instanceof HttpProfile) {
            context.get(HttpCallback).accept(context, profile)
        } else if (profile) {
            log.warn(LogUtil.toLog(context.request, 'UnhandledProfileClass', [
                    msg: 'Unknown profile for auth success callback',
                    type: profile.getClass().name
            ]))
        }
    }
}
