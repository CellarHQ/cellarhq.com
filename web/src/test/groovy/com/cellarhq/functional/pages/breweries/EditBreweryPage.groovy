package com.cellarhq.functional.pages.breweries

import com.cellarhq.functional.pages.BasePage


class EditBreweryPage extends BasePage {
    static at = { pageId ==~ /breweries.edit/ }

    static content = {
        editBreweryForm(wait: true) { $('#brewery_form') }
        submitButton(wait: true) { $('button[type=submit]') }
        errorMessages(required: false) { $('.alert.alert-danger') }
    }

    void fillForm() {
        editBreweryForm.description = 'descripton'
        editBreweryForm.established = '2011'
        editBreweryForm.phone = '513-824-1282'
        editBreweryForm.website = 'http://kyleboon.org'
        editBreweryForm.website = 'http://kyleboon.org'
        editBreweryForm.address = '298 Warwick St.'
        editBreweryForm.address2 = ''
        editBreweryForm.city = 'St. Paul'
        editBreweryForm.state = 'MN'
        editBreweryForm.country = 'US'
        editBreweryForm.postalcode = '55105'
    }

    void submitForm() {
        submitButton.click()
    }
}

