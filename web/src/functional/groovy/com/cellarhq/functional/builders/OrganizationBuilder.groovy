package com.cellarhq.functional.builders

import com.cellarhq.domain.Drink
import com.cellarhq.domain.DrinkType
import com.cellarhq.domain.Organization
import com.cellarhq.domain.OrganizationType
import com.cellarhq.generated.tables.records.OrganizationRecord
import org.jooq.DSLContext

import static com.cellarhq.generated.Tables.ORGANIZATION


class OrganizationBuilder {
  Map defaultProperties = [ name: 'defaultName',
    type: OrganizationType.BREWERY,
    slug: 'defaultname',
    needsModeration: false,
    warningFlag: false,
    totalBeers: 0,
    cellaredBeers: 0,
    containedInCellars: 0,
    collaboration: 0
  ]

  Organization organization

  OrganizationBuilder() {
    organization = new Organization(defaultProperties)
  }

  OrganizationBuilder(Map propertyOverrides) {
    defaultProperties.putAll(propertyOverrides)
    organization = new Organization(propertyOverrides)
    organization.slug = organization.name
    organization.type = OrganizationType.BREWERY.toString()
  }

  Organization build(DSLContext create) {
    OrganizationRecord organizationRecord = create.newRecord(ORGANIZATION, organization)

    organizationRecord.with {
      reset(ORGANIZATION.DATA)
      reset(ORGANIZATION.CREATED_DATE)
      reset(ORGANIZATION.MODIFIED_DATE)
      reset(ORGANIZATION.ID)
      store()
    }

    organizationRecord.into(Organization)
  }
}
