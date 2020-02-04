/*
 * This file is generated by jOOQ.
 */
package com.cellarhq.generated.tables;


import com.cellarhq.generated.Indexes;
import com.cellarhq.generated.Keys;
import com.cellarhq.generated.Public;
import com.cellarhq.generated.tables.records.DrinkRecord;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.JSON;
import org.jooq.Name;
import org.jooq.Record;
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
public class Drink extends TableImpl<DrinkRecord> {

    private static final long serialVersionUID = -2097542913;

    /**
     * The reference instance of <code>public.drink</code>
     */
    public static final Drink DRINK = new Drink();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DrinkRecord> getRecordType() {
        return DrinkRecord.class;
    }

    /**
     * The column <code>public.drink.id</code>.
     */
    public final TableField<DrinkRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('drink_id_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.drink.version</code>.
     */
    public final TableField<DrinkRecord, Integer> VERSION = createField(DSL.name("version"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("1", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.drink.photo_id</code>.
     */
    public final TableField<DrinkRecord, Long> PHOTO_ID = createField(DSL.name("photo_id"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.drink.organization_id</code>.
     */
    public final TableField<DrinkRecord, Long> ORGANIZATION_ID = createField(DSL.name("organization_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.drink.style_id</code>.
     */
    public final TableField<DrinkRecord, Long> STYLE_ID = createField(DSL.name("style_id"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.drink.glassware_id</code>.
     */
    public final TableField<DrinkRecord, Long> GLASSWARE_ID = createField(DSL.name("glassware_id"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.drink.drink_type</code>.
     */
    public final TableField<DrinkRecord, String> DRINK_TYPE = createField(DSL.name("drink_type"), org.jooq.impl.SQLDataType.VARCHAR(10).nullable(false), this, "");

    /**
     * The column <code>public.drink.slug</code>.
     */
    public final TableField<DrinkRecord, String> SLUG = createField(DSL.name("slug"), org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>public.drink.name</code>.
     */
    public final TableField<DrinkRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.drink.description</code>.
     */
    public final TableField<DrinkRecord, String> DESCRIPTION = createField(DSL.name("description"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.drink.srm</code>.
     */
    public final TableField<DrinkRecord, Integer> SRM = createField(DSL.name("srm"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.drink.ibu</code>.
     */
    public final TableField<DrinkRecord, Integer> IBU = createField(DSL.name("ibu"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.drink.abv</code>.
     */
    public final TableField<DrinkRecord, BigDecimal> ABV = createField(DSL.name("abv"), org.jooq.impl.SQLDataType.NUMERIC(6, 3), this, "");

    /**
     * The column <code>public.drink.availability</code>.
     */
    public final TableField<DrinkRecord, String> AVAILABILITY = createField(DSL.name("availability"), org.jooq.impl.SQLDataType.VARCHAR(20), this, "");

    /**
     * The column <code>public.drink.brewery_db_id</code>.
     */
    public final TableField<DrinkRecord, String> BREWERY_DB_ID = createField(DSL.name("brewery_db_id"), org.jooq.impl.SQLDataType.VARCHAR(64), this, "");

    /**
     * The column <code>public.drink.brewery_db_last_updated</code>.
     */
    public final TableField<DrinkRecord, Timestamp> BREWERY_DB_LAST_UPDATED = createField(DSL.name("brewery_db_last_updated"), org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>public.drink.locked</code>.
     */
    public final TableField<DrinkRecord, Boolean> LOCKED = createField(DSL.name("locked"), org.jooq.impl.SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>public.drink.needs_moderation</code>.
     */
    public final TableField<DrinkRecord, Boolean> NEEDS_MODERATION = createField(DSL.name("needs_moderation"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("true", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>public.drink.created_date</code>.
     */
    public final TableField<DrinkRecord, Timestamp> CREATED_DATE = createField(DSL.name("created_date"), org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>public.drink.modified_date</code>.
     */
    public final TableField<DrinkRecord, Timestamp> MODIFIED_DATE = createField(DSL.name("modified_date"), org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>public.drink.data</code>.
     */
    public final TableField<DrinkRecord, JSON> DATA = createField(DSL.name("data"), org.jooq.impl.SQLDataType.JSON, this, "");

    /**
     * The column <code>public.drink.warning_flag</code>.
     */
    public final TableField<DrinkRecord, Boolean> WARNING_FLAG = createField(DSL.name("warning_flag"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("false", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>public.drink.tradable_beers</code>.
     */
    public final TableField<DrinkRecord, Short> TRADABLE_BEERS = createField(DSL.name("tradable_beers"), org.jooq.impl.SQLDataType.SMALLINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0::smallint", org.jooq.impl.SQLDataType.SMALLINT)), this, "");

    /**
     * The column <code>public.drink.cellared_beers</code>.
     */
    public final TableField<DrinkRecord, Short> CELLARED_BEERS = createField(DSL.name("cellared_beers"), org.jooq.impl.SQLDataType.SMALLINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0::smallint", org.jooq.impl.SQLDataType.SMALLINT)), this, "");

    /**
     * The column <code>public.drink.contained_in_cellars</code>.
     */
    public final TableField<DrinkRecord, Short> CONTAINED_IN_CELLARS = createField(DSL.name("contained_in_cellars"), org.jooq.impl.SQLDataType.SMALLINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0::smallint", org.jooq.impl.SQLDataType.SMALLINT)), this, "");

    /**
     * Create a <code>public.drink</code> table reference
     */
    public Drink() {
        this(DSL.name("drink"), null);
    }

    /**
     * Create an aliased <code>public.drink</code> table reference
     */
    public Drink(String alias) {
        this(DSL.name(alias), DRINK);
    }

    /**
     * Create an aliased <code>public.drink</code> table reference
     */
    public Drink(Name alias) {
        this(alias, DRINK);
    }

    private Drink(Name alias, Table<DrinkRecord> aliased) {
        this(alias, aliased, null);
    }

    private Drink(Name alias, Table<DrinkRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Drink(Table<O> child, ForeignKey<O, DrinkRecord> key) {
        super(child, key, DRINK);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.IDX_DRINK_BREWERY_DB_ID, Indexes.IDX_DRINK_NEEDS_MODERATION, Indexes.PK_DRINK);
    }

    @Override
    public Identity<DrinkRecord, Long> getIdentity() {
        return Keys.IDENTITY_DRINK;
    }

    @Override
    public UniqueKey<DrinkRecord> getPrimaryKey() {
        return Keys.PK_DRINK;
    }

    @Override
    public List<UniqueKey<DrinkRecord>> getKeys() {
        return Arrays.<UniqueKey<DrinkRecord>>asList(Keys.PK_DRINK);
    }

    @Override
    public List<ForeignKey<DrinkRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<DrinkRecord, ?>>asList(Keys.DRINK__FK_DRINK_PHOTO_ID, Keys.DRINK__FK_DRINK_ORGANIZATION_ID, Keys.DRINK__FK_DRINK_STYLE_ID, Keys.DRINK__FK_DRINK_GLASSWARE_ID);
    }

    public Photo photo() {
        return new Photo(this, Keys.DRINK__FK_DRINK_PHOTO_ID);
    }

    public Organization organization() {
        return new Organization(this, Keys.DRINK__FK_DRINK_ORGANIZATION_ID);
    }

    public Style style() {
        return new Style(this, Keys.DRINK__FK_DRINK_STYLE_ID);
    }

    public Glassware glassware() {
        return new Glassware(this, Keys.DRINK__FK_DRINK_GLASSWARE_ID);
    }

    @Override
    public Drink as(String alias) {
        return new Drink(DSL.name(alias), this);
    }

    @Override
    public Drink as(Name alias) {
        return new Drink(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Drink rename(String name) {
        return new Drink(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Drink rename(Name name) {
        return new Drink(name, null);
    }
}