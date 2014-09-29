package com.cellarhq.jooq

import static com.cellarhq.generated.Tables.CELLAR
import static com.cellarhq.generated.Tables.CELLARED_DRINK
import static com.cellarhq.generated.Tables.DRINK

import groovy.transform.CompileStatic
import org.jooq.DSLContext
import org.jooq.Field
import org.jooq.impl.DSL

/**
 * Updates a Cellar's drink stats according to the current state of the database.
 */
@CompileStatic
class CellarStatsUpdater {

    static void update(Long cellarId, DSLContext create) {
        Field<Short> totalDrinks = create.select(DSL.sum(CELLARED_DRINK.QUANTITY))
                .from(CELLARED_DRINK)
                .where(CELLARED_DRINK.CELLAR_ID.eq(cellarId))
                .asField()

        Field<Short> uniqueDrinks = create.selectCount()
                .from(CELLARED_DRINK)
                .where(CELLARED_DRINK.CELLAR_ID.eq(cellarId))
                .groupBy(CELLARED_DRINK.DRINK_ID)
                .asField()

        Field<Short> uniqueOrganizations = create.selectCount()
                .from(CELLARED_DRINK)
                .join(DRINK).onKey()
                .where(CELLARED_DRINK.CELLAR_ID.eq(cellarId))
                .groupBy(DRINK.ORGANIZATION_ID)
                .asField()

        create.update(CELLAR)
                .set(CELLAR.TOTAL_BEERS, totalDrinks)
                .set(CELLAR.UNIQUE_BEERS, uniqueDrinks)
                .set(CELLAR.UNIQUE_BREWERIES, uniqueOrganizations)
                .where(CELLAR.ID.eq(cellarId))
    }
}
