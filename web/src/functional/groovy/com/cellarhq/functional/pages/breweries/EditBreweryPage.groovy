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
    editBreweryForm.with {
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

