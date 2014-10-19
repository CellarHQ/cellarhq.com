package com.cellarhq.functional.pages.breweries

import com.cellarhq.functional.pages.BasePage


class ShowBreweryPage extends BasePage {
    static url = '/breweries'
    static at = { pageId ==~ /breweries.show/ }

    static content = {
        editBreweryButton(wait: true) { $('#edit-brewery-button') }
        beersTable(wait: true) { $('#beer-list') }
    }
}
