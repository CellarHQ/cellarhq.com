package com.cellarhq.hibernate

import com.codahale.metrics.health.HealthCheck
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import ratpack.codahale.metrics.NamedHealthCheck

/**
 * Database health check.
 */
@CompileStatic
class SessionFactoryHealthCheck extends NamedHealthCheck {

    String name = 'Database-Health-Check'

    @Inject
    SessionFactory sessionFactory

    @SuppressWarnings(['VariableName', 'CatchException'])
    @Override
    protected HealthCheck.Result check() throws Exception {
        final Session session = sessionFactory.openSession()
        try {
            final Transaction txn = session.beginTransaction()
            try {
                session.createSQLQuery('select 1').list()
                txn.commit()
            } catch (Exception e) {
                if (txn.active) {
                    txn.rollback()
                }
                throw e
            }
        } finally {
            session.close()
        }
        return HealthCheck.Result.healthy()
    }
}
