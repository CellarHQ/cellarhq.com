package com.cellarhq.cellars

import com.cellarhq.generated.Keys
import com.cellarhq.support.ProgressSupport
import org.jooq.DSLContext
import org.jooq.impl.DSL

import static com.cellarhq.generated.Tables.*

class CellarCountUpdater implements ProgressSupport {
  void updateCounts(DSLContext dslContext) {
    List<Long> ids = dslContext.select(CELLAR.ID).from(CELLAR).fetchInto(Long)

    dslContext.batch(ids.collect { Long id ->
      incrementProgressAnts()

      Integer totalBeers = dslContext.select(DSL.sum(CELLARED_DRINK.QUANTITY))
        .from(CELLARED_DRINK)
        .where(CELLARED_DRINK.CELLAR_ID.eq(id))
        .fetchOneInto(Integer) ?: 0

      Integer uniqueBeers = dslContext.fetchCount(
        dslContext.selectDistinct(CELLARED_DRINK.DRINK_ID)
          .from(CELLARED_DRINK)
          .where(CELLARED_DRINK.CELLAR_ID.eq(id))) ?: 0

      Integer uniqueBreweries = dslContext.fetchCount(
        dslContext.selectDistinct(DRINK.ORGANIZATION_ID)
          .from(CELLARED_DRINK)
          .join(DRINK).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID)
          .where(CELLARED_DRINK.CELLAR_ID.eq(id))) ?: 0

      // last statment must return the sql statement for the batch
      dslContext.update(CELLAR)
        .set(CELLAR.TOTAL_BEERS, totalBeers)
        .set(CELLAR.UNIQUE_BEERS, uniqueBeers)
        .set(CELLAR.UNIQUE_BREWERIES, uniqueBreweries)
        .where(CELLAR.ID.eq(id))
    }).execute()
  }
}
