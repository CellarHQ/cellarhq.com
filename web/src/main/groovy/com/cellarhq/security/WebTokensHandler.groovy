package com.cellarhq.security

import static ratpack.jackson.Jackson.jsonNode

import com.auth0.jwt.JWTVerifier
import com.fasterxml.jackson.databind.JsonNode
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain
import ratpack.http.HttpMethod
import ratpack.http.Request
import ratpack.util.MultiValueMap

@Slf4j
@CompileStatic
class WebTokensHandler implements Action<Chain> {

    private final static String TOKEN = 'token'

    // TODO!!!
    final static String SECRET = 'secret'

    @SuppressWarnings('NestedBlockDepth')
    @Override
    void execute(Chain chain) throws Exception {
        Groovy.chain(chain) {
            handler {
                byContent {
                    json {
                        Request request = context.request

                        MultiValueMap<String, String> params = request.queryParams
                        if (params.containsKey(TOKEN)) {
                            request.add(getPayload(params.get(TOKEN)))
                        }

                        HttpMethod method = request.method
                        if (method.isPost() || method.isPut() || method.isPatch()) {
                            JsonNode node = context.parse(jsonNode())
                            if (node.has(TOKEN)) {
                                request.add(getPayload(node.get(TOKEN).textValue()))
                            }
                        }

                        context.next()
                    }
                }
            }
        }
    }

    JwtPayload getPayload(String token) {
        return (JwtPayload) new JWTVerifier(SECRET).verify(token)
    }
}
