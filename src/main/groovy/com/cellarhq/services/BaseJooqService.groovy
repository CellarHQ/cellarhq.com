package com.cellarhq.services

import static com.cellarhq.util.DataSourceUtil.withDslContext

import groovy.transform.CompileStatic

import javax.sql.DataSource

@CompileStatic
abstract class BaseJooqService {

    protected final DataSource dataSource

    BaseJooqService(DataSource dataSource) {
        this.dataSource = dataSource
    }

    protected <T> T jooq(Closure<T> operation) {
        return withDslContext(dataSource, operation)
    }

    protected <T> T jooq(Closure configger, Closure<T> operation) {
        return withDslContext(dataSource, configger, operation)
    }
}
