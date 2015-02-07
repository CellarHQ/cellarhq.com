package com.cellarhq.functional

import com.cellarhq.CellarHQConfig
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
            conn = DriverManager.getConnection(getJdbcUrl(), cellarHQCOnfig.databaseUser, cellarHQCOnfig.databasePassword)
            create = DSL.using(conn, SQLDialect.POSTGRES)
        }
        return create
    }

    CellarHQConfig getCellarHQCOnfig() {
        remote.exec {
            get(CellarHQConfig)
        }
    }

    private String getJdbcUrl() {
        System.getenv('DB_JDBC_URL') ?:
                "jdbc:postgresql://${cellarHQCOnfig.databaseServerName}:${cellarHQCOnfig.databasePortNumber}/${cellarHQCOnfig.databaseName}?user=${cellarHQCOnfig.databaseUser}&password=${cellarHQCOnfig.databasePassword}"
    }
}