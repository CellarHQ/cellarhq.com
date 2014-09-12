package com.cellarhq.services

import static com.cellarhq.generated.Tables.*
import static ratpack.rx.RxRatpack.observeEach

import com.cellarhq.domain.Drink
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.jooq.DSLContext
import ratpack.exec.ExecControl

import javax.sql.DataSource

@CompileStatic
class DrinkService extends BaseJooqService {

    @Inject
    DrinkService(DataSource dataSource, ExecControl execControl) {
        super(dataSource, execControl)
    }


    rx.Observable<Drink> search(String searchTerm, int numberOfRows=20, int offset=0) {
        observeEach(execControl.blocking {
            jooq { DSLContext create ->
                create.select()
                        .from(DRINK)
                        .where(DRINK.NAME.likeIgnoreCase("%${searchTerm}%"))
                        .orderBy(DRINK.NAME)
                        .limit(offset, numberOfRows)
                        .fetchInto(Drink)
            }
        })
    }
}
