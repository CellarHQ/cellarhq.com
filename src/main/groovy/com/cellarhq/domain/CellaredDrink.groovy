package com.cellarhq.domain

import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

@CompileStatic
@InheritConstructors
class CellaredDrink extends com.cellarhq.generated.tables.pojos.CellaredDrink {

    CellaredDrink() {
        quantity = 0
        setPrivate(false)
    }
}
