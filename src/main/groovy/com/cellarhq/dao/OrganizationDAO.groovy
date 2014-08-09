package com.cellarhq.dao

import com.cellarhq.domain.Organization
import com.cellarhq.ratpack.hibernate.AbstractDAO
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.hibernate.SessionFactory

@CompileStatic
class OrganizationDAO extends AbstractDAO<Organization> {

    @Inject
    OrganizationDAO(SessionFactory sessionFactory) {
        super(sessionFactory)
    }

    Organization save(Organization org) {
        return persist(org)
    }

    Organization find(Serializable id) {
        return get(id)
    }

    Iterable<Organization> findAll() {
        return list(criteria())
    }
}
