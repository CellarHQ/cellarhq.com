package com.cellarhq.services

import static com.cellarhq.util.DataSourceUtil.withDslContext

import com.cellarhq.domain.jooq.Cellar
import com.cellarhq.generated.Tables
import com.cellarhq.generated.tables.records.CellarRecord
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.jooq.DSLContext

import javax.sql.DataSource

@CompileStatic
class JooqCellarService {

    private final DataSource dataSource

    @Inject
    JooqCellarService(DataSource dataSource) {
        this.dataSource = dataSource
    }

    Cellar get(Long id) {
        withDslContext(dataSource) { DSLContext create ->
            create.select()
                    .from(Tables.CELLAR)
                    .where(Tables.CELLAR.ID.eq(id))
                    .fetchOne()
                    .into(Cellar)
        }
    }

    Cellar save(Cellar cellar) {
        withDslContext(dataSource) { DSLContext create ->
            CellarRecord cellarRecord = create.newRecord(Tables.CELLAR, cellar)
            if (cellar.id) {
                create.executeUpdate(cellarRecord)
            } else {
                create.executeInsert(cellarRecord)
            }
            cellarRecord.into(Cellar)
        }
    }
}
