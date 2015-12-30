package com.cellarhq.functional.pages

class LinkTwitterAccountPage extends BasePage {

  static url = '/settings/link-twitter'
  static at = { pageId ==~ /link-twitter-account/ }

  static content = {
    linkForm(wait: true) { $('#link-twitter-form') }
    submitButton(wait: true) { $('button[type=submit]') }
  }

  void fillForm(String username) {
    linkForm.username = username
  }

  void submitForm() {
    submitButton.click()
  }
}
