package com.cellarhq.dao

import com.cellarhq.entities.Cellar
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

    void save(Cellar cellar) {
        persist(cellar)
    }
}
