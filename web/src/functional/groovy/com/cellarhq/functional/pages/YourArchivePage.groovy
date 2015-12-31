package com.cellarhq.functional.pages

import geb.Module

class YourArchivePage extends BasePage {

  static url = '/yourarchive'
  static at = { pageId ==~ /yourarchive/ }

  static content = {
    archtiveItems { moduleList CellarRow, $("table#archived-drinks-table tr").tail() } // tailing to skip the header row
  }
}

class CellarRow extends Module {
  static content = {
    cell { $("td", it) }
    productName { cell(0).text() }
    quantity { cell(1).text().toInteger() }
    price { cell(2).text().toDouble() }
  }
}
