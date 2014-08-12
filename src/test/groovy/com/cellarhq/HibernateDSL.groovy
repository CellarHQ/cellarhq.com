package com.cellarhq

import org.hibernate.Session
import org.hibernate.SessionFactory

/**
 * Test-friendly hibernate DSL. It does not use promises or setup a blocking thread.
 */
abstract class HibernateDSL {

    static <T> T transaction(SessionFactory sessionFactory, Closure<T> operation) {
        T result = null

        Session session = sessionFactory.currentSession
        session.beginTransaction()
        result = operation.call()
        session.flush()
        session.transaction.commit()

        return result
    }
}
