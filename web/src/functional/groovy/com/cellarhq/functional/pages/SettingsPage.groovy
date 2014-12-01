package com.cellarhq.functional.pages

class SettingsPage extends BasePage {

    static url = '/settings'
    static at = { pageId ==~ /settings/ }

    static content = {
        linkEmailAccountLink(wait: true) { $('#link-email-account') }
        linkTwitterAccountLink(wait: true) { $('#link-twitter-account') }
    }
}
