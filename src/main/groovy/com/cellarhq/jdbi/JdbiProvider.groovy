package com.cellarhq.jdbi

import com.google.inject.Inject
import com.google.inject.Provider
import groovy.transform.CompileStatic
import org.skife.jdbi.v2.DBI

import javax.sql.DataSource

@CompileStatic
class JdbiProvider implements Provider<DBI> {

    private final DataSource ds

    @Inject
    JdbiProvider(DataSource ds) {
        this.ds = ds
    }

    @Override
    DBI get() {
        return new DBI(ds)
    }
}
