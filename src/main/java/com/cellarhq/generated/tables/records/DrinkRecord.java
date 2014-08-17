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
@javax.persistence.Table(name = "drink", schema = "public")
public class DrinkRecord extends org.jooq.impl.UpdatableRecordImpl<com.cellarhq.generated.tables.records.DrinkRecord> implements org.jooq.Record21<java.lang.Long, java.lang.Integer, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.math.BigDecimal, java.lang.String, java.lang.Boolean, java.lang.String, java.sql.Timestamp, java.lang.Boolean, java.lang.Boolean, java.sql.Timestamp, java.sql.Timestamp> {

	private static final long serialVersionUID = 755276674;

	/**
	 * Setter for <code>public.drink.id</code>.
	 */
	public void setId(java.lang.Long value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.drink.id</code>.
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "id", unique = true, nullable = false, precision = 64)
	@javax.validation.constraints.NotNull
	public java.lang.Long getId() {
		return (java.lang.Long) getValue(0);
	}

	/**
	 * Setter for <code>public.drink.version</code>.
	 */
	public void setVersion(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.drink.version</code>.
	 */
	@javax.persistence.Column(name = "version", nullable = false, precision = 32)
	@javax.validation.constraints.NotNull
	public java.lang.Integer getVersion() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>public.drink.photo_id</code>.
	 */
	public void setPhotoId(java.lang.Long value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.drink.photo_id</code>.
	 */
	@javax.persistence.Column(name = "photo_id", precision = 64)
	public java.lang.Long getPhotoId() {
		return (java.lang.Long) getValue(2);
	}

	/**
	 * Setter for <code>public.drink.organization_id</code>.
	 */
	public void setOrganizationId(java.lang.Long value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>public.drink.organization_id</code>.
	 */
	@javax.persistence.Column(name = "organization_id", nullable = false, precision = 64)
	@javax.validation.constraints.NotNull
	public java.lang.Long getOrganizationId() {
		return (java.lang.Long) getValue(3);
	}

	/**
	 * Setter for <code>public.drink.style_id</code>.
	 */
	public void setStyleId(java.lang.Long value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>public.drink.style_id</code>.
	 */
	@javax.persistence.Column(name = "style_id", nullable = false, precision = 64)
	@javax.validation.constraints.NotNull
	public java.lang.Long getStyleId() {
		return (java.lang.Long) getValue(4);
	}

	/**
	 * Setter for <code>public.drink.glassware_id</code>.
	 */
	public void setGlasswareId(java.lang.Long value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>public.drink.glassware_id</code>.
	 */
	@javax.persistence.Column(name = "glassware_id", nullable = false, precision = 64)
	@javax.validation.constraints.NotNull
	public java.lang.Long getGlasswareId() {
		return (java.lang.Long) getValue(5);
	}

	/**
	 * Setter for <code>public.drink.drink_type</code>.
	 */
	public void setDrinkType(java.lang.String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>public.drink.drink_type</code>.
	 */
	@javax.persistence.Column(name = "drink_type", nullable = false, length = 10)
	@javax.validation.constraints.NotNull
	@javax.validation.constraints.Size(max = 10)
	public java.lang.String getDrinkType() {
		return (java.lang.String) getValue(6);
	}

	/**
	 * Setter for <code>public.drink.slug</code>.
	 */
	public void setSlug(java.lang.String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>public.drink.slug</code>.
	 */
	@javax.persistence.Column(name = "slug", unique = true, nullable = false, length = 100)
	@javax.validation.constraints.NotNull
	@javax.validation.constraints.Size(max = 100)
	public java.lang.String getSlug() {
		return (java.lang.String) getValue(7);
	}

	/**
	 * Setter for <code>public.drink.name</code>.
	 */
	public void setName(java.lang.String value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>public.drink.name</code>.
	 */
	@javax.persistence.Column(name = "name", nullable = false, length = 100)
	@javax.validation.constraints.NotNull
	@javax.validation.constraints.Size(max = 100)
	public java.lang.String getName() {
		return (java.lang.String) getValue(8);
	}

	/**
	 * Setter for <code>public.drink.description</code>.
	 */
	public void setDescription(java.lang.String value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>public.drink.description</code>.
	 */
	@javax.persistence.Column(name = "description")
	public java.lang.String getDescription() {
		return (java.lang.String) getValue(9);
	}

	/**
	 * Setter for <code>public.drink.srm</code>.
	 */
	public void setSrm(java.lang.Integer value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>public.drink.srm</code>.
	 */
	@javax.persistence.Column(name = "srm", precision = 32)
	public java.lang.Integer getSrm() {
		return (java.lang.Integer) getValue(10);
	}

	/**
	 * Setter for <code>public.drink.ibu</code>.
	 */
	public void setIbu(java.lang.Integer value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>public.drink.ibu</code>.
	 */
	@javax.persistence.Column(name = "ibu", precision = 32)
	public java.lang.Integer getIbu() {
		return (java.lang.Integer) getValue(11);
	}

	/**
	 * Setter for <code>public.drink.abv</code>.
	 */
	public void setAbv(java.math.BigDecimal value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>public.drink.abv</code>.
	 */
	@javax.persistence.Column(name = "abv", precision = 3, scale = 3)
	public java.math.BigDecimal getAbv() {
		return (java.math.BigDecimal) getValue(12);
	}

	/**
	 * Setter for <code>public.drink.availability</code>.
	 */
	public void setAvailability(java.lang.String value) {
		setValue(13, value);
	}

	/**
	 * Getter for <code>public.drink.availability</code>.
	 */
	@javax.persistence.Column(name = "availability", length = 20)
	@javax.validation.constraints.Size(max = 20)
	public java.lang.String getAvailability() {
		return (java.lang.String) getValue(13);
	}

	/**
	 * Setter for <code>public.drink.searchable</code>.
	 */
	public void setSearchable(java.lang.Boolean value) {
		setValue(14, value);
	}

	/**
	 * Getter for <code>public.drink.searchable</code>.
	 */
	@javax.persistence.Column(name = "searchable", nullable = false)
	@javax.validation.constraints.NotNull
	public java.lang.Boolean getSearchable() {
		return (java.lang.Boolean) getValue(14);
	}

	/**
	 * Setter for <code>public.drink.brewery_db_id</code>.
	 */
	public void setBreweryDbId(java.lang.String value) {
		setValue(15, value);
	}

	/**
	 * Getter for <code>public.drink.brewery_db_id</code>.
	 */
	@javax.persistence.Column(name = "brewery_db_id", length = 64)
	@javax.validation.constraints.Size(max = 64)
	public java.lang.String getBreweryDbId() {
		return (java.lang.String) getValue(15);
	}

	/**
	 * Setter for <code>public.drink.brewery_db_last_updated</code>.
	 */
	public void setBreweryDbLastUpdated(java.sql.Timestamp value) {
		setValue(16, value);
	}

	/**
	 * Getter for <code>public.drink.brewery_db_last_updated</code>.
	 */
	@javax.persistence.Column(name = "brewery_db_last_updated")
	public java.sql.Timestamp getBreweryDbLastUpdated() {
		return (java.sql.Timestamp) getValue(16);
	}

	/**
	 * Setter for <code>public.drink.locked</code>.
	 */
	public void setLocked(java.lang.Boolean value) {
		setValue(17, value);
	}

	/**
	 * Getter for <code>public.drink.locked</code>.
	 */
	@javax.persistence.Column(name = "locked")
	public java.lang.Boolean getLocked() {
		return (java.lang.Boolean) getValue(17);
	}

	/**
	 * Setter for <code>public.drink.needs_moderation</code>.
	 */
	public void setNeedsModeration(java.lang.Boolean value) {
		setValue(18, value);
	}

	/**
	 * Getter for <code>public.drink.needs_moderation</code>.
	 */
	@javax.persistence.Column(name = "needs_moderation", nullable = false)
	@javax.validation.constraints.NotNull
	public java.lang.Boolean getNeedsModeration() {
		return (java.lang.Boolean) getValue(18);
	}

	/**
	 * Setter for <code>public.drink.created_date</code>.
	 */
	public void setCreatedDate(java.sql.Timestamp value) {
		setValue(19, value);
	}

	/**
	 * Getter for <code>public.drink.created_date</code>.
	 */
	@javax.persistence.Column(name = "created_date", nullable = false)
	@javax.validation.constraints.NotNull
	public java.sql.Timestamp getCreatedDate() {
		return (java.sql.Timestamp) getValue(19);
	}

	/**
	 * Setter for <code>public.drink.modified_date</code>.
	 */
	public void setModifiedDate(java.sql.Timestamp value) {
		setValue(20, value);
	}

	/**
	 * Getter for <code>public.drink.modified_date</code>.
	 */
	@javax.persistence.Column(name = "modified_date", nullable = false)
	@javax.validation.constraints.NotNull
	public java.sql.Timestamp getModifiedDate() {
		return (java.sql.Timestamp) getValue(20);
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
	// Record21 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row21<java.lang.Long, java.lang.Integer, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.math.BigDecimal, java.lang.String, java.lang.Boolean, java.lang.String, java.sql.Timestamp, java.lang.Boolean, java.lang.Boolean, java.sql.Timestamp, java.sql.Timestamp> fieldsRow() {
		return (org.jooq.Row21) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row21<java.lang.Long, java.lang.Integer, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.math.BigDecimal, java.lang.String, java.lang.Boolean, java.lang.String, java.sql.Timestamp, java.lang.Boolean, java.lang.Boolean, java.sql.Timestamp, java.sql.Timestamp> valuesRow() {
		return (org.jooq.Row21) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field1() {
		return com.cellarhq.generated.tables.Drink.DRINK.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return com.cellarhq.generated.tables.Drink.DRINK.VERSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field3() {
		return com.cellarhq.generated.tables.Drink.DRINK.PHOTO_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field4() {
		return com.cellarhq.generated.tables.Drink.DRINK.ORGANIZATION_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field5() {
		return com.cellarhq.generated.tables.Drink.DRINK.STYLE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field6() {
		return com.cellarhq.generated.tables.Drink.DRINK.GLASSWARE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field7() {
		return com.cellarhq.generated.tables.Drink.DRINK.DRINK_TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field8() {
		return com.cellarhq.generated.tables.Drink.DRINK.SLUG;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field9() {
		return com.cellarhq.generated.tables.Drink.DRINK.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field10() {
		return com.cellarhq.generated.tables.Drink.DRINK.DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field11() {
		return com.cellarhq.generated.tables.Drink.DRINK.SRM;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field12() {
		return com.cellarhq.generated.tables.Drink.DRINK.IBU;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigDecimal> field13() {
		return com.cellarhq.generated.tables.Drink.DRINK.ABV;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field14() {
		return com.cellarhq.generated.tables.Drink.DRINK.AVAILABILITY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field15() {
		return com.cellarhq.generated.tables.Drink.DRINK.SEARCHABLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field16() {
		return com.cellarhq.generated.tables.Drink.DRINK.BREWERY_DB_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field17() {
		return com.cellarhq.generated.tables.Drink.DRINK.BREWERY_DB_LAST_UPDATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field18() {
		return com.cellarhq.generated.tables.Drink.DRINK.LOCKED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field19() {
		return com.cellarhq.generated.tables.Drink.DRINK.NEEDS_MODERATION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field20() {
		return com.cellarhq.generated.tables.Drink.DRINK.CREATED_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field21() {
		return com.cellarhq.generated.tables.Drink.DRINK.MODIFIED_DATE;
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
	public java.lang.Long value3() {
		return getPhotoId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value4() {
		return getOrganizationId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value5() {
		return getStyleId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value6() {
		return getGlasswareId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value7() {
		return getDrinkType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value8() {
		return getSlug();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value9() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value10() {
		return getDescription();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value11() {
		return getSrm();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value12() {
		return getIbu();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigDecimal value13() {
		return getAbv();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value14() {
		return getAvailability();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value15() {
		return getSearchable();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value16() {
		return getBreweryDbId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value17() {
		return getBreweryDbLastUpdated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value18() {
		return getLocked();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value19() {
		return getNeedsModeration();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value20() {
		return getCreatedDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value21() {
		return getModifiedDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value1(java.lang.Long value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value2(java.lang.Integer value) {
		setVersion(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value3(java.lang.Long value) {
		setPhotoId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value4(java.lang.Long value) {
		setOrganizationId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value5(java.lang.Long value) {
		setStyleId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value6(java.lang.Long value) {
		setGlasswareId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value7(java.lang.String value) {
		setDrinkType(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value8(java.lang.String value) {
		setSlug(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value9(java.lang.String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value10(java.lang.String value) {
		setDescription(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value11(java.lang.Integer value) {
		setSrm(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value12(java.lang.Integer value) {
		setIbu(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value13(java.math.BigDecimal value) {
		setAbv(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value14(java.lang.String value) {
		setAvailability(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value15(java.lang.Boolean value) {
		setSearchable(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value16(java.lang.String value) {
		setBreweryDbId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value17(java.sql.Timestamp value) {
		setBreweryDbLastUpdated(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value18(java.lang.Boolean value) {
		setLocked(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value19(java.lang.Boolean value) {
		setNeedsModeration(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value20(java.sql.Timestamp value) {
		setCreatedDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord value21(java.sql.Timestamp value) {
		setModifiedDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrinkRecord values(java.lang.Long value1, java.lang.Integer value2, java.lang.Long value3, java.lang.Long value4, java.lang.Long value5, java.lang.Long value6, java.lang.String value7, java.lang.String value8, java.lang.String value9, java.lang.String value10, java.lang.Integer value11, java.lang.Integer value12, java.math.BigDecimal value13, java.lang.String value14, java.lang.Boolean value15, java.lang.String value16, java.sql.Timestamp value17, java.lang.Boolean value18, java.lang.Boolean value19, java.sql.Timestamp value20, java.sql.Timestamp value21) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached DrinkRecord
	 */
	public DrinkRecord() {
		super(com.cellarhq.generated.tables.Drink.DRINK);
	}

	/**
	 * Create a detached, initialised DrinkRecord
	 */
	public DrinkRecord(java.lang.Long id, java.lang.Integer version, java.lang.Long photoId, java.lang.Long organizationId, java.lang.Long styleId, java.lang.Long glasswareId, java.lang.String drinkType, java.lang.String slug, java.lang.String name, java.lang.String description, java.lang.Integer srm, java.lang.Integer ibu, java.math.BigDecimal abv, java.lang.String availability, java.lang.Boolean searchable, java.lang.String breweryDbId, java.sql.Timestamp breweryDbLastUpdated, java.lang.Boolean locked, java.lang.Boolean needsModeration, java.sql.Timestamp createdDate, java.sql.Timestamp modifiedDate) {
		super(com.cellarhq.generated.tables.Drink.DRINK);

		setValue(0, id);
		setValue(1, version);
		setValue(2, photoId);
		setValue(3, organizationId);
		setValue(4, styleId);
		setValue(5, glasswareId);
		setValue(6, drinkType);
		setValue(7, slug);
		setValue(8, name);
		setValue(9, description);
		setValue(10, srm);
		setValue(11, ibu);
		setValue(12, abv);
		setValue(13, availability);
		setValue(14, searchable);
		setValue(15, breweryDbId);
		setValue(16, breweryDbLastUpdated);
		setValue(17, locked);
		setValue(18, needsModeration);
		setValue(19, createdDate);
		setValue(20, modifiedDate);
	}
}
