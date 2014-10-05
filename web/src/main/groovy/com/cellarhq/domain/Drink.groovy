package com.cellarhq.domain

import com.cellarhq.annotations.Sanitize
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

    String organizationName
    String organizationSlug
    String styleName

    Drink() {
        searchable = true
        locked = false
        needsModeration = true
        createdDate = Timestamp.valueOf(LocalDateTime.now())
        modifiedDate = createdDate
    }

    @Override
    void setSlug(String slug) {
        super.slug = new Slugify().slugify(slug)
    }

    boolean getEditable() {
        !locked
    }
}
