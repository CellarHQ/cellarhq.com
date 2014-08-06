package com.cellarhq.ratpack.hibernate

import static com.google.inject.internal.util.$Preconditions.checkNotNull

import com.cellarhq.util.Generics
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.hibernate.FlushMode
import org.hibernate.HibernateException
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.criterion.DetachedCriteria

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

    AbstractDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory
        this.entityClass = Generics.getTypeParameter(getClass())
    }

    public Session currentSession() {
        sessionFactory.currentSession.with { Session session ->
            flushMode = FlushMode.MANUAL
            return session
        }
    }

    public DetachedCriteria criteria() {
        return DetachedCriteria.forClass(entityClass)
    }

    @SuppressWarnings('unchecked')
    public Class<E> getEntityClass() {
        return (Class<E>) entityClass
    }

    @SuppressWarnings('unchecked')
    protected E uniqueResult(DetachedCriteria criteria) throws HibernateException {
        (E) checkNotNull(criteria.getExecutableCriteria(currentSession())).uniqueResult()
    }

    @SuppressWarnings('unchecked')
    protected Iterable<E> list(DetachedCriteria criteria) throws HibernateException {
        checkNotNull(criteria.getExecutableCriteria(currentSession())).list()
    }

    @SuppressWarnings('unchecked')
    protected E get(Serializable id) {
        (E) currentSession().get(entityClass, checkNotNull(id))
    }

    protected E persist(E entity) throws HibernateException {
        currentSession().saveOrUpdate(checkNotNull(entity))
        entity
    }
}
