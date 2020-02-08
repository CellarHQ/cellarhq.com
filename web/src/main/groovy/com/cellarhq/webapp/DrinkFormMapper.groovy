package com.cellarhq.webapp

import com.cellarhq.domain.Drink
import com.cellarhq.domain.DrinkType
import ratpack.form.Form

class DrinkFormMapper {
  static Drink applyFrom(Drink drink, Form form) {
    if (!drink.id) {
      drink.with {
        drinkType = DrinkType.BEER
        slug = form.name
        name = form.name
        organizationId = form.organizationId.toLong()
      }
    }

    drink.with {
      description = form.description

      if (form.styleId) {
        styleId = form.styleId.toLong()
      }
      if (form.glasswareId) {
        glasswareId = form.glasswareId.toLong()
      }
      if (form.srm) {
        srm = form.srm.toInteger()
      }
      if (form.ibu) {
        ibu = form.ibu.toInteger()
      }
      if (form.abv) {
        abv = form.abv.toBigDecimal()
      }
      if (form.availability) {
        availability = form.availability
      }

      needsModeration = true
    }
    return drink
  }
}
