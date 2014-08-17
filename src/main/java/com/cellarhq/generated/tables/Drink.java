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
public class Drink extends org.jooq.impl.TableImpl<com.cellarhq.generated.tables.records.DrinkRecord> {

	private static final long serialVersionUID = -1306318865;

	/**
	 * The singleton instance of <code>public.drink</code>
	 */
	public static final com.cellarhq.generated.tables.Drink DRINK = new com.cellarhq.generated.tables.Drink();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<com.cellarhq.generated.tables.records.DrinkRecord> getRecordType() {
		return com.cellarhq.generated.tables.records.DrinkRecord.class;
	}

	/**
	 * The column <code>public.drink.id</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.drink.version</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.Integer> VERSION = createField("version", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.drink.photo_id</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.Long> PHOTO_ID = createField("photo_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>public.drink.organization_id</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.Long> ORGANIZATION_ID = createField("organization_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>public.drink.style_id</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.Long> STYLE_ID = createField("style_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>public.drink.glassware_id</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.Long> GLASSWARE_ID = createField("glassware_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>public.drink.drink_type</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.String> DRINK_TYPE = createField("drink_type", org.jooq.impl.SQLDataType.VARCHAR.length(10).nullable(false), this, "");

	/**
	 * The column <code>public.drink.slug</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.String> SLUG = createField("slug", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

	/**
	 * The column <code>public.drink.name</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

	/**
	 * The column <code>public.drink.description</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>public.drink.srm</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.Integer> SRM = createField("srm", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>public.drink.ibu</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.Integer> IBU = createField("ibu", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>public.drink.abv</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.math.BigDecimal> ABV = createField("abv", org.jooq.impl.SQLDataType.NUMERIC.precision(3, 3), this, "");

	/**
	 * The column <code>public.drink.availability</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.String> AVAILABILITY = createField("availability", org.jooq.impl.SQLDataType.VARCHAR.length(20), this, "");

	/**
	 * The column <code>public.drink.searchable</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.Boolean> SEARCHABLE = createField("searchable", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.drink.brewery_db_id</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.String> BREWERY_DB_ID = createField("brewery_db_id", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * The column <code>public.drink.brewery_db_last_updated</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.sql.Timestamp> BREWERY_DB_LAST_UPDATED = createField("brewery_db_last_updated", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

	/**
	 * The column <code>public.drink.locked</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.Boolean> LOCKED = createField("locked", org.jooq.impl.SQLDataType.BOOLEAN, this, "");

	/**
	 * The column <code>public.drink.needs_moderation</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.Boolean> NEEDS_MODERATION = createField("needs_moderation", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.drink.created_date</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.sql.Timestamp> CREATED_DATE = createField("created_date", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.drink.modified_date</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.sql.Timestamp> MODIFIED_DATE = createField("modified_date", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * Create a <code>public.drink</code> table reference
	 */
	public Drink() {
		this("drink", null);
	}

	/**
	 * Create an aliased <code>public.drink</code> table reference
	 */
	public Drink(java.lang.String alias) {
		this(alias, com.cellarhq.generated.tables.Drink.DRINK);
	}

	private Drink(java.lang.String alias, org.jooq.Table<com.cellarhq.generated.tables.records.DrinkRecord> aliased) {
		this(alias, aliased, null);
	}

	private Drink(java.lang.String alias, org.jooq.Table<com.cellarhq.generated.tables.records.DrinkRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, com.cellarhq.generated.Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.Long> getIdentity() {
		return com.cellarhq.generated.Keys.IDENTITY_DRINK;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<com.cellarhq.generated.tables.records.DrinkRecord> getPrimaryKey() {
		return com.cellarhq.generated.Keys.PK_DRINK;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<com.cellarhq.generated.tables.records.DrinkRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<com.cellarhq.generated.tables.records.DrinkRecord>>asList(com.cellarhq.generated.Keys.PK_DRINK, com.cellarhq.generated.Keys.UNQ_DRINK_SLUG);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<com.cellarhq.generated.tables.records.DrinkRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<com.cellarhq.generated.tables.records.DrinkRecord, ?>>asList(com.cellarhq.generated.Keys.DRINK__FK_DRINK_PHOTO_ID, com.cellarhq.generated.Keys.DRINK__FK_DRINK_ORGANIZATION_ID, com.cellarhq.generated.Keys.DRINK__FK_DRINK_STYLE_ID, com.cellarhq.generated.Keys.DRINK__FK_DRINK_GLASSWARE_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.TableField<com.cellarhq.generated.tables.records.DrinkRecord, java.lang.Integer> getRecordVersion() {
		return com.cellarhq.generated.tables.Drink.DRINK.VERSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public com.cellarhq.generated.tables.Drink as(java.lang.String alias) {
		return new com.cellarhq.generated.tables.Drink(alias, this);
	}

	/**
	 * Rename this table
	 */
	public com.cellarhq.generated.tables.Drink rename(java.lang.String name) {
		return new com.cellarhq.generated.tables.Drink(name, null);
	}
}
