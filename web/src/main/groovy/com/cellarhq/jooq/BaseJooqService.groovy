package com.cellarhq.jooq

import com.cellarhq.jooq.listeners.CellarStatsUpdatingListener
import com.cellarhq.jooq.listeners.InputSanitizingListener
import groovy.transform.CompileStatic
import org.jooq.*
import org.jooq.impl.DSL
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultRecordListenerProvider

import javax.sql.DataSource

@CompileStatic
abstract class BaseJooqService {

  protected final DataSource dataSource

  BaseJooqService(DataSource dataSource) {
    this.dataSource = dataSource
  }

  SortField makeSortField(SortCommand sortCommand, TableField defaultSort, Map<String, TableField> fieldMap) {
    SortField sortField
    if (sortCommand && sortCommand.isValid() && fieldMap.containsKey(sortCommand.field)) {
      Field field = fieldMap[sortCommand.field]
      sortField = sortCommand.order == SortCommand.Order.DESC ? field.desc() : field.asc()
    } else {
      sortField = defaultSort.asc()
    }

    return sortField.nullsLast()
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
