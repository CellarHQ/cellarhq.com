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
        System.getenv('DB_JDBC_URL')?:"jdbc:postgresql://${getHost()}:${getPort()}/${getName()}?user=${getUser()}&password=${getPassword()}"
    }

    private String getHost() {
        System.getenv('DB_HOST')?:'localhost'
    }

    private String getPort() {
        System.getenv('DB_PORT')?:'15432'
    }

    private String getName() {
        System.getenv('DB_NAME')?:'cellarhq_testing'
    }

    private String getUser() {
        System.getenv('DB_USERNAME')?:'cellarhq'
    }

    private String getPassword() {
        System.getenv('DB_PASSWORD')?:'cellarhq'
    }
}