package com.cellarhq.domain

import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

import javax.persistence.Column
import javax.validation.constraints.Min
import java.sql.Timestamp
import java.time.LocalDateTime

@CompileStatic
@InheritConstructors
class CellaredDrink extends com.cellarhq.generated.tables.pojos.CellaredDrink {

    CellaredDrink() {
        quantity = 0
        tradeable = false
        createdDate = Timestamp.valueOf(LocalDateTime.now())
        modifiedDate = Timestamp.valueOf(LocalDateTime.now())
    }

    @Override
    @Min(0L)
    @Column(name = 'num_tradeable')
    Short getNumTradeable() {
        return super.numTradeable
    }
}
