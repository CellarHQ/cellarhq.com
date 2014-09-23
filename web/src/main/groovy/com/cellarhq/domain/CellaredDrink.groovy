package com.cellarhq.domain

import com.cellarhq.annotations.Sanitize
import com.cellarhq.generated.tables.records.CellaredDrinkRecord
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

import javax.persistence.Column
import javax.validation.constraints.Min

@Sanitize(recordType = CellaredDrinkRecord, fields = [
        'size',
        'notes',
        'binIdentifier'
])
@CompileStatic
@InheritConstructors
class CellaredDrink extends com.cellarhq.generated.tables.pojos.CellaredDrink {

    CellaredDrink() {
        quantity = 0
        tradeable = false
    }

    @Override
    @Min(0L)
    @Column(name = 'num_tradeable')
    Short getNumTradeable() {
        return super.numTradeable
    }
}
