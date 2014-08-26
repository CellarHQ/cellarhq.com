package com.cellarhq.functional.pages


class BreweriesPage extends BasePage {

    static url = '/breweries'
    static at = { pageId ==~ /breweries.list/ }
}