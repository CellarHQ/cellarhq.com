/**
 * This class is generated by jOOQ
 */
package com.cellarhq.generated.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
@javax.persistence.Entity
@javax.persistence.Table(name = "glassware", schema = "public")
public class GlasswareRecord extends org.jooq.impl.UpdatableRecordImpl<com.cellarhq.generated.tables.records.GlasswareRecord> implements org.jooq.Record9<java.lang.Long, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.String, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp> {

	private static final long serialVersionUID = 814888197;

	/**
	 * Setter for <code>public.glassware.id</code>.
	 */
	public void setId(java.lang.Long value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.glassware.id</code>.
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "id", unique = true, nullable = false, precision = 64)
	@javax.validation.constraints.NotNull
	public java.lang.Long getId() {
		return (java.lang.Long) getValue(0);
	}

	/**
	 * Setter for <code>public.glassware.version</code>.
	 */
	public void setVersion(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.glassware.version</code>.
	 */
	@javax.persistence.Column(name = "version", nullable = false, precision = 32)
	@javax.validation.constraints.NotNull
	public java.lang.Integer getVersion() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>public.glassware.name</code>.
	 */
	public void setName(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.glassware.name</code>.
	 */
	@javax.persistence.Column(name = "name", nullable = false, length = 100)
	@javax.validation.constraints.NotNull
	@javax.validation.constraints.Size(max = 100)
	public java.lang.String getName() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>public.glassware.description</code>.
	 */
	public void setDescription(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>public.glassware.description</code>.
	 */
	@javax.persistence.Column(name = "description")
	public java.lang.String getDescription() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>public.glassware.searchable</code>.
	 */
	public void setSearchable(java.lang.Boolean value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>public.glassware.searchable</code>.
	 */
	@javax.persistence.Column(name = "searchable", nullable = false)
	@javax.validation.constraints.NotNull
	public java.lang.Boolean getSearchable() {
		return (java.lang.Boolean) getValue(4);
	}

	/**
	 * Setter for <code>public.glassware.brewery_db_id</code>.
	 */
	public void setBreweryDbId(java.lang.String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>public.glassware.brewery_db_id</code>.
	 */
	@javax.persistence.Column(name = "brewery_db_id", length = 64)
	@javax.validation.constraints.Size(max = 64)
	public java.lang.String getBreweryDbId() {
		return (java.lang.String) getValue(5);
	}

	/**
	 * Setter for <code>public.glassware.brewery_db_last_updated</code>.
	 */
	public void setBreweryDbLastUpdated(java.sql.Timestamp value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>public.glassware.brewery_db_last_updated</code>.
	 */
	@javax.persistence.Column(name = "brewery_db_last_updated")
	public java.sql.Timestamp getBreweryDbLastUpdated() {
		return (java.sql.Timestamp) getValue(6);
	}

	/**
	 * Setter for <code>public.glassware.created_date</code>.
	 */
	public void setCreatedDate(java.sql.Timestamp value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>public.glassware.created_date</code>.
	 */
	@javax.persistence.Column(name = "created_date", nullable = false)
	@javax.validation.constraints.NotNull
	public java.sql.Timestamp getCreatedDate() {
		return (java.sql.Timestamp) getValue(7);
	}

	/**
	 * Setter for <code>public.glassware.modified_date</code>.
	 */
	public void setModifiedDate(java.sql.Timestamp value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>public.glassware.modified_date</code>.
	 */
	@javax.persistence.Column(name = "modified_date", nullable = false)
	@javax.validation.constraints.NotNull
	public java.sql.Timestamp getModifiedDate() {
		return (java.sql.Timestamp) getValue(8);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Long> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record9 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row9<java.lang.Long, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.String, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp> fieldsRow() {
		return (org.jooq.Row9) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row9<java.lang.Long, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.String, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp> valuesRow() {
		return (org.jooq.Row9) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field1() {
		return com.cellarhq.generated.tables.Glassware.GLASSWARE.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return com.cellarhq.generated.tables.Glassware.GLASSWARE.VERSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return com.cellarhq.generated.tables.Glassware.GLASSWARE.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return com.cellarhq.generated.tables.Glassware.GLASSWARE.DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field5() {
		return com.cellarhq.generated.tables.Glassware.GLASSWARE.SEARCHABLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field6() {
		return com.cellarhq.generated.tables.Glassware.GLASSWARE.BREWERY_DB_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field7() {
		return com.cellarhq.generated.tables.Glassware.GLASSWARE.BREWERY_DB_LAST_UPDATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field8() {
		return com.cellarhq.generated.tables.Glassware.GLASSWARE.CREATED_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field9() {
		return com.cellarhq.generated.tables.Glassware.GLASSWARE.MODIFIED_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value2() {
		return getVersion();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getDescription();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value5() {
		return getSearchable();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value6() {
		return getBreweryDbId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value7() {
		return getBreweryDbLastUpdated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value8() {
		return getCreatedDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value9() {
		return getModifiedDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GlasswareRecord value1(java.lang.Long value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GlasswareRecord value2(java.lang.Integer value) {
		setVersion(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GlasswareRecord value3(java.lang.String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GlasswareRecord value4(java.lang.String value) {
		setDescription(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GlasswareRecord value5(java.lang.Boolean value) {
		setSearchable(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GlasswareRecord value6(java.lang.String value) {
		setBreweryDbId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GlasswareRecord value7(java.sql.Timestamp value) {
		setBreweryDbLastUpdated(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GlasswareRecord value8(java.sql.Timestamp value) {
		setCreatedDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GlasswareRecord value9(java.sql.Timestamp value) {
		setModifiedDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GlasswareRecord values(java.lang.Long value1, java.lang.Integer value2, java.lang.String value3, java.lang.String value4, java.lang.Boolean value5, java.lang.String value6, java.sql.Timestamp value7, java.sql.Timestamp value8, java.sql.Timestamp value9) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached GlasswareRecord
	 */
	public GlasswareRecord() {
		super(com.cellarhq.generated.tables.Glassware.GLASSWARE);
	}

	/**
	 * Create a detached, initialised GlasswareRecord
	 */
	public GlasswareRecord(java.lang.Long id, java.lang.Integer version, java.lang.String name, java.lang.String description, java.lang.Boolean searchable, java.lang.String breweryDbId, java.sql.Timestamp breweryDbLastUpdated, java.sql.Timestamp createdDate, java.sql.Timestamp modifiedDate) {
		super(com.cellarhq.generated.tables.Glassware.GLASSWARE);

		setValue(0, id);
		setValue(1, version);
		setValue(2, name);
		setValue(3, description);
		setValue(4, searchable);
		setValue(5, breweryDbId);
		setValue(6, breweryDbLastUpdated);
		setValue(7, createdDate);
		setValue(8, modifiedDate);
	}
}
