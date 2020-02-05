/*
 * This file is generated by jOOQ.
 */
package com.cellarhq.generated.tables;


import com.cellarhq.generated.Indexes;
import com.cellarhq.generated.Keys;
import com.cellarhq.generated.Public;
import com.cellarhq.generated.tables.records.GlasswareRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.JSON;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row10;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Glassware extends TableImpl<GlasswareRecord> {

    private static final long serialVersionUID = 488836462;

    /**
     * The reference instance of <code>public.glassware</code>
     */
    public static final Glassware GLASSWARE = new Glassware();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<GlasswareRecord> getRecordType() {
        return GlasswareRecord.class;
    }

    /**
     * The column <code>public.glassware.id</code>.
     */
    public final TableField<GlasswareRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.glassware.version</code>.
     */
    public final TableField<GlasswareRecord, Integer> VERSION = createField(DSL.name("version"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("1", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.glassware.name</code>.
     */
    public final TableField<GlasswareRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>public.glassware.description</code>.
     */
    public final TableField<GlasswareRecord, String> DESCRIPTION = createField(DSL.name("description"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.glassware.searchable</code>.
     */
    public final TableField<GlasswareRecord, Boolean> SEARCHABLE = createField(DSL.name("searchable"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("true", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>public.glassware.brewery_db_id</code>.
     */
    public final TableField<GlasswareRecord, String> BREWERY_DB_ID = createField(DSL.name("brewery_db_id"), org.jooq.impl.SQLDataType.VARCHAR(64), this, "");

    /**
     * The column <code>public.glassware.brewery_db_last_updated</code>.
     */
    public final TableField<GlasswareRecord, LocalDateTime> BREWERY_DB_LAST_UPDATED = createField(DSL.name("brewery_db_last_updated"), org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * The column <code>public.glassware.created_date</code>.
     */
    public final TableField<GlasswareRecord, LocalDateTime> CREATED_DATE = createField(DSL.name("created_date"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>public.glassware.modified_date</code>.
     */
    public final TableField<GlasswareRecord, LocalDateTime> MODIFIED_DATE = createField(DSL.name("modified_date"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>public.glassware.data</code>.
     */
    public final TableField<GlasswareRecord, JSON> DATA = createField(DSL.name("data"), org.jooq.impl.SQLDataType.JSON, this, "");

    /**
     * Create a <code>public.glassware</code> table reference
     */
    public Glassware() {
        this(DSL.name("glassware"), null);
    }

    /**
     * Create an aliased <code>public.glassware</code> table reference
     */
    public Glassware(String alias) {
        this(DSL.name(alias), GLASSWARE);
    }

    /**
     * Create an aliased <code>public.glassware</code> table reference
     */
    public Glassware(Name alias) {
        this(alias, GLASSWARE);
    }

    private Glassware(Name alias, Table<GlasswareRecord> aliased) {
        this(alias, aliased, null);
    }

    private Glassware(Name alias, Table<GlasswareRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Glassware(Table<O> child, ForeignKey<O, GlasswareRecord> key) {
        super(child, key, GLASSWARE);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.GLASSWARE_PKEY, Indexes.IDX_GLASSWARE_BREWERY_DB_ID);
    }

    @Override
    public UniqueKey<GlasswareRecord> getPrimaryKey() {
        return Keys.GLASSWARE_PKEY;
    }

    @Override
    public List<UniqueKey<GlasswareRecord>> getKeys() {
        return Arrays.<UniqueKey<GlasswareRecord>>asList(Keys.GLASSWARE_PKEY);
    }

    @Override
    public Glassware as(String alias) {
        return new Glassware(DSL.name(alias), this);
    }

    @Override
    public Glassware as(Name alias) {
        return new Glassware(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Glassware rename(String name) {
        return new Glassware(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Glassware rename(Name name) {
        return new Glassware(name, null);
    }

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row10<Long, Integer, String, String, Boolean, String, LocalDateTime, LocalDateTime, LocalDateTime, JSON> fieldsRow() {
        return (Row10) super.fieldsRow();
    }
}
