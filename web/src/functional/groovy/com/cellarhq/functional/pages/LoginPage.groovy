package com.cellarhq.functional.pages

class LoginPage extends BasePage {

  static url = '/login'
  static at = { pageId ==~ /login/ }

  static content = {
    twitterLoginLink(wait: true) { $('#twitter-login-btn') }
    loginForm(wait: true) { $('#form-login') }
    submitButton(wait: true) { $('button[type=submit]') }
    errorMessages(required: false) { $('.alert.alert-danger') }
  }

  void fillForm(String email, String password) {
    loginForm.username = email
    loginForm.password = password
  }

  void submitForm() {
    submitButton.click()
  }
}
