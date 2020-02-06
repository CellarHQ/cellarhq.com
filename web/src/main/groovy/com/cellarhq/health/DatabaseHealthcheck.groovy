package com.cellarhq.health

import com.google.inject.Inject
import ratpack.exec.Operation
import ratpack.exec.Promise
import ratpack.health.HealthCheck
import ratpack.registry.Registry

import javax.sql.DataSource

class DatabaseHealthcheck implements ratpack.health.HealthCheck {

  private final DataSource dataSource

  final String name = 'database'

  @Inject
  DatabaseHealthcheck(DataSource dataSource) {
    this.dataSource = dataSource
  }

  @Override
  Promise<ratpack.health.HealthCheck.Result> check(Registry registry) throws Exception {
    Operation.of { dataSource.connection.isValid(0) }.promise()
             .map { HealthCheck.Result.healthy() }
            .mapError { HealthCheck.Result.unhealthy('Database connection is invalid') }

  }
}
