package com.cellarhq.dbimport.simpledb

import com.cellarhq.generated.tables.records.DrinkRecord
import com.cellarhq.generated.tables.records.OrganizationRecord
import com.github.slugify.Slugify
import org.jooq.DSLContext

import static com.cellarhq.generated.Tables.DRINK
import static com.cellarhq.generated.Tables.ORGANIZATION


class LastResort {
  DrinkRecord insertDrink(String name, Integer organizationId, DSLContext dslContext) {
    DrinkRecord newDrink = dslContext.newRecord(DRINK)

    newDrink.name = name
    newDrink.slug = new Slugify().slugify(newDrink.name)
    newDrink.drinkType = 'BEER'
    newDrink.organizationId = organizationId
    newDrink.warningFlag = true
    newDrink.tradableBeers = 0
    newDrink.cellaredBeers = 0
    newDrink.containedInCellars = 0

    newDrink.store()

    return newDrink
  }

  OrganizationRecord insertOrganization(String name, DSLContext dslContext) {
    OrganizationRecord newOrganization = dslContext.newRecord(ORGANIZATION)

    newOrganization.name = name
    newOrganization.slug = new Slugify().slugify(newOrganization.name)
    newOrganization.type = 'BREWERY'
    newOrganization.warningFlag = true
    newOrganization.totalBeers = 0
    newOrganization.cellaredBeers = 0
    newOrganization.containedInCellars = 0

    newOrganization.store()

    return newOrganization
  }
}
