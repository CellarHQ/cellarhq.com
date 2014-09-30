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
public class DrinkRecord extends org.jooq.impl.UpdatableRecordImpl<com.cellarhq.generated.tables.records.DrinkRecord> {

	private static final long serialVersionUID = 1761449757;

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
	@javax.persistence.Column(name = "style_id", precision = 64)
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
	@javax.persistence.Column(name = "glassware_id", precision = 64)
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
	@javax.persistence.Column(name = "abv", precision = 6, scale = 3)
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
	public java.sql.Timestamp getModifiedDate() {
		return (java.sql.Timestamp) getValue(20);
	}

	/**
	 * Setter for <code>public.drink.data</code>.
	 */
	public void setData(java.lang.Object value) {
		setValue(21, value);
	}

	/**
	 * Getter for <code>public.drink.data</code>.
	 */
	@javax.persistence.Column(name = "data")
	public java.lang.Object getData() {
		return (java.lang.Object) getValue(21);
	}

	/**
	 * Setter for <code>public.drink.warning_flag</code>.
	 */
	public void setWarningFlag(java.lang.Boolean value) {
		setValue(22, value);
	}

	/**
	 * Getter for <code>public.drink.warning_flag</code>.
	 */
	@javax.persistence.Column(name = "warning_flag", nullable = false)
	public java.lang.Boolean getWarningFlag() {
		return (java.lang.Boolean) getValue(22);
	}

	/**
	 * Setter for <code>public.drink.tradable_beers</code>.
	 */
	public void setTradableBeers(java.lang.Short value) {
		setValue(23, value);
	}

	/**
	 * Getter for <code>public.drink.tradable_beers</code>.
	 */
	@javax.persistence.Column(name = "tradable_beers", nullable = false, precision = 16)
	public java.lang.Short getTradableBeers() {
		return (java.lang.Short) getValue(23);
	}

	/**
	 * Setter for <code>public.drink.cellared_beers</code>.
	 */
	public void setCellaredBeers(java.lang.Short value) {
		setValue(24, value);
	}

	/**
	 * Getter for <code>public.drink.cellared_beers</code>.
	 */
	@javax.persistence.Column(name = "cellared_beers", nullable = false, precision = 16)
	public java.lang.Short getCellaredBeers() {
		return (java.lang.Short) getValue(24);
	}

	/**
	 * Setter for <code>public.drink.contained_in_cellars</code>.
	 */
	public void setContainedInCellars(java.lang.Short value) {
		setValue(25, value);
	}

	/**
	 * Getter for <code>public.drink.contained_in_cellars</code>.
	 */
	@javax.persistence.Column(name = "contained_in_cellars", nullable = false, precision = 16)
	public java.lang.Short getContainedInCellars() {
		return (java.lang.Short) getValue(25);
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
	public DrinkRecord(java.lang.Long id, java.lang.Integer version, java.lang.Long photoId, java.lang.Long organizationId, java.lang.Long styleId, java.lang.Long glasswareId, java.lang.String drinkType, java.lang.String slug, java.lang.String name, java.lang.String description, java.lang.Integer srm, java.lang.Integer ibu, java.math.BigDecimal abv, java.lang.String availability, java.lang.Boolean searchable, java.lang.String breweryDbId, java.sql.Timestamp breweryDbLastUpdated, java.lang.Boolean locked, java.lang.Boolean needsModeration, java.sql.Timestamp createdDate, java.sql.Timestamp modifiedDate, java.lang.Object data, java.lang.Boolean warningFlag, java.lang.Short tradableBeers, java.lang.Short cellaredBeers, java.lang.Short containedInCellars) {
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
		setValue(21, data);
		setValue(22, warningFlag);
		setValue(23, tradableBeers);
		setValue(24, cellaredBeers);
		setValue(25, containedInCellars);
	}
}
