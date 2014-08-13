package com.cellarhq.ratpack.hibernate

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.hibernate.HibernateException
import org.hibernate.Session
import org.hibernate.SessionFactory
import ratpack.exec.Promise
import ratpack.handling.Context

/**
 * Ratpack Hibernate DSL
 */
@Slf4j
@CompileStatic
abstract class HibernateDSL {

    /**
     * Creates a blocking thread and wraps the given operation in a Hibernate transaction.
     */
    static <T> Promise<T> transaction(Context context, Closure<T> operation) {
        context.blocking {
            return transaction(context.get(SessionFactory), operation)
        }
    }

    /**
     * Creates a transaction assuming the transaction is already within the context of a blocking thread.
     */
    static <T> T transaction(SessionFactory sessionFactory, Closure<T> operation) {
        T result = null

        Session session = sessionFactory.currentSession
        try {
            session.beginTransaction()
            try {
                result = operation.call()
                session.flush()
                session.transaction.commit()
            } catch (HibernateException e) {
                log.error('Transaction error:', e)
                session.transaction.rollback()
            }
        } catch (HibernateException e) {
            log.error('Error handling transaction:', e)
        }

        return (T) result
    }
}
