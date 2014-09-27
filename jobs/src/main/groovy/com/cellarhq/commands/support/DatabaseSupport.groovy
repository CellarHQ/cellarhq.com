package com.cellarhq.commands.support

import groovy.transform.CompileStatic
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL

import java.sql.Connection
import java.sql.DriverManager

/**
 * Adds database support to a command.
 */
@CompileStatic
trait DatabaseSupport {

    private DSLContext create

    DSLContext getCreate() {
        if (!create) {
            // TODO Make configurable. Perhaps ENVARS
            String userName = 'cellarhq'
            String password = 'cellarhq'
            String url = 'dbc:postgresql://localhost:15432/cellarhq'

            Class.forName("org.postgresql.ds.PGSimpleDataSource").newInstance()
            Connection conn = DriverManager.getConnection(url, userName, password)
            create = DSL.using(conn, SQLDialect.POSTGRES)
        }
        return create
    }
}
