package com.cellarhq.dao

import com.cellarhq.domain.Drink
import com.cellarhq.ratpack.hibernate.AbstractDAO
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.hibernate.SessionFactory

@CompileStatic
class DrinkDAO extends AbstractDAO<Drink> {

    @Inject
    DrinkDAO(SessionFactory sessionFactory) {
        super(sessionFactory)
    }

    Drink save(Drink drink) {
        return persist(drink)
    }

    Drink find(Serializable id) {
        return get(id)
    }

    Iterable<Drink> findAll() {
        return list(criteria())
    }
}
