package com.cellarhq.jdbi

import com.codahale.metrics.health.HealthCheck
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.skife.jdbi.v2.DBI
import org.skife.jdbi.v2.Handle
import ratpack.codahale.metrics.NamedHealthCheck

/**
 * Simple ping database health check.
 */
@CompileStatic
class DatabaseHealthCheck extends NamedHealthCheck {

    String name = 'Database-Health-Check'

    @Inject
    DBI dbi

    @Override
    protected HealthCheck.Result check() throws Exception {
        Handle h = dbi.open()
        h.execute('select 1')
        h.close()
        return HealthCheck.Result.healthy()
    }
}
