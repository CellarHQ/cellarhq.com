package com.cellarhq.services

import com.cellarhq.jooq.SortCommand
import com.cellarhq.jooq.listeners.CellarStatsUpdatingListener
import com.cellarhq.jooq.listeners.InputSanitizingListener
import groovy.transform.CompileStatic
import org.jooq.Configuration
import org.jooq.DSLContext
import org.jooq.Field
import org.jooq.SQLDialect
import org.jooq.SortField
import org.jooq.TableField
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

    SortField makeSortField(SortCommand sortCommand, TableField defaultSort, Map<String, TableField> fieldMap) {
        if (!sortCommand || !sortCommand.isValid() || !fieldMap.containsKey(sortCommand.field)) {
            return defaultSort.asc()
        }

        Field field = fieldMap[sortCommand.field]
        return sortCommand.order == SortCommand.Order.DESC ? field.desc() : field.asc()
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
