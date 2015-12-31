package com.cellarhq.functional.pages

import geb.Page

/**
 * The base page for all CellarHQ Geb pages
 */
abstract class BasePage extends Page {

  static content = {
    pageId(wait: true) { $('meta', name: 'pageId').getAttribute('content') }

    authenticatedMenu(required: false) { $('#authenticated-menu') }
    unauthenticatedMenu(required: false) { $('#unauthenticated-menu') }

    errorMessages(required: false) { $('.alert.alert-danger') }
    warningMessages(required: false) { $('.alert.alert-warning') }
    infoMessages(required: false) { $('.alert.alert-info') }
    successMessages(required: false) { $('.alert.alert-success') }
  }
}
