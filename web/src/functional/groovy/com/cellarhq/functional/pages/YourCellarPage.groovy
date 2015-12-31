package com.cellarhq.functional.pages

class YourCellarPage extends BasePage {

  static url = '/yourcellar'
  static at = { pageId ==~ /yourcellar/ }

  static content = {
    yourArchiveLink(wait: true) { $('#archive-link') }
    cellarItems { moduleList CellarRow, $("table#cellared-drinks-table tr").tail() } // tailing to skip the header row
  }
}
