/*
 * This file is generated by jOOQ.
 */
package com.cellarhq.generated.tables;


import com.cellarhq.generated.Indexes;
import com.cellarhq.generated.Keys;
import com.cellarhq.generated.Public;
import com.cellarhq.generated.tables.records.AccountLinkRequestRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
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
public class AccountLinkRequest extends TableImpl<AccountLinkRequestRecord> {

    private static final long serialVersionUID = 4862298;

    /**
     * The reference instance of <code>public.account_link_request</code>
     */
    public static final AccountLinkRequest ACCOUNT_LINK_REQUEST = new AccountLinkRequest();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AccountLinkRequestRecord> getRecordType() {
        return AccountLinkRequestRecord.class;
    }

    /**
     * The column <code>public.account_link_request.id</code>.
     */
    public final TableField<AccountLinkRequestRecord, String> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.CHAR(32).nullable(false), this, "");

    /**
     * The column <code>public.account_link_request.cellar_id</code>.
     */
    public final TableField<AccountLinkRequestRecord, Long> CELLAR_ID = createField(DSL.name("cellar_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.account_link_request.account_class</code>.
     */
    public final TableField<AccountLinkRequestRecord, String> ACCOUNT_CLASS = createField(DSL.name("account_class"), org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>public.account_link_request.account_identifier</code>.
     */
    public final TableField<AccountLinkRequestRecord, String> ACCOUNT_IDENTIFIER = createField(DSL.name("account_identifier"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.account_link_request.created_date</code>.
     */
    public final TableField<AccountLinkRequestRecord, LocalDateTime> CREATED_DATE = createField(DSL.name("created_date"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * Create a <code>public.account_link_request</code> table reference
     */
    public AccountLinkRequest() {
        this(DSL.name("account_link_request"), null);
    }

    /**
     * Create an aliased <code>public.account_link_request</code> table reference
     */
    public AccountLinkRequest(String alias) {
        this(DSL.name(alias), ACCOUNT_LINK_REQUEST);
    }

    /**
     * Create an aliased <code>public.account_link_request</code> table reference
     */
    public AccountLinkRequest(Name alias) {
        this(alias, ACCOUNT_LINK_REQUEST);
    }

    private AccountLinkRequest(Name alias, Table<AccountLinkRequestRecord> aliased) {
        this(alias, aliased, null);
    }

    private AccountLinkRequest(Name alias, Table<AccountLinkRequestRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> AccountLinkRequest(Table<O> child, ForeignKey<O, AccountLinkRequestRecord> key) {
        super(child, key, ACCOUNT_LINK_REQUEST);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.ACCOUNT_LINK_REQUEST_PKEY);
    }

    @Override
    public UniqueKey<AccountLinkRequestRecord> getPrimaryKey() {
        return Keys.ACCOUNT_LINK_REQUEST_PKEY;
    }

    @Override
    public List<UniqueKey<AccountLinkRequestRecord>> getKeys() {
        return Arrays.<UniqueKey<AccountLinkRequestRecord>>asList(Keys.ACCOUNT_LINK_REQUEST_PKEY);
    }

    @Override
    public List<ForeignKey<AccountLinkRequestRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<AccountLinkRequestRecord, ?>>asList(Keys.ACCOUNT_LINK_REQUEST__FK_ACCOUNT_LINK_REQUEST_CELLAR_ID);
    }

    public Cellar cellar() {
        return new Cellar(this, Keys.ACCOUNT_LINK_REQUEST__FK_ACCOUNT_LINK_REQUEST_CELLAR_ID);
    }

    @Override
    public AccountLinkRequest as(String alias) {
        return new AccountLinkRequest(DSL.name(alias), this);
    }

    @Override
    public AccountLinkRequest as(Name alias) {
        return new AccountLinkRequest(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AccountLinkRequest rename(String name) {
        return new AccountLinkRequest(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AccountLinkRequest rename(Name name) {
        return new AccountLinkRequest(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<String, Long, String, String, LocalDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
