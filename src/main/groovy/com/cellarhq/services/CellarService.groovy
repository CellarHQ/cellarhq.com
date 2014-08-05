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

    rx.Observable<Cellar> save(Cellar cellar) {
        cellarDAO.save(cellar)
    }

    rx.Observable<Cellar> get(Long id) {
        cellarDAO.find(id)
    }

    rx.Observable<Iterable<Cellar>> all() {
        cellarDAO.findAll()
    }

}
