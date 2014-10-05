package com.cellarhq.services

import static com.cellarhq.generated.Tables.*
import static ratpack.rx.RxRatpack.observeEach

import com.cellarhq.domain.Style
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.jooq.DSLContext
import ratpack.exec.ExecControl

import javax.sql.DataSource

@CompileStatic
class StyleService extends BaseJooqService {

    @Inject
    StyleService(DataSource dataSource, ExecControl execControl) {
        super(dataSource, execControl)
    }

    rx.Observable<List<Style>> search(String searchTerm, int numRows = 20, int offset = 0) {
        observeEach(execControl.blocking {
            jooq { DSLContext create ->
                create.select()
                        .from(STYLE)
                        .where(STYLE.NAME.likeIgnoreCase("%${searchTerm}%"))
                        .orderBy(STYLE.NAME.asc())
                        .limit(offset, numRows)
                        .fetchInto(Style)
            }
        })
    }
}
