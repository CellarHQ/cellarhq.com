package com.cellarhq.jdbi

import com.codahale.metrics.health.HealthCheck
import com.google.inject.Inject
import org.skife.jdbi.v2.DBI
import org.skife.jdbi.v2.Handle
import ratpack.codahale.metrics.NamedHealthCheck

/**
 * Simple ping database health check.
 */
class DatabaseHealthCheck extends NamedHealthCheck {

    @Inject
    DBI dbi

    @Override
    String getName() {
        return 'Database-Health-Check'
    }

    @Override
    protected HealthCheck.Result check() throws Exception {
        Handle h = dbi.open()
        h.execute('select 1')
        h.close()
        return HealthCheck.Result.healthy()
    }
}
