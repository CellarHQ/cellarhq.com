/*
 * This file is generated by jOOQ.
 */
package com.cellarhq.generated.tables;


import com.cellarhq.generated.Indexes;
import com.cellarhq.generated.Keys;
import com.cellarhq.generated.Public;
import com.cellarhq.generated.tables.records.AccountOauthRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row7;
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
public class AccountOauth extends TableImpl<AccountOauthRecord> {

    private static final long serialVersionUID = -417060688;

    /**
     * The reference instance of <code>public.account_oauth</code>
     */
    public static final AccountOauth ACCOUNT_OAUTH = new AccountOauth();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AccountOauthRecord> getRecordType() {
        return AccountOauthRecord.class;
    }

    /**
     * The column <code>public.account_oauth.id</code>.
     */
    public final TableField<AccountOauthRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.account_oauth.version</code>.
     */
    public final TableField<AccountOauthRecord, Integer> VERSION = createField(DSL.name("version"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("1", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.account_oauth.cellar_id</code>.
     */
    public final TableField<AccountOauthRecord, Long> CELLAR_ID = createField(DSL.name("cellar_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.account_oauth.client</code>.
     */
    public final TableField<AccountOauthRecord, String> CLIENT = createField(DSL.name("client"), org.jooq.impl.SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>public.account_oauth.username</code>.
     */
    public final TableField<AccountOauthRecord, String> USERNAME = createField(DSL.name("username"), org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>public.account_oauth.created_date</code>.
     */
    public final TableField<AccountOauthRecord, LocalDateTime> CREATED_DATE = createField(DSL.name("created_date"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>public.account_oauth.modified_date</code>.
     */
    public final TableField<AccountOauthRecord, LocalDateTime> MODIFIED_DATE = createField(DSL.name("modified_date"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * Create a <code>public.account_oauth</code> table reference
     */
    public AccountOauth() {
        this(DSL.name("account_oauth"), null);
    }

    /**
     * Create an aliased <code>public.account_oauth</code> table reference
     */
    public AccountOauth(String alias) {
        this(DSL.name(alias), ACCOUNT_OAUTH);
    }

    /**
     * Create an aliased <code>public.account_oauth</code> table reference
     */
    public AccountOauth(Name alias) {
        this(alias, ACCOUNT_OAUTH);
    }

    private AccountOauth(Name alias, Table<AccountOauthRecord> aliased) {
        this(alias, aliased, null);
    }

    private AccountOauth(Name alias, Table<AccountOauthRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> AccountOauth(Table<O> child, ForeignKey<O, AccountOauthRecord> key) {
        super(child, key, ACCOUNT_OAUTH);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.ACCOUNT_OAUTH_CLIENT_USERNAME_KEY, Indexes.ACCOUNT_OAUTH_PKEY);
    }

    @Override
    public UniqueKey<AccountOauthRecord> getPrimaryKey() {
        return Keys.ACCOUNT_OAUTH_PKEY;
    }

    @Override
    public List<UniqueKey<AccountOauthRecord>> getKeys() {
        return Arrays.<UniqueKey<AccountOauthRecord>>asList(Keys.ACCOUNT_OAUTH_PKEY, Keys.ACCOUNT_OAUTH_CLIENT_USERNAME_KEY);
    }

    @Override
    public List<ForeignKey<AccountOauthRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<AccountOauthRecord, ?>>asList(Keys.ACCOUNT_OAUTH__FK_ACCOUNT_OAUTH_CELLAR_ID);
    }

    public Cellar cellar() {
        return new Cellar(this, Keys.ACCOUNT_OAUTH__FK_ACCOUNT_OAUTH_CELLAR_ID);
    }

    @Override
    public AccountOauth as(String alias) {
        return new AccountOauth(DSL.name(alias), this);
    }

    @Override
    public AccountOauth as(Name alias) {
        return new AccountOauth(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AccountOauth rename(String name) {
        return new AccountOauth(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AccountOauth rename(Name name) {
        return new AccountOauth(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Long, Integer, Long, String, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}
