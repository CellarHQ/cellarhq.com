package com.cellarhq.functional.pages

class SettingsPage extends BasePage {

  static url = '/settings'
  static at = { pageId ==~ /settings/ }

  static content = {
    emptyCellarButton(wait: true) { $('#empty-cellar') }
    deleteAccountButton(wait: true) { $('#delete-account') }
    //linkEmailAccountLink(wait: true) { $('#link-email-account') }
    //linkTwitterAccountLink(wait: true) { $('#link-twitter-account') }
  }

  void emptyCellar() {
    emptyCellarButton.click()
  }

  void deleteAccount() {
    deleteAccountButton.click()
  }
}
