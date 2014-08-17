package com.cellarhq.dao

import com.cellarhq.domain.PasswordChangeRequest
import com.cellarhq.ratpack.hibernate.AbstractDAO
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.hibernate.SessionFactory
import org.hibernate.criterion.DetachedCriteria
import org.hibernate.criterion.Restrictions

@CompileStatic
class PasswordChangeRequestDAO extends AbstractDAO<PasswordChangeRequest> {

    @Inject
    PasswordChangeRequestDAO(SessionFactory sessionFactory) {
        super(sessionFactory)
    }

    PasswordChangeRequest save(PasswordChangeRequest passwordChangeRequest) {
        return persist(passwordChangeRequest)
    }

    PasswordChangeRequest find(Serializable id) {
        Calendar calendar = Calendar.instance
        calendar.add(Calendar.HOUR_OF_DAY, -24)

        DetachedCriteria criteria = criteria()
                .add(Restrictions.eq('id', id))
                .add(Restrictions.gt('createdDate', calendar.time))

        return uniqueResult(criteria)
    }
}
