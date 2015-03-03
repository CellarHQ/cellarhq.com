package com.cellarhq.health

import com.codahale.metrics.health.HealthCheck
import com.google.inject.Inject
import groovy.transform.CompileStatic
import ratpack.codahale.healthcheck.NamedHealthCheck

import javax.sql.DataSource

@CompileStatic
class DatabaseHealthcheck extends NamedHealthCheck {

    private final DataSource dataSource

    final String name = 'database'

    @Inject
    DatabaseHealthcheck(DataSource dataSource) {
        this.dataSource = dataSource
    }

    @Override
    protected HealthCheck.Result check() throws Exception {
        return dataSource.connection.isValid(0) ?
                HealthCheck.Result.healthy() :
                HealthCheck.Result.unhealthy('Database connection is invalid')
    }
}
