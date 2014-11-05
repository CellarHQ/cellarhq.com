package com.cellarhq.jooq.listeners

import static com.cellarhq.generated.Tables.*

import com.cellarhq.generated.tables.records.CellaredDrinkRecord
import com.cellarhq.jooq.CellarStatsUpdater
import groovy.transform.CompileStatic
import org.jooq.Record
import org.jooq.RecordContext
import org.jooq.impl.DSL
import org.jooq.impl.DefaultRecordListener

/**
 * Responsible for updating a Cellar's drink stats whenever a record is saved or deleted.
 *
 * This listener does not listen to explicit queries executed. For any manual changes of drink quantities, you should
 * also manually invoke CellarStatsUpdater.
 */
@CompileStatic
class CellarStatsUpdatingListener extends DefaultRecordListener {

    @Override
    void storeEnd(RecordContext ctx) {
        super.storeEnd(ctx)

        if (!shouldProcessEvent(ctx.record(), false)) {
            return
        }

        CellarStatsUpdater.updateAllCounts(
                ctx.record().getValue(CELLARED_DRINK.CELLAR_ID),
                ctx.record().getValue(CELLARED_DRINK.DRINK_ID),
                DSL.using(ctx.configuration())
        )
    }

    @Override
    void deleteEnd(RecordContext ctx) {
        super.deleteStart(ctx)

        if (!shouldProcessEvent(ctx.record(), false)) {
            return
        }

        CellarStatsUpdater.updateCellarCounts(
                ctx.record().getValue(CELLARED_DRINK.CELLAR_ID),
                DSL.using(ctx.configuration())
        )
    }

    boolean shouldProcessEvent(Record record, isWrite = true) {
        if (record instanceof CellaredDrinkRecord) {
            if (isWrite) {
                return record.changed(CELLARED_DRINK.QUANTITY) || record.id == null
            }
            return true
        }
        return false
    }
}
