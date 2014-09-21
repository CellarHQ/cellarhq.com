package com.cellarhq.domain

import com.cellarhq.annotations.Sanitize
import com.cellarhq.generated.tables.records.DrinkRecord
import com.github.slugify.Slugify
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

@Sanitize(recordType = DrinkRecord, fields = [
        'name',
        'description',
        'availability'
])
@CompileStatic
@InheritConstructors
class Drink extends com.cellarhq.generated.tables.pojos.Drink {

    Drink() {
        searchable = true
        locked = false
        needsModeration = true
    }

    @Override
    void setSlug(String slug) {
        super.slug = new Slugify().slugify(slug)
    }
}
