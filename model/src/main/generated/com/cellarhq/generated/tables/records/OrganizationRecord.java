/*
 * This file is generated by jOOQ.
 */
package com.cellarhq.generated.tables.records;


import com.cellarhq.generated.tables.Organization;

import java.time.LocalDateTime;

import javax.annotation.processing.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.jooq.JSON;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


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
@Entity
@Table(name = "organization", schema = "public", uniqueConstraints = {
    @UniqueConstraint(name = "organization_pkey", columnNames = {"id"}),
    @UniqueConstraint(name = "unq_organization_slug", columnNames = {"slug"})
}, indexes = {
    @Index(name = "organization_pkey", unique = true, columnList = "id ASC"),
    @Index(name = "unq_organization_slug", unique = true, columnList = "slug ASC")
})
public class OrganizationRecord extends UpdatableRecordImpl<OrganizationRecord> {

    private static final long serialVersionUID = 633422463;

    /**
     * Setter for <code>public.organization.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.organization.id</code>.
     */
    @Id
    @Column(name = "id", nullable = false, precision = 64)
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.organization.version</code>.
     */
    public void setVersion(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.organization.version</code>.
     */
    @Column(name = "version", nullable = false, precision = 32)
    public Integer getVersion() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.organization.photo_id</code>.
     */
    public void setPhotoId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.organization.photo_id</code>.
     */
    @Column(name = "photo_id", precision = 64)
    public Long getPhotoId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.organization.type</code>.
     */
    public void setType(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.organization.type</code>.
     */
    @Column(name = "type", nullable = false, length = 16)
    public String getType() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.organization.slug</code>.
     */
    public void setSlug(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.organization.slug</code>.
     */
    @Column(name = "slug", nullable = false, length = 100)
    public String getSlug() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.organization.name</code>.
     */
    public void setName(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.organization.name</code>.
     */
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.organization.description</code>.
     */
    public void setDescription(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.organization.description</code>.
     */
    @Column(name = "description")
    public String getDescription() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.organization.established</code>.
     */
    public void setEstablished(Short value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.organization.established</code>.
     */
    @Column(name = "established", precision = 16)
    public Short getEstablished() {
        return (Short) get(7);
    }

    /**
     * Setter for <code>public.organization.phone</code>.
     */
    public void setPhone(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.organization.phone</code>.
     */
    @Column(name = "phone", length = 30)
    public String getPhone() {
        return (String) get(8);
    }

    /**
     * Setter for <code>public.organization.website</code>.
     */
    public void setWebsite(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.organization.website</code>.
     */
    @Column(name = "website", length = 100)
    public String getWebsite() {
        return (String) get(9);
    }

    /**
     * Setter for <code>public.organization.address</code>.
     */
    public void setAddress(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.organization.address</code>.
     */
    @Column(name = "address", length = 100)
    public String getAddress() {
        return (String) get(10);
    }

    /**
     * Setter for <code>public.organization.address2</code>.
     */
    public void setAddress2(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>public.organization.address2</code>.
     */
    @Column(name = "address2", length = 100)
    public String getAddress2() {
        return (String) get(11);
    }

    /**
     * Setter for <code>public.organization.locality</code>.
     */
    public void setLocality(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>public.organization.locality</code>.
     */
    @Column(name = "locality", length = 100)
    public String getLocality() {
        return (String) get(12);
    }

    /**
     * Setter for <code>public.organization.postal_code</code>.
     */
    public void setPostalCode(String value) {
        set(13, value);
    }

    /**
     * Getter for <code>public.organization.postal_code</code>.
     */
    @Column(name = "postal_code", length = 20)
    public String getPostalCode() {
        return (String) get(13);
    }

    /**
     * Setter for <code>public.organization.country</code>.
     */
    public void setCountry(String value) {
        set(14, value);
    }

    /**
     * Getter for <code>public.organization.country</code>.
     */
    @Column(name = "country", length = 100)
    public String getCountry() {
        return (String) get(14);
    }

    /**
     * Setter for <code>public.organization.brewery_db_id</code>.
     */
    public void setBreweryDbId(String value) {
        set(15, value);
    }

    /**
     * Getter for <code>public.organization.brewery_db_id</code>.
     */
    @Column(name = "brewery_db_id", length = 64)
    public String getBreweryDbId() {
        return (String) get(15);
    }

    /**
     * Setter for <code>public.organization.brewery_db_last_updated</code>.
     */
    public void setBreweryDbLastUpdated(LocalDateTime value) {
        set(16, value);
    }

    /**
     * Getter for <code>public.organization.brewery_db_last_updated</code>.
     */
    @Column(name = "brewery_db_last_updated")
    public LocalDateTime getBreweryDbLastUpdated() {
        return (LocalDateTime) get(16);
    }

    /**
     * Setter for <code>public.organization.locked</code>.
     */
    public void setLocked(Boolean value) {
        set(17, value);
    }

    /**
     * Getter for <code>public.organization.locked</code>.
     */
    @Column(name = "locked")
    public Boolean getLocked() {
        return (Boolean) get(17);
    }

    /**
     * Setter for <code>public.organization.needs_moderation</code>.
     */
    public void setNeedsModeration(Boolean value) {
        set(18, value);
    }

    /**
     * Getter for <code>public.organization.needs_moderation</code>.
     */
    @Column(name = "needs_moderation", nullable = false)
    public Boolean getNeedsModeration() {
        return (Boolean) get(18);
    }

    /**
     * Setter for <code>public.organization.created_date</code>.
     */
    public void setCreatedDate(LocalDateTime value) {
        set(19, value);
    }

    /**
     * Getter for <code>public.organization.created_date</code>.
     */
    @Column(name = "created_date", nullable = false)
    public LocalDateTime getCreatedDate() {
        return (LocalDateTime) get(19);
    }

    /**
     * Setter for <code>public.organization.modified_date</code>.
     */
    public void setModifiedDate(LocalDateTime value) {
        set(20, value);
    }

    /**
     * Getter for <code>public.organization.modified_date</code>.
     */
    @Column(name = "modified_date", nullable = false)
    public LocalDateTime getModifiedDate() {
        return (LocalDateTime) get(20);
    }

    /**
     * Setter for <code>public.organization.data</code>.
     */
    public void setData(JSON value) {
        set(21, value);
    }

    /**
     * Getter for <code>public.organization.data</code>.
     */
    @Column(name = "data")
    public JSON getData() {
        return (JSON) get(21);
    }

    /**
     * Setter for <code>public.organization.region</code>.
     */
    public void setRegion(String value) {
        set(22, value);
    }

    /**
     * Getter for <code>public.organization.region</code>.
     */
    @Column(name = "region", length = 100)
    public String getRegion() {
        return (String) get(22);
    }

    /**
     * Setter for <code>public.organization.warning_flag</code>.
     */
    public void setWarningFlag(Boolean value) {
        set(23, value);
    }

    /**
     * Getter for <code>public.organization.warning_flag</code>.
     */
    @Column(name = "warning_flag", nullable = false)
    public Boolean getWarningFlag() {
        return (Boolean) get(23);
    }

    /**
     * Setter for <code>public.organization.total_beers</code>.
     */
    public void setTotalBeers(Short value) {
        set(24, value);
    }

    /**
     * Getter for <code>public.organization.total_beers</code>.
     */
    @Column(name = "total_beers", nullable = false, precision = 16)
    public Short getTotalBeers() {
        return (Short) get(24);
    }

    /**
     * Setter for <code>public.organization.cellared_beers</code>.
     */
    public void setCellaredBeers(Short value) {
        set(25, value);
    }

    /**
     * Getter for <code>public.organization.cellared_beers</code>.
     */
    @Column(name = "cellared_beers", nullable = false, precision = 16)
    public Short getCellaredBeers() {
        return (Short) get(25);
    }

    /**
     * Setter for <code>public.organization.contained_in_cellars</code>.
     */
    public void setContainedInCellars(Short value) {
        set(26, value);
    }

    /**
     * Getter for <code>public.organization.contained_in_cellars</code>.
     */
    @Column(name = "contained_in_cellars", nullable = false, precision = 16)
    public Short getContainedInCellars() {
        return (Short) get(26);
    }

    /**
     * Setter for <code>public.organization.locality_sort</code>.
     */
    public void setLocalitySort(String value) {
        set(27, value);
    }

    /**
     * Getter for <code>public.organization.locality_sort</code>.
     */
    @Column(name = "locality_sort", length = 100)
    public String getLocalitySort() {
        return (String) get(27);
    }

    /**
     * Setter for <code>public.organization.collaboration</code>.
     */
    public void setCollaboration(Boolean value) {
        set(28, value);
    }

    /**
     * Getter for <code>public.organization.collaboration</code>.
     */
    @Column(name = "collaboration", nullable = false)
    public Boolean getCollaboration() {
        return (Boolean) get(28);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OrganizationRecord
     */
    public OrganizationRecord() {
        super(Organization.ORGANIZATION);
    }

    /**
     * Create a detached, initialised OrganizationRecord
     */
    public OrganizationRecord(Long id, Integer version, Long photoId, String type, String slug, String name, String description, Short established, String phone, String website, String address, String address2, String locality, String postalCode, String country, String breweryDbId, LocalDateTime breweryDbLastUpdated, Boolean locked, Boolean needsModeration, LocalDateTime createdDate, LocalDateTime modifiedDate, JSON data, String region, Boolean warningFlag, Short totalBeers, Short cellaredBeers, Short containedInCellars, String localitySort, Boolean collaboration) {
        super(Organization.ORGANIZATION);

        set(0, id);
        set(1, version);
        set(2, photoId);
        set(3, type);
        set(4, slug);
        set(5, name);
        set(6, description);
        set(7, established);
        set(8, phone);
        set(9, website);
        set(10, address);
        set(11, address2);
        set(12, locality);
        set(13, postalCode);
        set(14, country);
        set(15, breweryDbId);
        set(16, breweryDbLastUpdated);
        set(17, locked);
        set(18, needsModeration);
        set(19, createdDate);
        set(20, modifiedDate);
        set(21, data);
        set(22, region);
        set(23, warningFlag);
        set(24, totalBeers);
        set(25, cellaredBeers);
        set(26, containedInCellars);
        set(27, localitySort);
        set(28, collaboration);
    }
}
