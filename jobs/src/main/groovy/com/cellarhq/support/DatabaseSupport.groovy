package com.cellarhq.support

import groovy.transform.CompileStatic
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL

import java.sql.Connection
import java.sql.DriverManager

/**
 * Adds database support to a command.
 */
trait DatabaseSupport {

    protected DSLContext create
    protected Connection conn

    DSLContext getCreate() {
        if (!create) {
            Class.forName("org.postgresql.ds.PGSimpleDataSource").newInstance()
            conn = DriverManager.getConnection(jdbcUrl, username, password)
            create = DSL.using(conn, SQLDialect.POSTGRES)
        }
        return create
    }

    String getJdbcUrl() {
        System.getProperty('DB_JDBC_URL') ?: 'jdbc:postgresql://localhost:15432/cellarhq'
    }

    String getUsername() {
        System.getProperty('DB_USERNAME') ?: 'cellarhq'
    }

    String getPassword() {
        System.getProperty('DB_PASSWORD') ?: 'cellarhq'
    }
}
