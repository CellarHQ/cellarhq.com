package com.cellarhq.domain

import com.cellarhq.jooq.annotations.Sanitize
import com.cellarhq.generated.tables.records.DrinkRecord
import com.github.slugify.Slugify
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

import java.sql.Timestamp
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
        createdDate = Timestamp.valueOf(LocalDateTime.now())
        modifiedDate = createdDate
        warningFlag = false
        tradableBeers = 0
        cellaredBeers = 0
        containedInCellars = 0
    }

    @Override
    void setSlug(String slug) {
        super.slug = new Slugify().slugify(slug)
    }

    boolean getEditable() {
        !locked
    }
}
