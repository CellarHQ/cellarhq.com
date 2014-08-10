package com.cellarhq.dao

import com.cellarhq.domain.Style
import com.cellarhq.ratpack.hibernate.AbstractDAO
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.hibernate.SessionFactory

@CompileStatic
class StyleDAO extends AbstractDAO<Style> {

    @Inject
    StyleDAO(SessionFactory sessionFactory) {
        super(sessionFactory)
    }

    Style save(Style style) {
        return persist(style)
    }

    Style find(Serializable id) {
        return get(id)
    }

    Iterable<Style> findAll() {
        return list(criteria())
    }
}
