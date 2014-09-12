package com.cellarhq.util

import groovy.transform.CompileStatic
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.jooq.tools.jdbc.JDBCUtils

import javax.sql.DataSource
import java.sql.Connection

@SuppressWarnings('JdbcConnectionReference')
@CompileStatic
abstract class DataSourceUtil {

    /**
     * Auto-manages closing the JDBC connection used with jOOQ. This DSL will not work with lazy fetching and the
     * connection will need to managed manually.
     */
    static <T> T withDslContext(DataSource ds, Closure<T> operation) {
        Connection conn = ds.connection
        T result = operation.call(DSL.using(conn, JDBCUtils.dialect(conn)))
        conn.close()
        return result
    }

    /**
     * Similar to withDslContext(D, C), but allows passing in a configuration to provide record mappers and so-on.
     */
    static <T> T withDslContext(DataSource ds, Closure configger, Closure<T> operation) {
        Connection conn = ds.connection
        DSLContext dsl = DSL.using(conn, JDBCUtils.dialect(conn))
        configger.call(dsl.configuration())
        T result = operation.call(dsl)
        conn.close()
        return result
    }
}