package com.cellarhq.brewerydb

import groovy.transform.CompileStatic

@CompileStatic
enum WebHookBrewerySubAction {
    NONE('none'),
    ALTERNATE_NAME_INSERT('alternatename-insert'),
    ALTERNATE_NAME_DELETE('alternatename-delete'),
    BEER_INSERT('beer-insert'),
    BEER_EDIT('beer-edit'),
    BEER_DELETE('beer-delete'),
    EVENT_INSERT('event-insert'),
    EVENT_DELETE('event-delete'),
    EVENT_EDIT('event-edit'),
    GUILD_INSERT('guild-insert'),
    GUILD_EDIT('guild-edit'),
    GUILD_DELETE('guild-delete'),
    SOCIAL_ACCOUNT_INSERT('socialaccount-insert'),
    SOCIAL_ACCOUNT_DELETE('socialaccount-delete'),
    SOCIAL_ACCOUNT_EDIT('socialaccount-edit'),
    LOCATION_INSERT('location-insert'),
    LOCATION_DELETE('location-delete'),
    LOCATION_EDIT('location-edit')

    private final String value

    WebHookBrewerySubAction(String value) {
        this.value = value
    }

    String toString() {
        return value
    }
}
