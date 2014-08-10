package com.cellarhq.dao

import com.cellarhq.domain.EmailAccount
import com.cellarhq.ratpack.hibernate.AbstractDAO
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.hibernate.SessionFactory
import org.hibernate.criterion.DetachedCriteria
import org.hibernate.criterion.Restrictions

@CompileStatic
class EmailAccountDAO extends AbstractDAO<EmailAccount> {

    @Inject
    EmailAccountDAO(SessionFactory sessionFactory) {
        super(sessionFactory)
    }

    EmailAccount save(EmailAccount account) {
        return persist(account)
    }

    EmailAccount findByEmailAndPassword(String email, String password) {
        DetachedCriteria criteria = criteria()
                .add(Restrictions.eq('email', email))
                .add(Restrictions.eq('password', password))

        return uniqueResult(criteria)
    }
}
