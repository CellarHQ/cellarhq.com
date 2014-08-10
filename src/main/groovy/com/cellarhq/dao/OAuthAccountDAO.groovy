package com.cellarhq.dao

import static com.google.inject.internal.util.$Preconditions.checkNotNull

import com.cellarhq.domain.OAuthAccount
import com.cellarhq.domain.OAuthAccount.Client
import com.cellarhq.ratpack.hibernate.AbstractDAO
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.hibernate.SessionFactory
import org.hibernate.criterion.DetachedCriteria
import org.hibernate.criterion.Projections
import org.hibernate.criterion.Restrictions

@CompileStatic
class OAuthAccountDAO extends AbstractDAO<OAuthAccount> {

    @Inject
    OAuthAccountDAO(SessionFactory sessionFactory) {
        super(sessionFactory)
    }

    OAuthAccount save(OAuthAccount account) {
        return persist(account)
    }

    OAuthAccount findByClientAndUsername(Client client, String username) {
        DetachedCriteria criteria = criteria()
                .add(Restrictions.eq('client', client))
                .add(Restrictions.eq('username', username))
        return uniqueResult(criteria)
    }

    long countAll() {
        DetachedCriteria criteria = criteria()
                .setProjection(Projections.rowCount())
        return (long) checkNotNull(criteria.getExecutableCriteria(currentSession())).uniqueResult()
    }
}
