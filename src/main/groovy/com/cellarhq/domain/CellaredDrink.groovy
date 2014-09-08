package com.cellarhq.domain

import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

import javax.persistence.Column
import javax.validation.constraints.Min

@CompileStatic
@InheritConstructors
class CellaredDrink extends com.cellarhq.generated.tables.pojos.CellaredDrink {

    CellaredDrink() {
        quantity = 0
        setPrivate(false)
        tradeable = false
    }

    @Override
    @Min(0L)
    @Column(name = 'num_tradeable')
    Short getNumTradeable() {
        return super.numTradeable
    }
}
