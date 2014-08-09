package com.cellarhq.services

import com.cellarhq.dao.DrinkCategoryDAO
import com.cellarhq.domain.DrinkCategory
import com.google.inject.Inject
import groovy.transform.CompileStatic

@CompileStatic
class DrinkCategoryService {

    private final DrinkCategoryDAO drinkCategoryDAO

    @Inject
    DrinkCategoryService(DrinkCategoryDAO drinkCategoryDAO) {
        this.drinkCategoryDAO = drinkCategoryDAO
    }

    DrinkCategory save(DrinkCategory drinkCategory) {
        drinkCategoryDAO.save(drinkCategory)
    }

    DrinkCategory get(Long id) {
        drinkCategoryDAO.find(id)
    }

    Iterable<DrinkCategory> all() {
        drinkCategoryDAO.findAll()
    }

}
