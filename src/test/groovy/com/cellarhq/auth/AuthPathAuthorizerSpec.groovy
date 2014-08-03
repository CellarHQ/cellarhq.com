package com.cellarhq.auth

import ratpack.handling.Context
import ratpack.http.Request
import spock.lang.Specification
import spock.lang.Unroll

class AuthPathAuthorizerSpec extends Specification {

    @Unroll("The path '#path' #expectation authentication")
    def 'check if request path requires authentication'() {
        given:
        AuthPathAuthorizer authorizer = new AuthPathAuthorizer()

        and:
        Context context = Stub(Context) {
            getRequest() >> {
                Stub(Request) {
                    getPath() >> path
                }
            }
        }

        when:
        boolean result = authorizer.isAuthenticationRequired(context)

        then:
        result == expected

        where:
        path                                           | expected
        AuthPathAuthorizer.ANONYMOUS_WHITELIST.first() | false
        '/foo'                                         | true
        '/cellars/12'                                  | false
        '/beers/'                                      | false

        expectation = expected ? 'should require' : 'should not require'
    }
}
