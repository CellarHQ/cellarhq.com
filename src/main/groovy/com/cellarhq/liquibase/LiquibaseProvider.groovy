package com.cellarhq.liquibase

import com.google.inject.Inject
import com.google.inject.Provider

import javax.sql.DataSource

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
