package com.cellarhq.dao

import com.cellarhq.domain.Glassware
import com.cellarhq.ratpack.hibernate.AbstractDAO
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.hibernate.SessionFactory

@CompileStatic
class GlasswareDAO extends AbstractDAO<Glassware> {

    @Inject
    GlasswareDAO(SessionFactory sessionFactory) {
        super(sessionFactory)
    }

    Glassware save(Glassware glassware) {
        return persist(glassware)
    }

    Glassware find(Serializable id) {
        return get(id)
    }

    Iterable<Glassware> findAll() {
        return list(criteria())
    }
}
