package com.cellarhq.util

import groovy.transform.CompileStatic
import org.jooq.Field
import org.jooq.TableField

@CompileStatic
class JooqUtil {

    /**
     * Generates an array of filtered Fields, optionally merging them into another array of Fields.
     *
     *  <pre class="example">
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
        Field[] filtered = fields.findAll { !except.contains(it) }
        if (mergeInto) {
            List<Field> merged = mergeInto.toList()
            merged.addAll(filtered)
            return (Field[]) merged.toArray()
        }
        return filtered
    }

    static Field[] andFields(Field[] fields, Field... more) {
        List<Field> merged = fields.toList()
        merged.addAll(more)
        return (Field[]) merged.toArray()
    }

    static Field[] andFields(Field[] fields, List<Field> more) {
        List<Field> merged = fields.toList()
        merged.addAll(more)
        return (Field[]) merged.toArray()
    }

    static String uuid() {
        return UUID.randomUUID().toString().replaceAll(/\W/, '')
    }
}
