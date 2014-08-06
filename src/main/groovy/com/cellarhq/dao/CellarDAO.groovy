package com.cellarhq.dao

import com.cellarhq.domain.Cellar
import com.cellarhq.ratpack.hibernate.AbstractDAO
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.hibernate.SessionFactory

@CompileStatic
class CellarDAO extends AbstractDAO<Cellar> {

    @Inject
    CellarDAO(SessionFactory sessionFactory) {
        super(sessionFactory)
    }

    Cellar save(Cellar cellar) {
        return persist(cellar)
    }

    Cellar find(Serializable id) {
        return get(id)
    }

    Iterable<Cellar> findAll() {
        return list(criteria())
    }
}
