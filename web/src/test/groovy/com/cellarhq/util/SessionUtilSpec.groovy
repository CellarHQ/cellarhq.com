package com.cellarhq.util

import org.pac4j.core.profile.CommonProfile
import spock.lang.Specification
import spock.lang.Unroll

class SessionUtilSpec extends Specification {

    @Unroll("Given '#profile' with '#username' username, user #description")
    def 'utility returns whether or not profile is logged in'() {
        given:
        profile?.addAttribute(CommonProfile.USERNAME, username)

        when:
        boolean result = SessionUtil.isLoggedIn(Optional.ofNullable(profile))

        then:
        noExceptionThrown()
        assert result == expected

        where:
        profile             | username || expected
        new CommonProfile() | 'foo'    || true
        null                | null     || false
        new CommonProfile() | null     || false


        description = expected ? 'is logged in' : 'is not logged in'
    }
}
