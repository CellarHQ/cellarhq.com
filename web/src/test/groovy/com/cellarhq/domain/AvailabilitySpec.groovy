package com.cellarhq.domain

import spock.lang.Specification

class AvailabilitySpec extends Specification {

    def 'handlebars representation'() {
        when:
        def result = Availability.toHandlebars()

        then:
        assert result.first() == [
                value: 'limited',
                label: 'Limited'
        ]
        assert result[1] == [
                value: 'one time',
                label: 'One Time'
        ]
    }
}
