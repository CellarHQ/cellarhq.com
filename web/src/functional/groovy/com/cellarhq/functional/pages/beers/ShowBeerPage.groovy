package com.cellarhq.functional.pages.beers

import com.cellarhq.functional.pages.BasePage

class ShowBeerPage extends BasePage {
    static at = { pageId ==~ /beers.show/ }
}
