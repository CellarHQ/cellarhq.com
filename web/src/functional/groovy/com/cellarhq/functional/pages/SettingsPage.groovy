package com.cellarhq.functional.pages

class SettingsPage extends BasePage {

  static url = '/settings'
  static at = { pageId ==~ /settings/ }

  static content = {
    emptyCellarButton(wait: true) { $('#empty-cellar') }
    deleteAccountButton(wait: true) { $('#delete-account') }
    updateSettingsButton(wait: true) { $('#update-settings') }
    settingsForm(wait: true) { $('#settings-form') }
    errorMessages(required: false) { $('.alert.alert-success') }
    //linkEmailAccountLink(wait: true) { $('#link-email-account') }
    //linkTwitterAccountLink(wait: true) { $('#link-twitter-account') }
  }

  void updateSettings() {
    updateSettingsButton.click()
  }

  void emptyCellar() {
    emptyCellarButton.click()
  }

  void deleteAccount() {
    deleteAccountButton.click()
  }

  void fillForm(String displayName,
                String location,
                String website,
                String bio,
                String contactEmail,
                String twitter,
                String reddit,
                String beeradvocate,
                String ratebeer,
                boolean isPrivate) {

    settingsForm.with {
      it.displayName = displayName
      it.location = location
      it.website = website
      it.bio = bio
      it.contactEmail = contactEmail
      it.twitter = twitter
      it.reddit = reddit
      it.beeradvocate = beeradvocate
      it.ratebeer = ratebeer
      it.private = isPrivate
    }
  }
}
