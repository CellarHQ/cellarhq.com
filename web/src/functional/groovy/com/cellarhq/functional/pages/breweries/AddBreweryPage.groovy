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
      addBreweryForm.with {
        name = 'name'
        description = 'descripton'
        established = '2011'
        phone = '513-824-1282'
        website = 'http://kyleboon.org'
        website = 'http://kyleboon.org'
        address = '298 Warwick St.'
        address2 = ''
        city = 'St. Paul'
        state = 'MN'
        country = 'US'
        postalcode = '55105'
      }
    }

    void submitForm() {
        submitButton.click()
    }
}
