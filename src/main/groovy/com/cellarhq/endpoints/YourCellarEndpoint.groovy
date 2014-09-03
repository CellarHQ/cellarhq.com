package com.cellarhq.endpoints

import static ratpack.handlebars.Template.handlebarsTemplate

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
                    render handlebarsTemplate('yourcellar.html',
                            username: profile.username,
                            title: 'Your Cellar',
                            pageId: 'yourcellar')
                }
            }
        }
    }
}
