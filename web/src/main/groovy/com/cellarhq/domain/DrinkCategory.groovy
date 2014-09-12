package com.cellarhq.domain

import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

@CompileStatic
@InheritConstructors
class DrinkCategory extends com.cellarhq.generated.tables.pojos.Category {

    DrinkCategory() {
        searchable = true
    }
}
