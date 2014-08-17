/**
 * This class is generated by jOOQ
 */
package com.cellarhq.generated.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Organization implements java.io.Serializable {

	private static final long serialVersionUID = -787088213;

	private java.lang.Long      id;
	private java.lang.Integer   version;
	private java.lang.Long      photoId;
	private java.lang.String    type;
	private java.lang.String    slug;
	private java.lang.String    name;
	private java.lang.String    description;
	private java.time.LocalDate established;
	private java.lang.String    phone;
	private java.lang.String    website;
	private java.lang.String    address;
	private java.lang.String    address2;
	private java.lang.String    locality;
	private java.lang.String    postalCode;
	private java.lang.String    country;
	private java.lang.Boolean   searchable;
	private java.lang.String    breweryDbId;
	private java.sql.Timestamp  breweryDbLastUpdated;
	private java.lang.Boolean   locked;
	private java.lang.Boolean   needsModeration;
	private java.sql.Timestamp  createdDate;
	private java.sql.Timestamp  modifiedDate;

	public Organization() {}

	public Organization(
		java.lang.Long      id,
		java.lang.Integer   version,
		java.lang.Long      photoId,
		java.lang.String    type,
		java.lang.String    slug,
		java.lang.String    name,
		java.lang.String    description,
		java.time.LocalDate established,
		java.lang.String    phone,
		java.lang.String    website,
		java.lang.String    address,
		java.lang.String    address2,
		java.lang.String    locality,
		java.lang.String    postalCode,
		java.lang.String    country,
		java.lang.Boolean   searchable,
		java.lang.String    breweryDbId,
		java.sql.Timestamp  breweryDbLastUpdated,
		java.lang.Boolean   locked,
		java.lang.Boolean   needsModeration,
		java.sql.Timestamp  createdDate,
		java.sql.Timestamp  modifiedDate
	) {
		this.id = id;
		this.version = version;
		this.photoId = photoId;
		this.type = type;
		this.slug = slug;
		this.name = name;
		this.description = description;
		this.established = established;
		this.phone = phone;
		this.website = website;
		this.address = address;
		this.address2 = address2;
		this.locality = locality;
		this.postalCode = postalCode;
		this.country = country;
		this.searchable = searchable;
		this.breweryDbId = breweryDbId;
		this.breweryDbLastUpdated = breweryDbLastUpdated;
		this.locked = locked;
		this.needsModeration = needsModeration;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	@javax.validation.constraints.NotNull
	public java.lang.Long getId() {
		return this.id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	@javax.validation.constraints.NotNull
	public java.lang.Integer getVersion() {
		return this.version;
	}

	public void setVersion(java.lang.Integer version) {
		this.version = version;
	}

	public java.lang.Long getPhotoId() {
		return this.photoId;
	}

	public void setPhotoId(java.lang.Long photoId) {
		this.photoId = photoId;
	}

	@javax.validation.constraints.NotNull
	@javax.validation.constraints.Size(max = 16)
	public java.lang.String getType() {
		return this.type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	@javax.validation.constraints.NotNull
	@javax.validation.constraints.Size(max = 100)
	public java.lang.String getSlug() {
		return this.slug;
	}

	public void setSlug(java.lang.String slug) {
		this.slug = slug;
	}

	@javax.validation.constraints.NotNull
	@javax.validation.constraints.Size(max = 100)
	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getDescription() {
		return this.description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.time.LocalDate getEstablished() {
		return this.established;
	}

	public void setEstablished(java.time.LocalDate established) {
		this.established = established;
	}

	@javax.validation.constraints.Size(max = 20)
	public java.lang.String getPhone() {
		return this.phone;
	}

	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	@javax.validation.constraints.Size(max = 100)
	public java.lang.String getWebsite() {
		return this.website;
	}

	public void setWebsite(java.lang.String website) {
		this.website = website;
	}

	@javax.validation.constraints.Size(max = 100)
	public java.lang.String getAddress() {
		return this.address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	@javax.validation.constraints.Size(max = 100)
	public java.lang.String getAddress2() {
		return this.address2;
	}

	public void setAddress2(java.lang.String address2) {
		this.address2 = address2;
	}

	@javax.validation.constraints.Size(max = 100)
	public java.lang.String getLocality() {
		return this.locality;
	}

	public void setLocality(java.lang.String locality) {
		this.locality = locality;
	}

	@javax.validation.constraints.Size(max = 20)
	public java.lang.String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(java.lang.String postalCode) {
		this.postalCode = postalCode;
	}

	@javax.validation.constraints.Size(max = 100)
	public java.lang.String getCountry() {
		return this.country;
	}

	public void setCountry(java.lang.String country) {
		this.country = country;
	}

	@javax.validation.constraints.NotNull
	public java.lang.Boolean getSearchable() {
		return this.searchable;
	}

	public void setSearchable(java.lang.Boolean searchable) {
		this.searchable = searchable;
	}

	@javax.validation.constraints.Size(max = 64)
	public java.lang.String getBreweryDbId() {
		return this.breweryDbId;
	}

	public void setBreweryDbId(java.lang.String breweryDbId) {
		this.breweryDbId = breweryDbId;
	}

	public java.sql.Timestamp getBreweryDbLastUpdated() {
		return this.breweryDbLastUpdated;
	}

	public void setBreweryDbLastUpdated(java.sql.Timestamp breweryDbLastUpdated) {
		this.breweryDbLastUpdated = breweryDbLastUpdated;
	}

	public java.lang.Boolean getLocked() {
		return this.locked;
	}

	public void setLocked(java.lang.Boolean locked) {
		this.locked = locked;
	}

	@javax.validation.constraints.NotNull
	public java.lang.Boolean getNeedsModeration() {
		return this.needsModeration;
	}

	public void setNeedsModeration(java.lang.Boolean needsModeration) {
		this.needsModeration = needsModeration;
	}

	@javax.validation.constraints.NotNull
	public java.sql.Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(java.sql.Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@javax.validation.constraints.NotNull
	public java.sql.Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(java.sql.Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
