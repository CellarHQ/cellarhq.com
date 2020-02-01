package com.cellarhq.functional

import com.cellarhq.functional.pages.LoginPage
import groovy.transform.CompileStatic

@CompileStatic
/**
 * Trait to add a method to log in a user
 */
trait LogInUserTrait {
  void logInUser(String email, String password) {
    LoginPage page = to LoginPage
    page.fillForm(email, password)
    page.submitForm()
  }
}
