package com.cellarhq.cleanup

import com.cellarhq.generated.Keys
import com.cellarhq.generated.tables.pojos.Organization
import org.jooq.DSLContext

import static com.cellarhq.generated.Tables.CELLARED_DRINK
import static com.cellarhq.generated.Tables.ORGANIZATION
import static com.cellarhq.generated.Tables.DRINK

class OrphanedDataRemover {
    void deleteOrphans(DSLContext dslContext) {
        List<Long> orphanedDrinks = dslContext.select(DRINK.ID)
            .from(DRINK)
            .leftOuterJoin(CELLARED_DRINK).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID)
            .where(CELLARED_DRINK.ID.isNull()).fetchInto(Long)

        println "Deleting ${orphanedDrinks.size()} drinks"

        if (orphanedDrinks) {
            dslContext.delete(DRINK).where(DRINK.ID.in(orphanedDrinks)).execute()
        }

        List<Long> orphanedOrganizations = dslContext.select(ORGANIZATION.ID)
            .from(ORGANIZATION)
            .leftOuterJoin(DRINK).onKey(Keys.DRINK__FK_DRINK_ORGANIZATION_ID)
            .where(DRINK.ID.isNull()).fetchInto(Long)

        println "Deleting ${orphanedOrganizations.size()} organizations"

        if (orphanedOrganizations) {
            dslContext.delete(ORGANIZATION).where(ORGANIZATION.ID.in(orphanedOrganizations)).execute()
        }


    }
}
