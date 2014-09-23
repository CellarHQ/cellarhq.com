package com.cellarhq.domain.views

import com.cellarhq.domain.CellaredDrink
import groovy.transform.CompileStatic

import java.time.LocalDate

/**
 * View model for a Cellar's drink list.
 */
@CompileStatic
class CellaredDrinkDetails extends CellaredDrink {

    final static int DRINK_BY_WARN_DAYS = 30
    final static int DRINK_BY_DANGER_DAYS = 14

    String organizationName
    String organizationSlug

    String drinkName
    String drinkSlug

    String styleName

    boolean getWarnDrinkByDate() {
        return drinkByDate &&
                LocalDate.now().isAfter(drinkByDate?.minusDays(DRINK_BY_WARN_DAYS)) &&
                !dangerDrinkByDate
    }

    boolean getDangerDrinkByDate() {
        return drinkByDate &&
                LocalDate.now().isAfter(drinkByDate?.minusDays(DRINK_BY_DANGER_DAYS))
    }

    boolean getHasDetails() {
        return notes || binIdentifier || dateAcquired || drinkByDate || tradeable
    }
}
