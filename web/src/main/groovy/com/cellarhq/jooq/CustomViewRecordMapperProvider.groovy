package com.cellarhq.jooq

import com.cellarhq.util.LogUtil
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.jooq.Record
import org.jooq.RecordMapper
import org.jooq.RecordMapperProvider
import org.jooq.RecordType
import org.jooq.impl.DefaultRecordMapper

/**
 * Does naive record mapping for custom view records, first by mapping using the default jOOQ record mapper, then
 * checking the mapped object for properties of the same name in the record.
 */
@Slf4j
@CompileStatic
class CustomViewRecordMapperProvider implements RecordMapperProvider {

    /**
     * Key: Aliased column name
     * Value: The Java type the data should be cast to
     */
    private final Map<String, Class> aliasedFields

    CustomViewRecordMapperProvider(Map<String, Class> aliasedFields) {
        this.aliasedFields = aliasedFields
    }

    @Override
    <R extends Record, E> RecordMapper<R, E> provide(RecordType<R> recordType, Class<? extends E> type) {
        DefaultRecordMapper defaultRecordMapper = new DefaultRecordMapper(recordType, type)
        return new AliasedFieldRecordMapper<R, E>(defaultRecordMapper)
    }

    private class AliasedFieldRecordMapper<R extends Record, E> implements RecordMapper<R, E> {

        private final DefaultRecordMapper defaultRecordMapper

        AliasedFieldRecordMapper(DefaultRecordMapper defaultRecordMapper) {
            this.defaultRecordMapper = defaultRecordMapper
        }

        @Override
        E map(R record) {
            E mapped = (E) defaultRecordMapper.map(record)

            aliasedFields.each { Map.Entry<String, Class> field ->
                if (mapped.hasProperty(field.key) && record.field(field.key)) {
                    mapped[field.key] = record.getValue(field.key, field.value)
                } else {
                    log.error(LogUtil.toLog('MappingError', [
                            msg: 'Either target property does not exist or aliased record column by the same' +
                                    'name does not exist'
                    ]))
                }
            }

            return mapped
        }
    }
}
