package com.cellarhq.services

import com.cellarhq.jooq.listeners.CellarStatsUpdatingListener
import com.cellarhq.jooq.listeners.InputSanitizingListener
import groovy.transform.CompileStatic
import org.jooq.Configuration
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultRecordListenerProvider
import ratpack.exec.ExecControl

import javax.sql.DataSource

@CompileStatic
abstract class BaseJooqService {

    protected final DataSource dataSource
    protected final ExecControl execControl

    BaseJooqService(DataSource dataSource, ExecControl execControl) {
        this.dataSource = dataSource
        this.execControl = execControl
    }

    final <T> T jooq(Closure<T> operation) {
        return execute(operation)
    }

    final <T> T jooq(Closure configger, Closure<T> operation) {
        return execute(operation, configger)
    }

    private <T> T execute(Closure<T> operation, Closure extraConfig = null) {
        DSLContext dsl = DSL.using(makeConfiguration(extraConfig))
        T result = operation.call(dsl)
        return result
    }

    private Configuration makeConfiguration(Closure extraConfig = null) {
        Configuration configuration = new DefaultConfiguration().set(dataSource).set(SQLDialect.POSTGRES)
        configuration.set(new DefaultRecordListenerProvider(InputSanitizingListener.instance))
        configuration.set(new DefaultRecordListenerProvider(new CellarStatsUpdatingListener()))

        if (extraConfig) {
            extraConfig.call(configuration)
        }

        return configuration
    }
}
