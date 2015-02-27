package com.cellarhq.domain

import com.cellarhq.jooq.annotations.Sanitize
import com.cellarhq.generated.tables.records.CellaredDrinkRecord
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

import javax.persistence.Column
import javax.validation.constraints.Min
import java.sql.Timestamp
import java.time.LocalDateTime

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
        createdDate = Timestamp.valueOf(LocalDateTime.now())
        modifiedDate = Timestamp.valueOf(LocalDateTime.now())
    }

    //todo: remove when we get to groovy 2.4.1
    @SuppressWarnings(['UnnecessaryOverridingMethod', 'UnnecessaryGetter'])
    @Override
    @Min(0L)
    @Column(name = 'num_tradeable')
    Short getNumTradeable() {
        return super.getNumTradeable()
    }
}
