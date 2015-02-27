package com.cellarhq.jooq.annotations

import org.jooq.Record

import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Add to a domain POJO to mark certain fields as requiring user input sanitization on save operations.
 *
 * This will be read by InputSanitizingListener.
 *
 * Example:
 * <code>
 * @Sanitize(type=CellarRecord, fields=['displayName', 'bio'])
 * class Cellar { ... }
 * </code>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Sanitize {
    Class<? extends Record> recordType()
    String[] fields()
}
