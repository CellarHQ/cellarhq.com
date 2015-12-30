package com.cellarhq.jooq.listeners

import com.cellarhq.jooq.annotations.Sanitize
import com.cellarhq.util.LogUtil
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.jooq.ExecuteType
import org.jooq.Field
import org.jooq.Record
import org.jooq.RecordContext
import org.jooq.impl.DefaultRecordListener
import org.owasp.validator.html.AntiSamy
import org.owasp.validator.html.CleanResults
import org.owasp.validator.html.Policy
import org.reflections.Reflections

/**
 * Responsible for sanitizing user input according to the given policy file.
 *
 * Uses AntiSamy for sanitization and validation and will only work on Record saves, not direct queries.
 */
@Slf4j
@Singleton
@CompileStatic
class InputSanitizingListener extends DefaultRecordListener {

  private final static String POLICY_FILE = '/antisamy.xml'

  private final Map<Class<? extends Record>, List<String>> recordSanitizations = makeRecordSanitizationMap()
  private final AntiSamy antiSamy = new AntiSamy(Policy.getInstance(getClass().getResourceAsStream(POLICY_FILE)))

  @Override
  void storeStart(RecordContext ctx) {
    if (shouldProcessEvent(ctx.type())) {
      Record record = ctx.record()
      if (shouldSanitizeRecord(record)) {
        sanitizeRecord(record)
      }
    }

    super.storeStart(ctx)
  }

  void sanitizeRecord(Record record) {
    List<String> fieldsToSanitize = recordSanitizations[record.class]
    fieldsToSanitize.each { String fieldName ->
      Field<?> field = record.field(fieldName)
      if (field) {
        if (record.changed(field)) {
          Object value = record.getValue(field)
          if (value != null) {
            CleanResults results = antiSamy.scan(value.toString())
            record.setValue(field, results.cleanHTML.asType(field.type))
          }
        }
      } else {
        log.warn(LogUtil.toLog('SanitizeRuleFailure', [
          msg       : 'Field to be sanitized was not found',
          field     : fieldName,
          recordType: record.class.simpleName
        ]))
      }
    }
  }

  boolean shouldSanitizeRecord(Record record) {
    return recordSanitizations.keySet().contains(record.class)
  }

  boolean shouldProcessEvent(ExecuteType executeType) {
    return executeType == ExecuteType.WRITE
  }

  Map<Class<? extends Record>, List<String>> makeRecordSanitizationMap() {
    Set<Class<?>> pojos = new Reflections('com.cellarhq.domain').getTypesAnnotatedWith(Sanitize)
    return pojos.findAll { Class<?> pojoType ->
      return pojoType.getAnnotation(Sanitize)
    }.collectEntries { Class<?> pojoType ->
      Sanitize annotation = pojoType.getAnnotation(Sanitize)
      return [
        annotation.recordType(),
        annotation.fields().toList()
      ]
    }
  }

}
