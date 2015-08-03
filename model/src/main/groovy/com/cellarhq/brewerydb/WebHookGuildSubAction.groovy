package com.cellarhq.brewerydb

import groovy.transform.CompileStatic

@CompileStatic
enum WebHookGuildSubAction {
    NONE('none'),
    BREWERY_INSERT('brewery-insert'),
    BREWERY_DELETE('brewery-delete'),
    BREWERY_EDIT('brewery-edit'),
    SOCIAL_ACCOUNT_INSERT('socialaccount-insert'),
    SOCIAL_ACCOUNT_DELETE('socialaccount-delete'),
    SOCIAL_ACCOUNT_EDIT('socialaccount-edit')

    private final String value

    WebHookGuildSubAction(String value) {
        this.value = value
    }

    String toString() {
        return value
    }
}
