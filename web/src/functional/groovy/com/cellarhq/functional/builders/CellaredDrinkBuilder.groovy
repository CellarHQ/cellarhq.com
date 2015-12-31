package com.cellarhq.functional.builders

import com.cellarhq.domain.Cellar
import com.cellarhq.domain.CellaredDrink
import com.cellarhq.domain.Drink
import com.cellarhq.generated.tables.records.CellaredDrinkRecord
import org.jooq.DSLContext

import static com.cellarhq.generated.Tables.CELLARED_DRINK

class CellaredDrinkBuilder {
  Map defaultProperties = [
    quantity : 0,
    tradeable: false
  ]

  CellaredDrink cellaredDrink

  CellaredDrinkBuilder() {
    cellaredDrink = new CellaredDrink(defaultProperties)
  }

  CellaredDrinkBuilder(Map propertyOverrides) {
    cellaredDrink = new CellaredDrink(defaultProperties << propertyOverrides)
  }

  CellaredDrinkBuilder withCellar(Cellar cellar) {
    assert cellar.id
    cellaredDrink.cellarId = cellar.id
    return this
  }

  CellaredDrinkBuilder withDrink(Drink drink) {
    assert drink.id
    cellaredDrink.drinkId = drink.id
    return this
  }

  CellaredDrink build(DSLContext dslContext) {
    CellaredDrinkRecord drinkRecord = dslContext.newRecord(CELLARED_DRINK, cellaredDrink)
    drinkRecord.reset(CELLARED_DRINK.ID)
    drinkRecord.reset(CELLARED_DRINK.CREATED_DATE)
    drinkRecord.reset(CELLARED_DRINK.MODIFIED_DATE)

    drinkRecord.store()
    drinkRecord.into(CellaredDrink)
  }
}
