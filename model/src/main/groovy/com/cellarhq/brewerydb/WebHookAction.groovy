package com.cellarhq.brewerydb

enum WebHookAction {
    INSERT('insert'),
    EDIT('edit'),
    DELETE('delete')

    private final String value

    WebHookAction(String value) {
        this.value = value
    }

    String toString() {
        return value
    }
}
