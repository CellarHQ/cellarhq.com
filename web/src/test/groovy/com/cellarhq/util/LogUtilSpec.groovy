package com.cellarhq.util

import spock.lang.Specification

class LogUtilSpec extends Specification {

    def 'toLog serializes input map to key value formatting'() {
        given:
        String key = 'someKey'
        Map properties = [
                string: 'string',
                numberInt: 1234,
                numberFloat: 1.234,
                object: [bar: 'baz'],
                nullable: null
        ]

        when:
        String out = LogUtil.toLog(key, properties)

        then:
        assert out == 'KEY=someKey, string=string, numberInt=1234, numberFloat=1.234, object=[bar:baz], nullable=null'
    }
}
