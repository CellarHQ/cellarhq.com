package com.cellarhq.functional.pages.thirdparty

import com.cellarhq.functional.pages.YourCellarPage
import geb.Page

/**
 * Uses a valid Twitter account for logging in.
 */
class TwitterOAuthPage extends Page {

  final static TWITTER_VALID_USERNAME = 'cellarhqtesting'
  final static TWITTER_VALID_PASSWORD = 'testingcellarhq'

  static at = { title ==~ /Twitter \/ Authorize an application/ }
  static content = {
    username(wait: true) { $('input#username_or_email') }
    password(wait: true) { $('input#password') }
    submitButton(wait: true, to: [TwitterAuthorizePage, YourCellarPage]) { $('input#allow') }
  }

  void login() {
    username = TWITTER_VALID_USERNAME
    password = TWITTER_VALID_PASSWORD
    submitButton.click()
  }
}
