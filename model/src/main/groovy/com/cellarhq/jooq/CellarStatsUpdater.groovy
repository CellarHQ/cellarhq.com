package com.cellarhq.jooq

import com.cellarhq.generated.Keys
import org.jooq.DSLContext
import org.jooq.impl.DSL

import static com.cellarhq.generated.Tables.*

/**
 * Updates a Cellar's drink stats according to the current state of the database.
 */
@SuppressWarnings('AbcMetric')
class CellarStatsUpdater {

    /**
     * Caller needs to already be in a blocking thread when executing this method.
     *
     * @param cellarId
     * @param drinkId
     * @param organizationId
     * @param create
     */
    static void updateAllCounts(Long cellarId, Long drinkId, DSLContext create) {
        updateCellarCounts(cellarId, create)
        updateDrinkCounts(drinkId, create)

        Long organizationId =  create.select(DRINK.ORGANIZATION_ID)
                .from(DRINK)
                .where(DRINK.ID.eq(drinkId))
                .fetchOneInto(Long)

        updateOrganizationCounts(organizationId, create)

    }

    static void updateCellarCounts(Long cellarId, DSLContext create) {
        Integer  totalBeers = create.select(DSL.sum(CELLARED_DRINK.QUANTITY))
            .from(CELLARED_DRINK)
            .where(CELLARED_DRINK.CELLAR_ID.eq(cellarId))
            .fetchOneInto(Integer) ?: 0

        Integer uniqueBeers = create.fetchCount(
            create.selectDistinct(CELLARED_DRINK.DRINK_ID)
                .from(CELLARED_DRINK)
                .where(CELLARED_DRINK.CELLAR_ID.eq(cellarId)).and(CELLARED_DRINK.QUANTITY.greaterThan(0)))

        Integer uniqueBreweries = create.fetchCount(
            create.selectDistinct(DRINK.ORGANIZATION_ID)
                .from(CELLARED_DRINK)
                .join(DRINK).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID)
                .where(CELLARED_DRINK.CELLAR_ID.eq(cellarId)).and(CELLARED_DRINK.QUANTITY.greaterThan(0)))

        create.update(CELLAR)
            .set(CELLAR.TOTAL_BEERS, totalBeers)
            .set(CELLAR.UNIQUE_BEERS, uniqueBeers)
            .set(CELLAR.UNIQUE_BREWERIES, uniqueBreweries)
            .where(CELLAR.ID.eq(cellarId)).execute()
    }

    static void updateDrinkCounts(Long drinkId, DSLContext create) {
        Integer containedInCellars = create.fetchCount(
            create.selectDistinct(CELLARED_DRINK.CELLAR_ID)
                .from(CELLARED_DRINK)
                .join(DRINK).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID)
                .where(DRINK.ID.eq(drinkId)))

        Integer cellaredBeers = create.select(DSL.sum(CELLARED_DRINK.QUANTITY))
            .from(CELLARED_DRINK)
            .join(DRINK).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID)
            .where(DRINK.ID.eq(drinkId)).fetchOneInto(Integer) ?: 0

        Integer forTrade = create.select(DSL.sum(CELLARED_DRINK.NUM_TRADEABLE))
            .from(CELLARED_DRINK)
            .join(DRINK).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID)
            .where(DRINK.ID.eq(drinkId)).fetchOneInto(Integer) ?: 0

        create.update(DRINK)
            .set(DRINK.CELLARED_BEERS, cellaredBeers)
            .set(DRINK.CONTAINED_IN_CELLARS, containedInCellars)
            .set(DRINK.TRADABLE_BEERS, forTrade)
            .where(DRINK.ID.eq(drinkId)).execute()
    }

    static void updateOrganizationCounts(Long organizationId, DSLContext create) {
        Integer totalBeers = create.fetchCount(
            create.selectDistinct(DRINK.ID)
                .from(DRINK)
                .where(DRINK.ORGANIZATION_ID.eq(organizationId)))

        Integer containedInCellars = create.fetchCount(
            create.selectDistinct(CELLARED_DRINK.CELLAR_ID)
                .from(CELLARED_DRINK)
                .join(DRINK).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID)
                .where(DRINK.ORGANIZATION_ID.eq(organizationId)))

        Integer cellaredBeers = create.select(DSL.sum(CELLARED_DRINK.QUANTITY))
            .from(CELLARED_DRINK)
            .join(DRINK).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID)
            .where(DRINK.ORGANIZATION_ID.eq(organizationId)).fetchOneInto(Integer) ?: 0

        create.update(ORGANIZATION)
            .set(ORGANIZATION.TOTAL_BEERS, totalBeers)
            .set(ORGANIZATION.CONTAINED_IN_CELLARS, containedInCellars)
            .set(ORGANIZATION.CELLARED_BEERS, cellaredBeers)
            .where(ORGANIZATION.ID.eq(organizationId)).execute()
    }
}
