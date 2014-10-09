package com.cellarhq.functional

import ratpack.groovy.test.LocalScriptApplicationUnderTest


class CellarHqApplication  extends LocalScriptApplicationUnderTest {

    CellarHqApplication() {
        super(getOverriddenProperties())
    }

    private static Map<String, String> getOverriddenProperties() {
        def overriddenProperties = [
            'other.remoteControl.enabled': 'true',
            'other.hikari.dataSourceProperties.databaseName': 'cellarhq_testing'
        ]

        overriddenProperties
    }
}
