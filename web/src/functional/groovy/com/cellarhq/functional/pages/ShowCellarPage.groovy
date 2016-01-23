package com.cellarhq.functional.pages


class ShowCellarPage extends BasePage {

  static at = { pageId ==~ /cellars.show/ }

  static content = {
    exportLink(wait: true) { $('#export-link') }
    yourArchiveLink(wait: true) { $('#archive-link') }
    cellarItems { moduleList CellarRow, $("table#cellared-drinks-table tr").tail() } // tailing to skip the header row
  }
}
