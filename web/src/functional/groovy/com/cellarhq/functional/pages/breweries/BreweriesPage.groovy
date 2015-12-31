package com.cellarhq.functional.pages.breweries

import com.cellarhq.functional.pages.BasePage


class BreweriesPage extends BasePage {

  static url = '/breweries'
  static at = { pageId ==~ /breweries.list/ }
}
