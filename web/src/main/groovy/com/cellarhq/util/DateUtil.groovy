package com.cellarhq.util

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.time.temporal.TemporalAccessor

@Slf4j
@CompileStatic
abstract class DateUtil {

  @SuppressWarnings(['EmptyCatchBlock', 'CatchException'])
  static Optional<LocalDate> parse(String input) {
    if (!input) {
      return Optional.empty()
    }

    LocalDate date = null
    List<String> formats = [
      'yyyy',
      'yyyy-MM',
      'yyyy-MM-dd',
      'MM/dd/yyyy',
      'dd/MM/yyyy',
      // outliers
      'yyyy-M-dd',
      'yyyy-MM-d',
      'yyyy-M-d'
    ]
    for (format in formats) {
      DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()

      if (!format.contains('M')) {
        builder.parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
          .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
      } else if (!format.contains('d')) {
        builder.parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
      }

      DateTimeFormatter formatter = builder
        .append(DateTimeFormatter.ofPattern(format))
        .toFormatter()

      try {
        TemporalAccessor parsed = formatter.parse(input)
        if (parsed) {
          date = LocalDate.from(parsed)
        }
      } catch (Exception e) {
        // Swallow exception
      }

      if (date) {
        break
      }
    }

    if (!date) {
      log.error(LogUtil.toLog('DateFormatError', [
        msg  : 'Could not determine date format',
        input: input,
      ]))
    }

    return Optional.ofNullable(date)
  }
}
