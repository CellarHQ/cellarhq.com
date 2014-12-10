package com.cellarhq.functional.builders

import com.cellarhq.domain.Organization
import com.cellarhq.domain.OrganizationType
import com.cellarhq.generated.tables.records.OrganizationRecord
import org.jooq.DSLContext

import static com.cellarhq.generated.Tables.ORGANIZATION


class OrganizationBuilder {
    Organization organization = new Organization(name: 'defaultName',
            type: OrganizationType.BREWERY,
            slug: 'defaultname',
            needsModeration: false,
            warningFlag: false,
            totalBeers: 0,
            cellaredBeers: 0,
            containedInCellars: 0,
            collaboration: 0)

    Organization build(DSLContext create) {
        OrganizationRecord organizationRecord = create.newRecord(ORGANIZATION, organization)

        organizationRecord.reset(ORGANIZATION.DATA)
        organizationRecord.reset(ORGANIZATION.CREATED_DATE)
        organizationRecord.reset(ORGANIZATION.MODIFIED_DATE)
        organizationRecord.reset(ORGANIZATION.ID)
        organizationRecord.store()

        organizationRecord.into(Organization)
    }
}
