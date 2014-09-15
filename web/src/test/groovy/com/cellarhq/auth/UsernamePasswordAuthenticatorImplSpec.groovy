package com.cellarhq.auth

import com.cellarhq.services.AccountService
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime

class UsernamePasswordAuthenticatorImplSpec extends Specification {

    @Unroll("failure delay is '#expectedDelay' seconds after '#attempts' attempts")
    def 'login failure delay increases incrementally after first failure'() {
        given:
        UsernamePasswordAuthenticatorImpl authenticator = new UsernamePasswordAuthenticatorImpl(
                Mock(AccountService),
                Mock(PasswordService)
        )
        LocalDateTime threeMinutesAgo = LocalDateTime.now().minusMinutes(3)

        when:
        int result = authenticator.calculateDelay(threeMinutesAgo, attempts)

        then:
        noExceptionThrown()
        assert result == expectedDelay

        where:
        attempts || expectedDelay
        0        || 0
        1        || 0
        2        || 2
        3        || 4
        4        || 6
        10       || UsernamePasswordAuthenticatorImpl.MAX_DELAY_SECONDS
    }
}
