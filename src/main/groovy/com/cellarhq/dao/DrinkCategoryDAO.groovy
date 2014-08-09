package com.cellarhq.dao

import com.cellarhq.domain.DrinkCategory
import com.cellarhq.ratpack.hibernate.AbstractDAO
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.hibernate.SessionFactory

@CompileStatic
class DrinkCategoryDAO extends AbstractDAO<DrinkCategory> {

    @Inject
    DrinkCategoryDAO(SessionFactory sessionFactory) {
        super(sessionFactory)
    }

    DrinkCategory save(DrinkCategory style) {
        return persist(style)
    }

    DrinkCategory find(Serializable id) {
        return get(id)
    }

    Iterable<DrinkCategory> findAll() {
        return list(criteria())
    }
}
