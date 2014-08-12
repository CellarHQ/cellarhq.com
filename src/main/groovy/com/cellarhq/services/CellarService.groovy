package com.cellarhq.services

import com.cellarhq.dao.CellarDAO
import com.cellarhq.domain.Cellar
import com.google.inject.Inject
import groovy.transform.CompileStatic

@CompileStatic
class CellarService {

    private final CellarDAO cellarDAO

    @Inject
    CellarService(CellarDAO cellarDAO) {
        this.cellarDAO = cellarDAO
    }

    Cellar save(Cellar cellar) {
        cellarDAO.save(cellar)
    }

    Cellar get(Long id) {
        cellarDAO.find(id)
    }

    Iterable<Cellar> all() {
        cellarDAO.findAll()
    }

    void updateLoginStats(Cellar cellar) {
        cellar.lastLogin = new Date()
//        cellar.lastLoginIp // TODO  Ratpack remote address support
        cellarDAO.save(cellar)
    }

}
