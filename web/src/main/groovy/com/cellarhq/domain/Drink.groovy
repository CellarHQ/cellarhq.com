package com.cellarhq.domain

import com.cellarhq.generated.tables.records.DrinkRecord
import com.cellarhq.jooq.annotations.Sanitize
import com.github.slugify.Slugify
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

import java.time.LocalDateTime

@Sanitize(recordType = DrinkRecord, fields = [
  'name',
  'description',
  'availability'
])
@CompileStatic
@InheritConstructors
class Drink extends com.cellarhq.generated.tables.pojos.Drink {

  Photo photo

  String organizationName
  String organizationSlug
  String styleName
  String glasswareName

  Drink() {
    locked = false
    needsModeration = true
    createdDate = LocalDateTime.now()
    modifiedDate = createdDate
    warningFlag = false
    tradableBeers = 0
    cellaredBeers = 0
    containedInCellars = 0
  }

  @Override
  void setSlug(String slug) {
    super.setSlug(new Slugify().slugify(slug))
  }

  boolean getEditable() {
    !locked
  }
}
