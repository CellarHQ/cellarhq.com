package com.cellarhq.webapp

import com.cellarhq.domain.views.HomepageStatistics
import groovy.transform.CompileStatic
import org.jooq.Record
import org.jooq.RecordMapper
import org.jooq.RecordMapperProvider
import org.jooq.RecordType

@CompileStatic
class HomepageStatisticsRecordMapperProvider implements RecordMapperProvider {

  @Override
  <R extends Record, E> RecordMapper<R, E> provide(RecordType<R> recordType, Class<? extends E> type) {
    return new HomepageStatisticsRecordMapper<R, E>()
  }

  private class HomepageStatisticsRecordMapper<R extends Record, E> implements RecordMapper<R, E> {

    @Override
    E map(R record) {
      HomepageStatistics stats = new HomepageStatistics(
        organizations: record.getValue('organizations', Integer),
        drinks: record.getValue('drinks', Integer),
        cellars: record.getValue('cellars', Integer),
        cellaredDrinks: record.getValue('cellaredDrinks', Integer) ?: 0
      )

      return (E) stats
    }
  }
}
