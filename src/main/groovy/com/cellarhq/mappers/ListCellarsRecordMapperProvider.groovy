package com.cellarhq.mappers

import com.cellarhq.domain.Cellar
import groovy.transform.CompileStatic
import org.jooq.Record
import org.jooq.RecordMapper
import org.jooq.RecordMapperProvider
import org.jooq.RecordType
import org.jooq.impl.DefaultRecordMapper

/**
 * @todo Keeping this around for reference, for when we inevitably need to do some record mapping of some sort.
 *       This class, however, is not used anymore.
 */
@Deprecated
@CompileStatic
class ListCellarsRecordMapperProvider implements RecordMapperProvider {

    @Override
    def <R extends Record, E> RecordMapper<R, E> provide(RecordType<R> recordType, Class<? extends E> type) {
        return new ListCellarsRecordMapper<R, E>(new DefaultRecordMapper<R, E>(recordType, type))
    }

    private class ListCellarsRecordMapper<R extends Record, E> implements RecordMapper<R, E> {

        private final DefaultRecordMapper<R, E> defaultRecordMapper

        ListCellarsRecordMapper(DefaultRecordMapper defaultRecordMapper) {
            this.defaultRecordMapper = defaultRecordMapper
        }

        @Override
        E map(R record) {
            Cellar mapped = (Cellar) defaultRecordMapper.map(record)

//            mapped.totalBeers = record.getValue('totalBeers', Integer)
//            mapped.uniqueBeers = record.getValue('uniqueBeers', Integer)
//            mapped.breweries = record.getValue('breweries', Integer)
//            mapped.trading = record.getValue('trading', Boolean)

            return (E) mapped
        }
    }
}
