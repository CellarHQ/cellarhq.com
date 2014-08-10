package com.cellarhq.functional.pages

class LoginPage extends BasePage {

    static url = '/login'
    static at = { pageId ==~ /login/ }

    static content = {
        twitterLoginLink(wait: true) { $('#twitter-login-btn') }
    }
}
