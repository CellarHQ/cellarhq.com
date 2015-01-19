package com.cellarhq.repair.merger

import com.cellarhq.jooq.CellarStatsUpdater
import org.jooq.exception.DataAccessException

import static com.cellarhq.generated.Tables.ORGANIZATION
import static com.cellarhq.generated.Tables.DRINK

import com.cellarhq.commands.ExecutionFailedException
import com.cellarhq.generated.tables.records.OrganizationRecord
import com.cellarhq.support.DryRunSupport
import org.jooq.DSLContext
import org.jooq.Table

class OrganizationMerger implements Merger<OrganizationRecord>, DryRunSupport {

    DSLContext create

    OrganizationMerger(DSLContext create) {
        this.create = create
    }

    @Override
    Map<OrganizationRecord, OrganizationRecord> conflicts() {
        Table t1 = ORGANIZATION.as('t1')
        Table t2 = ORGANIZATION.as('t2')

        List<OrganizationRecord> records = create.select(t1.fields())
                .from(t1)
                .join(t2).on(t1.NAME.equalIgnoreCase(t2.NAME))
                .where(t1.ID.notEqual(t2.ID))
                .fetchInto(OrganizationRecord)

        return (Map<OrganizationRecord, OrganizationRecord>) buildConflictMap(records, ORGANIZATION.NAME)
    }

    @Override
    boolean merge(OrganizationRecord source, OrganizationRecord target) {
        if (!source || !target) {
            throw new ExecutionFailedException(
                    "One of the organizations does not exist (source: ${source}, target: ${target}")
        }

        if (source == target) {
            throw new ExecutionFailedException(
                    "Source and target may not be the same (source: ${source}, target: ${target}")
        }

        try {
            int changedRecords = moveDrinksFromTargetToSource(source, target)

            println "Updated target ${target.id} and moved ${changedRecords} beers"

            mergeSourceIntoTarget(source, target)
            deleteSourceOrganization(source)
            new CellarStatsUpdater().updateOrganizationCounts(target.id, create)
        } catch (DataAccessException dae) {
            println "Error merging source: ${source}, target: ${target}."
            println "Data may be in an inconsistant state for these."
            dae.printStackTrace()
            return false
        }


        // TODO Add old slug to redirects table

        return true
    }

    void mergeSourceIntoTarget(OrganizationRecord source, OrganizationRecord target) {

        target.address = target.address ?: source.address
        target.address2 = target.address2 ?: source.address2
        target.country = target.country ?: source.country
        target.description = target.description ?: source.description
        target.established = target.established ?: source.established
        target.locality = target.locality ?: source.locality
        target.localitySort = target.localitySort ?: source.localitySort
        target.phone = target.phone ?: source.phone
        target.photoId = target.photoId ?: source.photoId
        target.postalCode = target.postalCode ?: source.postalCode
        target.region = target.region ?: source.region
        target.website = target.website ?: source.website

        target.store()
    }

    void deleteSourceOrganization(OrganizationRecord source) {
        create.delete(ORGANIZATION).where(ORGANIZATION.ID.eq(source.id)).execute()
    }

    int moveDrinksFromTargetToSource(OrganizationRecord source, OrganizationRecord target) {
        create.update(DRINK)
                .set(DRINK.ORGANIZATION_ID, target.id)
                .where(DRINK.ORGANIZATION_ID.eq(source.id))
                .execute()

    }

    @Override
    List<OrganizationRecord> detectSourceAndTarget(OrganizationRecord a, OrganizationRecord b) {
        if (a.breweryDbId && !b.breweryDbId) {
            return [b, a]
        } else if (b.breweryDbId && !b.breweryDbId) {
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
