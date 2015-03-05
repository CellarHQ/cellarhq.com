package com.cellarhq.repair.merger

import com.cellarhq.commands.ExecutionFailedException
import com.cellarhq.generated.tables.records.DrinkRecord
import com.cellarhq.jooq.CellarStatsUpdater
import com.cellarhq.support.DryRunSupport
import groovy.transform.CompileStatic
import org.jooq.DSLContext
import org.jooq.Table
import org.jooq.exception.DataAccessException

import static com.cellarhq.generated.Tables.CELLARED_DRINK
import static com.cellarhq.generated.Tables.DRINK

@CompileStatic
class DrinkMerger implements Merger<DrinkRecord>, DryRunSupport {

    DSLContext create

    DrinkMerger(DSLContext create) {
        this.create = create
    }

    @Override
    Map<DrinkRecord, DrinkRecord> conflicts() {
        Table t1 = DRINK.as('t1')
        Table t2 = DRINK.as('t2')

        // only looks for drinks with the same name within the same organization
        List<Long> ids = create.selectDistinct(t1.ID)
                .from(t1)
                .join(t2).on((t1.NAME.equalIgnoreCase(t2.NAME)
                            .or(t1.SLUG.eq(t2.SLUG))
                            .or(t1.BREWERY_DB_ID.eq(t2.BREWERY_DB_ID)))
                            .and(t1.ORGANIZATION_ID.eq(t2.ORGANIZATION_ID)))
                .where(t1.ID.notEqual(t2.ID))
                .fetchInto(Long).unique()

        List<DrinkRecord> records = create.selectFrom(DRINK).where(DRINK.ID.in(ids)).fetchInto(DrinkRecord)

        return (Map<DrinkRecord, DrinkRecord>) buildConflictMap(records, [DRINK.NAME, DRINK.SLUG, DRINK.BREWERY_DB_ID]) { DrinkRecord left, DrinkRecord right ->
            left.organizationId == right.organizationId && left.id != right.id
        }
    }

    @Override
    boolean merge(DrinkRecord source, DrinkRecord target) {
        try {
            if (!source || !target) {
                throw new ExecutionFailedException(
                        "One of the drinks does not exist (source: ${source.id}, target: ${target.id}")
            }

            if (source == target) {
                throw new ExecutionFailedException(
                        "Source and target may not be the same (source: ${source.id}, target: ${target.id}")
            }

            if (source.organizationId != target.organizationId) {
                throw new ExecutionFailedException(
                        "Try to merge organization ${source.organizationId} to ${target.organizationId}")
            }

            if (source.id == target.id) {
                throw new ExecutionFailedException(
                        "Cannot merge source into target at the same id (source: ${source.id}, target: ${target.id}")
            }

            int changedRecords = moveCellaredDrinksFromTargetToSource(source, target)

            println "Updated target ${target.id} and moved ${changedRecords} beers"

            mergeSourceIntoTarget(source, target)
            deleteSourceOrganization(source)
            new CellarStatsUpdater().updateDrinkCounts(target.id, create)
        } catch (DataAccessException dae) {
            println "Error merging source: ${source}, target: ${target}."
            println "Data may be in an inconsistant state for these."
            dae.printStackTrace()
            return false
        } catch (ExecutionFailedException efe) {
            println "Error merging source: ${source.id}, target: ${target.id}. ${efe.message}"
            return false
        }

        return true
    }

    void mergeSourceIntoTarget(DrinkRecord source, DrinkRecord target) {
        target.name = target.name ?: source.name
        target.abv =  target.abv ?:  source.abv
        target.availability = target.availability ?: source.availability
        target.breweryDbId = target.breweryDbId ?: source.breweryDbId
        target.breweryDbLastUpdated = target.breweryDbLastUpdated ?: source.breweryDbLastUpdated
        target.drinkType = target.drinkType ?: source.drinkType
        target.glasswareId = target.glasswareId ?: source.glasswareId
        target.ibu = target.ibu ?: source.ibu
        target.slug = target.slug ?: source.slug
        target.srm = target.srm ?: source.srm
        target.styleId = target.styleId ?: source.styleId
        target.description = target.description ?: source.description
        target.photoId = target.photoId ?: source.photoId

        target.store()
    }

    void deleteSourceOrganization(DrinkRecord source) {
        create.delete(DRINK).where(DRINK.ID.eq(source.id)).execute()
    }

    int moveCellaredDrinksFromTargetToSource(DrinkRecord source, DrinkRecord target) {
        create.update(CELLARED_DRINK)
                .set(CELLARED_DRINK.DRINK_ID, target.id)
                .where(CELLARED_DRINK.DRINK_ID.eq(source.id))
                .execute()

    }

    @Override
    List<DrinkRecord> detectSourceAndTarget(DrinkRecord a, DrinkRecord b) {
        // error condition that is handled down stream, want to avoid user input
        if (a.id == b.id) {
            return [a, b]
        }


        if (a.organizationId != b.organizationId) {
            return [a, b]
        }

        if (a.breweryDbId && !b.breweryDbId) {
            return [b, a]
        } else if (b.breweryDbId && !a.breweryDbId) {
            return [a, b]
        } else if (a.breweryDbId && b.breweryDbId) {
            if (a.breweryDbLastUpdated > b.breweryDbLastUpdated) {
                return [b, a]
            } else {
                return [a, b]
            }
        }

        return []
    }
}

