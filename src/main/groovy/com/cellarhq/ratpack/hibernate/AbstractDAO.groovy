package com.cellarhq.ratpack.hibernate

import static com.google.inject.internal.util.$Preconditions.checkNotNull
import static ratpack.rx.RxRatpack.observe
import static ratpack.rx.RxRatpack.observeEach

import com.cellarhq.util.Generics
import groovy.transform.CompileStatic
import org.hibernate.Criteria
import org.hibernate.Hibernate
import org.hibernate.HibernateException
import org.hibernate.Query
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.criterion.DetachedCriteria
import ratpack.exec.ExecControl
import rx.Observable.OnSubscribe
import rx.Subscriber

/**
 * Rx-enabled Hibernate DAO, inspired by Dropwizard.
 *
 * This DAO only provides support for DetachedCriteria, so that the session is opened in the blocking thread.
 */
@CompileStatic
class AbstractDAO<E> {

    private final SessionFactory sessionFactory
    private final Class<?> entityClass
    protected final ExecControl execControl

    AbstractDAO(SessionFactory sessionFactory, ExecControl execControl) {
        this.sessionFactory = sessionFactory
        this.entityClass = Generics.getTypeParameter(getClass())
        this.execControl = execControl
    }

    protected Session currentSession() {
        return sessionFactory.currentSession
    }

    protected DetachedCriteria criteria() {
        return DetachedCriteria.forClass(entityClass)
    }

    @SuppressWarnings('unchecked')
    public Class<E> getEntityClass() {
        return (Class<E>) entityClass
    }

    @SuppressWarnings('unchecked')
    protected rx.Observable<E> uniqueResult(DetachedCriteria criteria) throws HibernateException {
        return observe(execControl.blocking {
            (E) checkNotNull(criteria.getExecutableCriteria(currentSession())).uniqueResult()
        })
    }

    @SuppressWarnings('unchecked')
    protected rx.Observable<Iterable<E>> list(DetachedCriteria criteria) throws HibernateException {
        return observeEach(execControl.blocking {
            checkNotNull(criteria.getExecutableCriteria(currentSession())).list()
        })
    }

    @SuppressWarnings('unchecked')
    protected rx.Observable<E> get(Serializable id) {
        return observe(execControl.blocking {
            (E) currentSession().get(entityClass, checkNotNull(id))
        })
    }

    protected rx.Observable<E> persist(E entity) throws HibernateException {
        return observe(execControl.blocking {
            currentSession().saveOrUpdate(checkNotNull(entity))
            entity
        })
    }
}
