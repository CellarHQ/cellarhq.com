package com.cellarhq.services

import static com.cellarhq.generated.Tables.*
import static ratpack.rx.RxRatpack.observe

import com.cellarhq.domain.BreweryDbSync
import com.cellarhq.generated.tables.records.BrewerydbSyncRecord
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.jooq.DSLContext
import ratpack.exec.ExecControl

import javax.sql.DataSource

@CompileStatic
class BreweryDbSyncService extends BaseJooqService {

    @Inject
    BreweryDbSyncService(DataSource dataSource, ExecControl execControl) {
        super(dataSource, execControl)
    }

    rx.Observable<Boolean> queueSyncRequest(BreweryDbSync sync) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                BrewerydbSyncRecord record = create.newRecord(BREWERYDB_SYNC, sync)
                record.reset(BREWERYDB_SYNC.ID)
                record.store()
            }
        })
    }
}
