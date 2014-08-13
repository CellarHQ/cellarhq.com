package com.cellarhq.endpoints

import static ratpack.groovy.Groovy.groovyMarkupTemplate

import org.pac4j.core.profile.UserProfile
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler

class YourCellarEndpoint extends GroovyHandler {

    @Override
    protected void handle(GroovyContext context) {
        context.with {
            byMethod {
                get {
                    UserProfile profile = request.get(UserProfile)
                    render groovyMarkupTemplate('yourcellar.gtpl',
                            username: profile.username,
                            title: 'Your Cellar',
                            loggedIn: true)
                }
            }
        }
    }
}
