package com.cellarhq.util

import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateUtilSpec extends Specification {

    @Unroll("Date input '#input' is parsed: '#expected'")
    def 'parse dates'() {
        when:
        Optional<LocalDate> parsed = DateUtil.parse(input)

        then:
        noExceptionThrown()
        assert expected ? parsed.isPresent() : !parsed.isPresent()

        where:
        input        | expected
        '2014'       | true
        '2014-05'    | true
        '2014-01-01' | true
        'sdfa'       | false
        '2014-9-14'  | true
    }
}
