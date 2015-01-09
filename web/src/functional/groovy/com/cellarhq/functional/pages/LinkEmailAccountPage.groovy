package com.cellarhq.functional.pages

class LinkEmailAccountPage extends BasePage {

    static url = '/settings/link-email'
    static at = { pageId ==~ /link-email-account/ }

    static content = {
        linkForm(wait: true) { $('#link-email-form') }
        submitButton(wait: true) { $('button[type=submit]') }
    }

    void fillForm(String email) {
        linkForm.email = email
    }

    void submitForm() {
        submitButton.click()
    }
}
