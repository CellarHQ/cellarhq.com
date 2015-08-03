package com.cellarhq.brewerydb

import groovy.transform.CompileStatic

@CompileStatic
enum WebHookBeerSubAction {
    NONE('none'),
    BREWERY_INSERT('brewery-insert'),
    BREWERY_DELETE('brewery-delete'),
    BREWERY_EDIT('brewery-edit'),
    EVENT_INSERT('event-insert'),
    EVENT_DELETE('event-delete'),
    EVENT_EDIT('event-edit'),
    INGREDIENT_INSERT('ingredient-insert'),
    INGREDIENT_DELETE('ingredient-delete'),
    INGREDIENT_EDIT('ingredient-edit'),
    SOCIAL_ACCOUNT_INSERT('socialaccount-insert'),
    SOCIAL_ACCOUNT_DELETE('socialaccount-delete'),
    SOCIAL_ACCOUNT_EDIT('socialaccount-edit')

    private final String value

    WebHookBeerSubAction(String value) {
        this.value = value
    }

    String toString() {
        return value
    }
}
