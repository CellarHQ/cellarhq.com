package com.cellarhq.auth

import groovy.transform.CompileStatic

/**
 * A list of all available roles within the CellarHQ system.
 */
@CompileStatic
public enum Role {
    ANONYMOUS('anonymous'),
    MEMBER('member'),
    ADMIN('admin')

    private final String value

    Role(String value) {
        this.value = value
    }
}
