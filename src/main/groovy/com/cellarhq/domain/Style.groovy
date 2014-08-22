package com.cellarhq.domain

import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

@CompileStatic
@InheritConstructors
class Style extends com.cellarhq.generated.tables.pojos.Style {

    Style() {
        searchable = true
    }
}
