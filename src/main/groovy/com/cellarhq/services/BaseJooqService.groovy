package com.cellarhq.services

import static com.cellarhq.util.DataSourceUtil.withDslContext

import groovy.transform.CompileStatic
import ratpack.exec.ExecControl

import javax.sql.DataSource

@CompileStatic
abstract class BaseJooqService {

    protected final DataSource dataSource
    protected final ExecControl execControl

    BaseJooqService(DataSource dataSource, ExecControl execControl) {
        this.dataSource = dataSource
        this.execControl = execControl
    }

    protected <T> T jooq(Closure<T> operation) {
        return withDslContext(dataSource, operation)
    }

    protected <T> T jooq(Closure configger, Closure<T> operation) {
        return withDslContext(dataSource, configger, operation)
    }
}
