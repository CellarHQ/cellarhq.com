package com.cellarhq.dao

import com.cellarhq.entities.Cellar
import com.cellarhq.hibernate.AbstractDAO
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.hibernate.SessionFactory

@CompileStatic
class CellarDAO extends AbstractDAO<Cellar> {

    @Inject
    CellarDAO(SessionFactory sessionFactory) {
        super(sessionFactory)
    }

    void save(Cellar cellar) {
        persist(cellar)
    }
}
