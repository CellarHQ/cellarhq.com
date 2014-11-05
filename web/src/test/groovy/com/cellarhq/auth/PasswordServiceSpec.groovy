package com.cellarhq.auth

import spock.lang.Specification
import spock.lang.Unroll

class PasswordServiceSpec extends Specification {

    @Unroll('#hash #description')
    def 'should rehash password'() {
        given:
        PasswordService passwordService = new PasswordService()

        when:
        def result = passwordService.shouldRehashPassword(hash)

        then:
        noExceptionThrown()
        assert result == shouldRehash

        where:
        hash | shouldRehash
        '$2a$16$WBCR3zsCnH20tDjl'  | true
        '$2a$160$WBCR3zsCnH20tDjl' | true
        '$2a$x16$WBCR3zsCnH20tDjl' | true
        '$2a$xx$WBCR3zsCnH20tDjl'  | true
        '$2a$0$WBCR3zsCnH20tDjl'   | true
        '$2a$5$WBCR3zsCnH20tDjl'   | true
        'unclaimed'                | true
        null                       | true
        '$2a$6$WBCR3zsCnH20tDjl'   | false

        description = shouldRehash ? 'is too strong' : 'is not too strong'
    }
}
