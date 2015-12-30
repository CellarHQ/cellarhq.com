package com.cellarhq.functional

import com.cellarhq.functional.pages.LoginPage

trait LogInUserTrait {


  void logInUser(String email, String password) {
    LoginPage page = to LoginPage
    page.fillForm(email, password)
    page.submitForm()
  }

}
