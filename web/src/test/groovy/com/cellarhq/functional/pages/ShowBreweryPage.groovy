package com.cellarhq.functional.pages


class ShowBreweryPage extends BasePage {
    static url = '/breweries/show'
    static at = { pageId ==~ /breweries.show/ }
}
