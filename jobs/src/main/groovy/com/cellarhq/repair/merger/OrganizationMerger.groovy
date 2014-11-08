package com.cellarhq.repair.merger

import static com.cellarhq.generated.Tables.ORGANIZATION

import com.cellarhq.commands.ExecutionFailedException
import com.cellarhq.generated.tables.records.OrganizationRecord
import com.cellarhq.support.DryRunSupport
import org.jooq.DSLContext
import org.jooq.Table

import javax.naming.OperationNotSupportedException

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

        throw new OperationNotSupportedException('Organization merger is not complete yet.')

        // TODO Merge drinks
        //  - Fork logic to handle duplicate drinks?
        //  - Will need to update all cellars to point to the new drink
        // TODO Merge records
        // TODO Add old slug to redirects table

        return false
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
