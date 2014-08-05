package com.cellarhq.ratpack.liquibase

import com.google.inject.Inject
import com.google.inject.Provider
import groovy.transform.CompileStatic

import javax.sql.DataSource

@CompileStatic
class LiquibaseProvider implements Provider<LiquibaseService> {

    private final DataSource ds

    @Inject
    LiquibaseProvider(DataSource ds) {
        this.ds = ds
    }

    @Override
    LiquibaseService get() {
        return new LiquibaseService(ds)
    }
}
