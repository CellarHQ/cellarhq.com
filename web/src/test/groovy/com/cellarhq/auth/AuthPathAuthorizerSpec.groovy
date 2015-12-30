package com.cellarhq.auth

import org.pac4j.core.profile.UserProfile
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
    'authorized'                                   | true
    'cellars/12'                                   | false
    'beers/'                                       | false
    'styles/cellarhq.css'                          | false

    expectation = expected ? 'should require' : 'should not require'
  }

  @Unroll("The path '#path' accessed by user with roles '#roles' #description")
  def 'verify role-based authorization'() {
    given:
    AuthPathAuthorizer authorizer = new AuthPathAuthorizer()

    and:
    Context context = Mock(Context) {
      getRequest() >> {
        Stub(Request) {
          getPath() >> path
        }
      }
    }

    and:
    UserProfile userProfile = Stub(UserProfile) {
      getRoles() >> roles.collect { it.toString() }
    }

    when:
    authorizer.handleAuthorization(context, userProfile)

    then:
    if (shouldRedirect) {
      1 * context.redirect(_)
      0 * context.next()
    } else {
      0 * context.redirect(_)
      1 * context.next()
    }

    where:
    path            | roles                     | shouldRedirect
    'public/page'   | []                        | false
    'public/page'   | [Role.MEMBER]             | false
    'admin/secrets' | [Role.MEMBER]             | true
    'admin/secrets' | [Role.ADMIN]              | false
    'admin/secrets' | [Role.MEMBER, Role.ADMIN] | false

    description = shouldRedirect ? 'should redirect to login' : 'should grant access'
  }
}
