package com.cellarhq.services

import com.cellarhq.dao.DrinkDAO
import com.cellarhq.domain.Drink
import com.google.inject.Inject
import groovy.transform.CompileStatic

@CompileStatic
class DrinkService {

    private final DrinkDAO drinkDAO

    @Inject
    DrinkService(DrinkDAO drinkDAO) {
        this.drinkDAO = drinkDAO
    }

    Drink save(Drink drink) {
        drinkDAO.save(drink)
    }

    Drink get(Long id) {
        drinkDAO.find(id)
    }

    Iterable<Drink> all() {
        drinkDAO.findAll()
    }

}
