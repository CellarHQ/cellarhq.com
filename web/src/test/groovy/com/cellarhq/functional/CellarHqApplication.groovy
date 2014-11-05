package com.cellarhq.functional

import ratpack.groovy.test.LocalScriptApplicationUnderTest


class CellarHqApplication  extends LocalScriptApplicationUnderTest {

    CellarHqApplication() {
        super(getOverriddenProperties())
    }

    private static Map<String, String> getOverriddenProperties() {
        def overrides = [
                'other.remoteControl.enabled'  : 'true',
        ]
        if (!System.getenv('CI')) {
            overrides['other.dataSource.databaseName'] = 'cellarhq_testing'
        }

        return overrides
    }
}
