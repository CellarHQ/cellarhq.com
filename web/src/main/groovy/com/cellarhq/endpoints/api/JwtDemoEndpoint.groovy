package com.cellarhq.endpoints.api

import static ratpack.jackson.Jackson.json

import com.cellarhq.security.JwtPayload
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain

class JwtDemoEndpoint implements Action<Chain> {

    @Override
    void execute(Chain chain) throws Exception {
        Groovy.chain(chain) {
            get('jwt-demo') {
                render json(request.get(JwtPayload))
            }
        }
    }
}
