package com.cellarhq.functional

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL

import java.sql.Connection
import java.sql.DriverManager

trait DatabaseTrait {
    private DSLContext create
    private Connection conn

    DSLContext getCreate() {
        if (!create) {
            Class.forName("org.postgresql.ds.PGSimpleDataSource").newInstance()
            conn = DriverManager.getConnection(jdbcUrl, user, password)
            create = DSL.using(conn, SQLDialect.POSTGRES)
        }
        return create
    }

    private String getJdbcUrl() {
        System.getProperty('ratpack.other.dataSource.jdbcUrl',
                "jdbc:postgresql://${getHost()}:${getPort()}/${getName()}?user=${getUser()}&password=${getPassword()}"
        )
    }

    private String getHost() {
        System.getProperty('ratpack.other.dataSource.serverName', 'localhost')
    }

    private String getPort() {
        System.getProperty('ratpack.other.dataSource.portNumber', '15432')
    }

    private String getName() {
        System.getProperty('ratpack.other.dataSource.databaseName', 'cellarhq_testing')
    }

    private String getUser() {
        System.getProperty('ratpack.other.dataSource.user', 'cellarhq')
    }

    private String getPassword() {
        System.getProperty('ratpack.other.dataSource.password', 'cellarhq')
    }
}