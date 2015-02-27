package com.cellarhq.api.services

import com.cellarhq.jooq.BaseJooqService

import static com.cellarhq.generated.Tables.GLASSWARE
import static ratpack.rx.RxRatpack.observeEach

import com.cellarhq.domain.Glassware
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.jooq.DSLContext
import ratpack.exec.ExecControl

import javax.sql.DataSource

@CompileStatic
class GlasswareService extends BaseJooqService {

    @Inject
    GlasswareService(DataSource dataSource, ExecControl execControl) {
        super(dataSource, execControl)
    }

    rx.Observable<Glassware> search(String searchTerm, int numRows = 20, int offset = 0) {
        observeEach(execControl.blocking {
            jooq { DSLContext create ->
                create.select()
                        .from(GLASSWARE)
                        .where(GLASSWARE.NAME.likeIgnoreCase("%${searchTerm}%"))
                        .orderBy(GLASSWARE.NAME.asc())
                        .limit(offset, numRows)
                        .fetchInto(Glassware)
            }
        })
    }
}
