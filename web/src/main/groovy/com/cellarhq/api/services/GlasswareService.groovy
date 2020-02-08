package com.cellarhq.api.services

import com.cellarhq.domain.Glassware
import com.cellarhq.jooq.BaseJooqService
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.jooq.DSLContext
import ratpack.exec.Blocking
import ratpack.exec.Promise

import javax.sql.DataSource

import static com.cellarhq.generated.Tables.GLASSWARE

@CompileStatic
class GlasswareService extends BaseJooqService {

  @Inject
  GlasswareService(DataSource dataSource) {
    super(dataSource)
  }

  Promise<List<Glassware>> search(String searchTerm, int numRows = 20, int offset = 0) {
    Blocking.get {
      jooq { DSLContext create ->
        create.select()
              .from(GLASSWARE)
              .where(GLASSWARE.NAME.likeIgnoreCase("%${searchTerm}%"))
              .orderBy(GLASSWARE.NAME.asc())
              .limit(offset, numRows)
              .fetchInto(Glassware)
      }
    }
  }
}
