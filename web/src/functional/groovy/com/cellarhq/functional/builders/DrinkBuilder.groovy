package com.cellarhq.functional.builders

import com.cellarhq.domain.Drink
import com.cellarhq.domain.DrinkType
import com.cellarhq.domain.Organization
import com.cellarhq.generated.tables.records.DrinkRecord
import org.jooq.DSLContext

import static com.cellarhq.generated.Tables.DRINK

class DrinkBuilder {

  Map defaultProperties = [name              : 'defaultName',
                           drinkType         : DrinkType.BEER,
                           slug              : 'defaultname',
                           warningFlag       : false,
                           cellaredBeers     : 0,
                           containedInCellars: 0,
                           tradableBeers     : 0]

  Drink drink

  DrinkBuilder() {
    drink = new Drink(defaultProperties)
  }

  DrinkBuilder(Map propertyOverrides) {
    defaultProperties.putAll(propertyOverrides)
    drink = new Drink(propertyOverrides)
    drink.slug = drink.name
    drink.drinkType = DrinkType.BEER.toString()
  }

  DrinkBuilder withOrganization(Organization org) {
    assert org.id
    drink.organizationId = org.id

    return this
  }

  Drink build(DSLContext create) {
    DrinkRecord drinkRecord = create.newRecord(DRINK, drink)

    drinkRecord.reset(DRINK.DATA)
    drinkRecord.reset(DRINK.CREATED_DATE)
    drinkRecord.reset(DRINK.MODIFIED_DATE)

    if (drinkRecord.id) {
      drinkRecord.update()
    } else {
      drinkRecord.reset(DRINK.ID)
      drinkRecord.store()
    }

    drinkRecord.into(Drink)
  }
}
