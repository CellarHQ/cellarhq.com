package com.cellarhq.brewerydb

import groovy.transform.CompileStatic

@CompileStatic
enum WebHookAttribute {
    BEER('beer'),
    BREWERY('brewery'),
    LOCATION('location'),
    GUILD('guild'),
    EVENT('event')

    private final String value

    WebHookAttribute(String value) {
        this.value = value
    }

    String toString() {
        return value
    }
}
