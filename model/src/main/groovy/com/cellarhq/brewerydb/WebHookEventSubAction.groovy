package com.cellarhq.brewerydb

import groovy.transform.CompileStatic

@CompileStatic
enum WebHookEventSubAction {
    NONE('none'),
    AWARD_CATEGORY_INSERT('awardcategory-insert'),
    AWARD_CATEGORY_DELETE('awardcategory-delete'),
    AWARD_CATEGORY_EDIT('awardcategory-edit'),
    AWARD_PLACE_INSERT('awardplace-insert'),
    AWARD_PLACE_DELETE('awardplace-delete'),
    AWARD_PLACE_EDIT('awardplace-edit'),
    BEER_INSERT('beer-insert'),
    BEER_DELETE('beer-delete'),
    BEER_EDIT('beer-edit'),
    BREWERY_INSERT('brewery-insert'),
    BREWERY_DELETE('brewery-delete'),
    BREWERY_EDIT('brewery-edit'),
    SOCIAL_ACCOUNT_INSERT('socialaccount-insert'),
    SOCIAL_ACCOUNT_DELETE('socialaccount-delete'),
    SOCIAL_ACCOUNT_EDIT('socialaccount-edit')

    private final String value

    WebHookEventSubAction(String value) {
        this.value = value
    }

    String toString() {
        return value
    }
}
