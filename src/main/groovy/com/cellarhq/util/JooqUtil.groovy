package com.cellarhq.util

import groovy.transform.CompileStatic
import org.jooq.Field
import org.jooq.TableField

@CompileStatic
class JooqUtil {

    /**
     * Generates an array of filtered Fields, optionally merging them into another array of Fields.
     *
     * Useful for excluding fields during join operations that would otherwise collide with each other.
     *
     * <pre class="example">
     *     create.select(fieldsExcept(CELLAR.fields(), [CELLAR.ID], ACCOUNT_EMAIL.fields())
     *         .from(ACCOUNT_EMAIL)
     *         .join(CELLAR).onKey()
     *         .fetch()
     * </pre>
     *
     * @param fields
     * @param except
     * @param mergeInto
     * @return
     */
    static Field[] fieldsExcept(Field[] fields, List<TableField> except, Field[] mergeInto = null) {
        Field[] filtered = fields.findAll { except.contains(it) }
        if (mergeInto) {
            mergeInto.toList().addAll(filtered)
            return mergeInto
        }
        return filtered
    }
}
