package com.cellarhq.dao

import com.cellarhq.domain.Cellar
import com.cellarhq.ratpack.hibernate.AbstractDAO
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.hibernate.SessionFactory
import ratpack.exec.ExecControl

@CompileStatic
class CellarDAO extends AbstractDAO<Cellar> {

    @Inject
    CellarDAO(SessionFactory sessionFactory, ExecControl execControl) {
        super(sessionFactory, execControl)
    }

    rx.Observable<Cellar> save(Cellar cellar) {
        return persist(cellar)
    }

    rx.Observable<Cellar> find(Serializable id) {
        return get(id)
    }

    rx.Observable<Iterable<Cellar>> findAll() {
        return list(criteria())
    }
}
