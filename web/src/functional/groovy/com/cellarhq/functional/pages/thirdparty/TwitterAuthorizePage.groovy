package com.cellarhq.functional.pages.thirdparty

import geb.Page

class TwitterAuthorizePage extends Page {

    static at = { title.endsWith('Authorize an application') }
    static content = {
        authorizeButton(wait: true) { $('#allow') }
    }

    void authorize() {
        js.exec("document.getElementById('allow').click();")
    }
}
