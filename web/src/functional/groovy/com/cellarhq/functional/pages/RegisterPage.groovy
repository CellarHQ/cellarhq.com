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

  void fillForm(String email, String emailConfirm, String username, String password, String password2) {

    registerForm.with {
      it.email = email
      it.screenName = username
      it.password = password
      it.passwordConfirm = password2
      it.emailConfirm = emailConfirm
      it.'register-terms' = true
    }
  }

  void submitForm() {
    submitButton.click()
  }
}
