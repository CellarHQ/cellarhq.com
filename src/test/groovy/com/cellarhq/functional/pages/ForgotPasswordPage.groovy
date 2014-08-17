package com.cellarhq.functional.pages

class ForgotPasswordPage extends BasePage {

    static url = '/forgot-password'
    static at = { pageId ==~ /forgot-password/ }
    static content = {
        forgotPasswordForm(wait: true) { $('#forgot-password-form') }
        submitButton(wait: true) { $('button[type=submit]') }
    }


    void fillForm(String email) {
        forgotPasswordForm.email = email
    }

    void submit() {
        submitButton.click()
    }
}
