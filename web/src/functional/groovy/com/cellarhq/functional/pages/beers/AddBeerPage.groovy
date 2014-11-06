package com.cellarhq.functional.pages.beers

import com.cellarhq.domain.Availability
import com.cellarhq.domain.Organization
import com.cellarhq.functional.pages.BasePage

class AddBeerPage extends BasePage {

    static at = { pageId ==~ /beer.new/ }

    static content = {
        addBeerForm(wait: true) { $('#add-beer-form') }
        submitButton(wait: true) { $('button[type=submit]') }
        errorMessages(required: false) { $('.alert.alert-danger') }
    }

    String convertToPath(String orgSlug) {
        return "breweries/${orgSlug}/beers"
    }

    void fillForm(Organization organization) {
        addBeerForm.organization = organization?.name
        addBeerForm.organizationId = organization?.id
        addBeerForm.name = 'name'
        addBeerForm.description = 'description'
        addBeerForm.srm = 1
        addBeerForm.ibu = 1
        addBeerForm.abv = 1.0
        addBeerForm.availability = Availability.LIMITED
    }

    void submitForm() {
        submitButton.click()
    }
}
