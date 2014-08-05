package com.cellarhq.ratpack.hibernate

import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.hibernate.Session
import org.hibernate.SessionFactory
import ratpack.exec.ExecInterceptor
import ratpack.func.Action
import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.http.Request

/**
 * EXPERIMENTAL: Not being used yet!
 *
 * Creates a Hibernate transaction that wraps an execution, automatically flushing at the end.
 */
@Slf4j
class TransactionalHandler implements Handler {

    @Inject
    SessionFactory sessionFactory

    @Override
    void handle(Context exchange) throws Exception {
        exchange.addExecInterceptor(new HibernateTransactionExecInterceptor(exchange.request), new Action<Context>() {
            void execute(Context context) {
                context.next()
            }
        })
    }

    private class HibernateTransactionExecInterceptor implements ExecInterceptor {

        private final Request request

        HibernateTransactionExecInterceptor(Request request) {
            this.request = request
            request.register(sessionFactory.currentSession)
        }

        @SuppressWarnings('CatchException')
        @Override
        void intercept(ExecInterceptor.ExecType execType, Runnable continuation) {
            Session session = request.get(Session)
            try {
                session.beginTransaction()
                continuation.run()
                session.flush()
                session.transaction.commit()
            } catch (Exception e) {
                log.error('Transactional Error (Rolling back):', e)
                session.transaction.rollback()
            } finally {
                session.close()
            }
        }
    }
}
