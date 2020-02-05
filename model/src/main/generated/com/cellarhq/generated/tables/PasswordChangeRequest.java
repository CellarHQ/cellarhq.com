/*
 * This file is generated by jOOQ.
 */
package com.cellarhq.generated.tables;


import com.cellarhq.generated.Indexes;
import com.cellarhq.generated.Keys;
import com.cellarhq.generated.Public;
import com.cellarhq.generated.tables.records.PasswordChangeRequestRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
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
public class PasswordChangeRequest extends TableImpl<PasswordChangeRequestRecord> {

    private static final long serialVersionUID = -1374822053;

    /**
     * The reference instance of <code>public.password_change_request</code>
     */
    public static final PasswordChangeRequest PASSWORD_CHANGE_REQUEST = new PasswordChangeRequest();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PasswordChangeRequestRecord> getRecordType() {
        return PasswordChangeRequestRecord.class;
    }

    /**
     * The column <code>public.password_change_request.id</code>.
     */
    public final TableField<PasswordChangeRequestRecord, String> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.CHAR(32).nullable(false), this, "");

    /**
     * The column <code>public.password_change_request.account_email_id</code>.
     */
    public final TableField<PasswordChangeRequestRecord, Long> ACCOUNT_EMAIL_ID = createField(DSL.name("account_email_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.password_change_request.created_date</code>.
     */
    public final TableField<PasswordChangeRequestRecord, LocalDateTime> CREATED_DATE = createField(DSL.name("created_date"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * Create a <code>public.password_change_request</code> table reference
     */
    public PasswordChangeRequest() {
        this(DSL.name("password_change_request"), null);
    }

    /**
     * Create an aliased <code>public.password_change_request</code> table reference
     */
    public PasswordChangeRequest(String alias) {
        this(DSL.name(alias), PASSWORD_CHANGE_REQUEST);
    }

    /**
     * Create an aliased <code>public.password_change_request</code> table reference
     */
    public PasswordChangeRequest(Name alias) {
        this(alias, PASSWORD_CHANGE_REQUEST);
    }

    private PasswordChangeRequest(Name alias, Table<PasswordChangeRequestRecord> aliased) {
        this(alias, aliased, null);
    }

    private PasswordChangeRequest(Name alias, Table<PasswordChangeRequestRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> PasswordChangeRequest(Table<O> child, ForeignKey<O, PasswordChangeRequestRecord> key) {
        super(child, key, PASSWORD_CHANGE_REQUEST);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PASSWORD_CHANGE_REQUEST_PKEY);
    }

    @Override
    public UniqueKey<PasswordChangeRequestRecord> getPrimaryKey() {
        return Keys.PASSWORD_CHANGE_REQUEST_PKEY;
    }

    @Override
    public List<UniqueKey<PasswordChangeRequestRecord>> getKeys() {
        return Arrays.<UniqueKey<PasswordChangeRequestRecord>>asList(Keys.PASSWORD_CHANGE_REQUEST_PKEY);
    }

    @Override
    public List<ForeignKey<PasswordChangeRequestRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<PasswordChangeRequestRecord, ?>>asList(Keys.PASSWORD_CHANGE_REQUEST__FK_PASSWORD_CHANGE_REQUEST_ACCOUNT_EMAIL_ID);
    }

    public AccountEmail accountEmail() {
        return new AccountEmail(this, Keys.PASSWORD_CHANGE_REQUEST__FK_PASSWORD_CHANGE_REQUEST_ACCOUNT_EMAIL_ID);
    }

    @Override
    public PasswordChangeRequest as(String alias) {
        return new PasswordChangeRequest(DSL.name(alias), this);
    }

    @Override
    public PasswordChangeRequest as(Name alias) {
        return new PasswordChangeRequest(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public PasswordChangeRequest rename(String name) {
        return new PasswordChangeRequest(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PasswordChangeRequest rename(Name name) {
        return new PasswordChangeRequest(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, Long, LocalDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
