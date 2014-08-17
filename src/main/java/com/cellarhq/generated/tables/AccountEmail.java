/**
 * This class is generated by jOOQ
 */
package com.cellarhq.generated.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AccountEmail extends org.jooq.impl.TableImpl<com.cellarhq.generated.tables.records.AccountEmailRecord> {

	private static final long serialVersionUID = 476522183;

	/**
	 * The singleton instance of <code>public.account_email</code>
	 */
	public static final com.cellarhq.generated.tables.AccountEmail ACCOUNT_EMAIL = new com.cellarhq.generated.tables.AccountEmail();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<com.cellarhq.generated.tables.records.AccountEmailRecord> getRecordType() {
		return com.cellarhq.generated.tables.records.AccountEmailRecord.class;
	}

	/**
	 * The column <code>public.account_email.id</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.AccountEmailRecord, java.lang.Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.account_email.version</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.AccountEmailRecord, java.lang.Integer> VERSION = createField("version", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.account_email.cellar_id</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.AccountEmailRecord, java.lang.Long> CELLAR_ID = createField("cellar_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>public.account_email.email</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.AccountEmailRecord, java.lang.String> EMAIL = createField("email", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>public.account_email.password</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.AccountEmailRecord, java.lang.String> PASSWORD = createField("password", org.jooq.impl.SQLDataType.CHAR.length(64).nullable(false), this, "");

	/**
	 * The column <code>public.account_email.created_date</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.AccountEmailRecord, java.sql.Timestamp> CREATED_DATE = createField("created_date", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.account_email.modified_date</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.AccountEmailRecord, java.sql.Timestamp> MODIFIED_DATE = createField("modified_date", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * Create a <code>public.account_email</code> table reference
	 */
	public AccountEmail() {
		this("account_email", null);
	}

	/**
	 * Create an aliased <code>public.account_email</code> table reference
	 */
	public AccountEmail(java.lang.String alias) {
		this(alias, com.cellarhq.generated.tables.AccountEmail.ACCOUNT_EMAIL);
	}

	private AccountEmail(java.lang.String alias, org.jooq.Table<com.cellarhq.generated.tables.records.AccountEmailRecord> aliased) {
		this(alias, aliased, null);
	}

	private AccountEmail(java.lang.String alias, org.jooq.Table<com.cellarhq.generated.tables.records.AccountEmailRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, com.cellarhq.generated.Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<com.cellarhq.generated.tables.records.AccountEmailRecord, java.lang.Long> getIdentity() {
		return com.cellarhq.generated.Keys.IDENTITY_ACCOUNT_EMAIL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<com.cellarhq.generated.tables.records.AccountEmailRecord> getPrimaryKey() {
		return com.cellarhq.generated.Keys.PK_ACCOUNT_EMAIL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<com.cellarhq.generated.tables.records.AccountEmailRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<com.cellarhq.generated.tables.records.AccountEmailRecord>>asList(com.cellarhq.generated.Keys.PK_ACCOUNT_EMAIL, com.cellarhq.generated.Keys.UNQ_ACCOUNT_EMAIL_EMAIL);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<com.cellarhq.generated.tables.records.AccountEmailRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<com.cellarhq.generated.tables.records.AccountEmailRecord, ?>>asList(com.cellarhq.generated.Keys.ACCOUNT_EMAIL__FK_ACCOUNT_EMAIL_CELLAR_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.TableField<com.cellarhq.generated.tables.records.AccountEmailRecord, java.lang.Integer> getRecordVersion() {
		return com.cellarhq.generated.tables.AccountEmail.ACCOUNT_EMAIL.VERSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public com.cellarhq.generated.tables.AccountEmail as(java.lang.String alias) {
		return new com.cellarhq.generated.tables.AccountEmail(alias, this);
	}

	/**
	 * Rename this table
	 */
	public com.cellarhq.generated.tables.AccountEmail rename(java.lang.String name) {
		return new com.cellarhq.generated.tables.AccountEmail(name, null);
	}
}
