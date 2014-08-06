package com.cellarhq.ratpack.hibernate

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
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
    @SuppressWarnings('CatchException')
    static <T> Promise<T> transaction(Context context, Closure<T> operation) {
        context.blocking {
            T result = null

            Session session = context.get(SessionFactory).currentSession
            try {
                session.beginTransaction()
                try {
                    result = operation.call()
                    session.flush()
                    session.transaction.commit()
                } catch (Exception e) {
                    log.error('Transaction error:', e)
                    session.transaction.rollback()
                }
            } catch (Exception e) {
                log.error('Error handling transaction:', e)
            }

            return (T) result
        }
    }
}
