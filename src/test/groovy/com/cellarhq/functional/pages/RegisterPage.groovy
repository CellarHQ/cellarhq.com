package com.cellarhq.functional.pages

class RegisterPage extends BasePage {

    static url = '/register'
    static at = { pageId ==~ /register/ }

    static content = {
        twitterLoginLink(wait: true) { $('#twitter-login-btn') }
        registerForm(wait: true) { $('#form-register') }
        submitButton(wait: true) { $('button[type=submit]') }
        errorMessages(required: false) { $('.alert.alert-danger') }
    }

    void fillForm(String email, String username, String password, String password2) {
        registerForm.email = email
        registerForm.screenName = username
        registerForm.password = password
        registerForm.passwordConfirm = password2
    }

    void submitForm() {
        submitButton.click()
    }
}
