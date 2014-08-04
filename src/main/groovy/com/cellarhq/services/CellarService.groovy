package com.cellarhq.services

import com.cellarhq.dao.CellarDAO
import com.cellarhq.entities.Cellar
import com.google.inject.Inject
import groovy.transform.CompileStatic

@CompileStatic
class CellarService {

    private final CellarDAO cellarDAO

    @Inject
    CellarService(CellarDAO cellarDAO) {
        this.cellarDAO = cellarDAO
    }

    void save(Cellar cellar) {
        cellarDAO.save(cellar)
    }

}
