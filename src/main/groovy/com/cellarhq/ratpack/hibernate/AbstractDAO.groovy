package com.cellarhq.ratpack.hibernate

import static com.google.inject.internal.util.$Preconditions.checkNotNull
import static ratpack.rx.RxRatpack.observe
import static ratpack.rx.RxRatpack.observeEach

import com.cellarhq.util.Generics
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.hibernate.FlushMode
import org.hibernate.HibernateException
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.criterion.DetachedCriteria
import ratpack.exec.ExecControl

/**
 * Rx-enabled Hibernate DAO, inspired by Dropwizard.
 *
 * This DAO only provides support for DetachedCriteria, so that the session is opened in the blocking thread.
 */
@Slf4j
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
        sessionFactory.currentSession.with { Session session ->
            flushMode = FlushMode.MANUAL
            return session
        }
    }

    protected DetachedCriteria criteria() {
        return DetachedCriteria.forClass(entityClass)
    }

    @SuppressWarnings('CatchException')
    protected <U> U transaction(Closure<U> c) {
        U result = null
        try {
            currentSession().beginTransaction()
            result = c()
            currentSession().flush()
            currentSession().transaction.commit()
        } catch (Exception e) {
            log.error('Transaction failed:', e)
            currentSession().transaction.rollback()
        }

        return result
    }

    @SuppressWarnings('unchecked')
    public Class<E> getEntityClass() {
        return (Class<E>) entityClass
    }

    @SuppressWarnings('unchecked')
    protected rx.Observable<E> uniqueResult(DetachedCriteria criteria) throws HibernateException {
        return observe(execControl.blocking {
            transaction {
                (E) checkNotNull(criteria.getExecutableCriteria(currentSession())).uniqueResult()
            }
        })
    }

    @SuppressWarnings('unchecked')
    protected rx.Observable<Iterable<E>> list(DetachedCriteria criteria) throws HibernateException {
        return observeEach(execControl.blocking {
            transaction {
                checkNotNull(criteria.getExecutableCriteria(currentSession())).list()
            }
        })
    }

    @SuppressWarnings('unchecked')
    protected rx.Observable<E> get(Serializable id) {
        return observe(execControl.blocking {
            transaction {
                (E) currentSession().get(entityClass, checkNotNull(id))
            }
        })
    }

    protected rx.Observable<E> persist(E entity) throws HibernateException {
        return observe(execControl.blocking {
            transaction {
                currentSession().saveOrUpdate(checkNotNull(entity))
                entity
            }
        })
    }
}
