package com.cellarhq.webapp

import com.cellarhq.domain.views.HomepageStatistics
import com.cellarhq.jooq.BaseJooqService
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.jooq.Configuration
import org.jooq.DSLContext
import org.jooq.Field
import org.jooq.impl.DSL
import ratpack.exec.Blocking
import ratpack.exec.Promise

import javax.sql.DataSource

import static com.cellarhq.generated.Tables.*

@CompileStatic
class StatsService extends BaseJooqService {

  @Inject
  StatsService(DataSource dataSource) {
    super(dataSource)
  }

  Promise<HomepageStatistics> homepageStatistics() {
    Blocking.get {
      jooq({ Configuration c ->
        c.set(new HomepageStatisticsRecordMapperProvider())
      }) { DSLContext create ->
        Field<Integer> organizations = create.selectCount()
          .from(ORGANIZATION)
          .asField('organizations')
        Field<Integer> drinks = create.selectCount()
          .from(DRINK)
          .asField('drinks')
        Field<Integer> cellars = create.selectCount()
          .from(CELLAR)
          .asField('cellars')
        Field<Integer> cellaredDrinks = create.select(DSL.sum(CELLARED_DRINK.QUANTITY))
          .from(CELLARED_DRINK)
          .asField('cellaredDrinks')

        create.select(organizations, drinks, cellars, cellaredDrinks)
          .fetchOneInto(HomepageStatistics)
      }
    }
  }
}
