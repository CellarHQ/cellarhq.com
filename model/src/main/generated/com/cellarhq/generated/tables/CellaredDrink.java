/*
 * This file is generated by jOOQ.
 */
package com.cellarhq.generated.tables;


import com.cellarhq.generated.Indexes;
import com.cellarhq.generated.Keys;
import com.cellarhq.generated.Public;
import com.cellarhq.generated.tables.records.CellaredDrinkRecord;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row15;
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
public class CellaredDrink extends TableImpl<CellaredDrinkRecord> {

    private static final long serialVersionUID = 337135916;

    /**
     * The reference instance of <code>public.cellared_drink</code>
     */
    public static final CellaredDrink CELLARED_DRINK = new CellaredDrink();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CellaredDrinkRecord> getRecordType() {
        return CellaredDrinkRecord.class;
    }

    /**
     * The column <code>public.cellared_drink.id</code>.
     */
    public final TableField<CellaredDrinkRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.cellared_drink.version</code>.
     */
    public final TableField<CellaredDrinkRecord, Integer> VERSION = createField(DSL.name("version"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("1", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.cellared_drink.cellar_id</code>.
     */
    public final TableField<CellaredDrinkRecord, Long> CELLAR_ID = createField(DSL.name("cellar_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.cellared_drink.drink_id</code>.
     */
    public final TableField<CellaredDrinkRecord, Long> DRINK_ID = createField(DSL.name("drink_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.cellared_drink.bottle_date</code>.
     */
    public final TableField<CellaredDrinkRecord, LocalDate> BOTTLE_DATE = createField(DSL.name("bottle_date"), org.jooq.impl.SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.cellared_drink.size</code>.
     */
    public final TableField<CellaredDrinkRecord, String> SIZE = createField(DSL.name("size"), org.jooq.impl.SQLDataType.VARCHAR(30), this, "");

    /**
     * The column <code>public.cellared_drink.quantity</code>.
     */
    public final TableField<CellaredDrinkRecord, Integer> QUANTITY = createField(DSL.name("quantity"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.cellared_drink.notes</code>.
     */
    public final TableField<CellaredDrinkRecord, String> NOTES = createField(DSL.name("notes"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.cellared_drink.drink_by_date</code>.
     */
    public final TableField<CellaredDrinkRecord, LocalDate> DRINK_BY_DATE = createField(DSL.name("drink_by_date"), org.jooq.impl.SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.cellared_drink.created_date</code>.
     */
    public final TableField<CellaredDrinkRecord, LocalDateTime> CREATED_DATE = createField(DSL.name("created_date"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>public.cellared_drink.modified_date</code>.
     */
    public final TableField<CellaredDrinkRecord, LocalDateTime> MODIFIED_DATE = createField(DSL.name("modified_date"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>public.cellared_drink.bin_identifier</code>.
     */
    public final TableField<CellaredDrinkRecord, String> BIN_IDENTIFIER = createField(DSL.name("bin_identifier"), org.jooq.impl.SQLDataType.VARCHAR(20), this, "");

    /**
     * The column <code>public.cellared_drink.tradeable</code>.
     */
    public final TableField<CellaredDrinkRecord, Boolean> TRADEABLE = createField(DSL.name("tradeable"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("false", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>public.cellared_drink.num_tradeable</code>.
     */
    public final TableField<CellaredDrinkRecord, Short> NUM_TRADEABLE = createField(DSL.name("num_tradeable"), org.jooq.impl.SQLDataType.SMALLINT, this, "");

    /**
     * The column <code>public.cellared_drink.date_acquired</code>.
     */
    public final TableField<CellaredDrinkRecord, LocalDate> DATE_ACQUIRED = createField(DSL.name("date_acquired"), org.jooq.impl.SQLDataType.LOCALDATE, this, "");

    /**
     * Create a <code>public.cellared_drink</code> table reference
     */
    public CellaredDrink() {
        this(DSL.name("cellared_drink"), null);
    }

    /**
     * Create an aliased <code>public.cellared_drink</code> table reference
     */
    public CellaredDrink(String alias) {
        this(DSL.name(alias), CELLARED_DRINK);
    }

    /**
     * Create an aliased <code>public.cellared_drink</code> table reference
     */
    public CellaredDrink(Name alias) {
        this(alias, CELLARED_DRINK);
    }

    private CellaredDrink(Name alias, Table<CellaredDrinkRecord> aliased) {
        this(alias, aliased, null);
    }

    private CellaredDrink(Name alias, Table<CellaredDrinkRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> CellaredDrink(Table<O> child, ForeignKey<O, CellaredDrinkRecord> key) {
        super(child, key, CELLARED_DRINK);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.CELLARED_DRINK_PKEY, Indexes.IDX_CELLARED_DRINK_DRINK_BY_DATE);
    }

    @Override
    public UniqueKey<CellaredDrinkRecord> getPrimaryKey() {
        return Keys.CELLARED_DRINK_PKEY;
    }

    @Override
    public List<UniqueKey<CellaredDrinkRecord>> getKeys() {
        return Arrays.<UniqueKey<CellaredDrinkRecord>>asList(Keys.CELLARED_DRINK_PKEY);
    }

    @Override
    public List<ForeignKey<CellaredDrinkRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<CellaredDrinkRecord, ?>>asList(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_CELLAR_ID, Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID);
    }

    public Cellar cellar() {
        return new Cellar(this, Keys.CELLARED_DRINK__FK_CELLARED_DRINK_CELLAR_ID);
    }

    public Drink drink() {
        return new Drink(this, Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID);
    }

    @Override
    public CellaredDrink as(String alias) {
        return new CellaredDrink(DSL.name(alias), this);
    }

    @Override
    public CellaredDrink as(Name alias) {
        return new CellaredDrink(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CellaredDrink rename(String name) {
        return new CellaredDrink(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CellaredDrink rename(Name name) {
        return new CellaredDrink(name, null);
    }

    // -------------------------------------------------------------------------
    // Row15 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row15<Long, Integer, Long, Long, LocalDate, String, Integer, String, LocalDate, LocalDateTime, LocalDateTime, String, Boolean, Short, LocalDate> fieldsRow() {
        return (Row15) super.fieldsRow();
    }
}
