package com.cellarhq.functional.pages.breweries

import com.cellarhq.functional.pages.BasePage


class AddBreweryPage extends BasePage {
    static url = '/breweries/add'
    static at = { pageId ==~ /breweries.new/ }

    static content = {
        addBreweryForm(wait: true) { $('#brewery_form') }
        submitButton(wait: true) { $('button[type=submit]') }
        errorMessages(required: false) { $('.alert.alert-danger') }
    }

    void fillForm() {
        addBreweryForm.name = 'name'
        addBreweryForm.description = 'descripton'
        addBreweryForm.established = '2011'
        addBreweryForm.phone = '513-824-1282'
        addBreweryForm.website = 'http://kyleboon.org'
        addBreweryForm.website = 'http://kyleboon.org'
        addBreweryForm.address = '298 Warwick St.'
        addBreweryForm.address2 = ''
        addBreweryForm.city = 'St. Paul'
        addBreweryForm.state = 'MN'
        addBreweryForm.country = 'US'
        addBreweryForm.postalcode = '55105'
    }

    void submitForm() {
        submitButton.click()
    }
}
