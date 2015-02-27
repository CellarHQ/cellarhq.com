package com.cellarhq.functional

import com.cellarhq.common.CellarHQConfig
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
            conn = DriverManager.getConnection(getJdbcUrl(), cellarHQConfig.databaseUser, cellarHQConfig.databasePassword)
            create = DSL.using(conn, SQLDialect.POSTGRES)
        }
        return create
    }

    CellarHQConfig getCellarHQConfig() {
        remote.exec {
            get(CellarHQConfig)
        }
    }

    private String getJdbcUrl() {
        System.getenv('DB_JDBC_URL') ?:
                "jdbc:postgresql://${cellarHQConfig.databaseServerName}:${cellarHQConfig.databasePortNumber}/${cellarHQConfig.databaseName}?user=${cellarHQConfig.databaseUser}&password=${cellarHQConfig.databasePassword}"
    }
}