package com.cellarhq.api.services

import com.cellarhq.domain.Style
import com.cellarhq.jooq.BaseJooqService
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.jooq.DSLContext
import ratpack.exec.Blocking

import javax.sql.DataSource

import static com.cellarhq.generated.Tables.STYLE
import static ratpack.rx.RxRatpack.observeEach

@CompileStatic
class StyleService extends BaseJooqService {

  @Inject
  StyleService(DataSource dataSource) {
    super(dataSource)
  }

  rx.Observable<Style> search(String searchTerm, int numRows = 20, int offset = 0) {
    observeEach(Blocking.get {
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
