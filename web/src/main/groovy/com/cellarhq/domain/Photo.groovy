package com.cellarhq.domain

import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

import java.sql.Timestamp
import java.time.LocalDateTime

@CompileStatic
@InheritConstructors
class Photo extends com.cellarhq.generated.tables.pojos.Photo {

  enum Size {
    THUMB,
    LARGE
  }

  enum Type {
    CELLAR,
    DRINK,
    ORGANIZATION
  }

  final static String DESCRIPTION_TWITTER_UPLOAD = 'added by twitter profile'
  final static String DESCRIPTION_SETTINGS_UPLOAD = 'added by settings page'

  Photo() {
    version = 0
    createdDate = LocalDateTime.now()
    modifiedDate = createdDate
  }
}
