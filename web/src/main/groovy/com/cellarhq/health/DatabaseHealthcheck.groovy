package com.cellarhq.health

import com.codahale.metrics.health.HealthCheck
import com.google.inject.Inject
import groovy.transform.CompileStatic
import ratpack.exec.Promise

import javax.sql.DataSource

@CompileStatic
class DatabaseHealthcheck implements ratpack.health.HealthCheck {

    private final DataSource dataSource

    final String name = 'database'

    @Inject
    DatabaseHealthcheck(DataSource dataSource) {
        this.dataSource = dataSource
    }

    Promise<HealthCheck.Result>  check(ratpack.exec.ExecControl execControl) throws Exception {
        return execControl.promiseOf(dataSource.connection.isValid(0) ?
                HealthCheck.Result.healthy() :
                HealthCheck.Result.unhealthy('Database connection is invalid'))
    }
}
