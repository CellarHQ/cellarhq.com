/*
 * This file is generated by jOOQ.
 */
package com.cellarhq.generated.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.processing.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.jooq.JSON;


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
public class Organization implements Serializable {

    private static final long serialVersionUID = -856338605;

    private Long          id;
    private Integer       version;
    private Long          photoId;
    private String        type;
    private String        slug;
    private String        name;
    private String        description;
    private Short         established;
    private String        phone;
    private String        website;
    private String        address;
    private String        address2;
    private String        locality;
    private String        postalCode;
    private String        country;
    private String        breweryDbId;
    private LocalDateTime breweryDbLastUpdated;
    private Boolean       locked;
    private Boolean       needsModeration;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private JSON          data;
    private String        region;
    private Boolean       warningFlag;
    private Short         totalBeers;
    private Short         cellaredBeers;
    private Short         containedInCellars;
    private String        localitySort;
    private Boolean       collaboration;

    public Organization() {}

    public Organization(Organization value) {
        this.id = value.id;
        this.version = value.version;
        this.photoId = value.photoId;
        this.type = value.type;
        this.slug = value.slug;
        this.name = value.name;
        this.description = value.description;
        this.established = value.established;
        this.phone = value.phone;
        this.website = value.website;
        this.address = value.address;
        this.address2 = value.address2;
        this.locality = value.locality;
        this.postalCode = value.postalCode;
        this.country = value.country;
        this.breweryDbId = value.breweryDbId;
        this.breweryDbLastUpdated = value.breweryDbLastUpdated;
        this.locked = value.locked;
        this.needsModeration = value.needsModeration;
        this.createdDate = value.createdDate;
        this.modifiedDate = value.modifiedDate;
        this.data = value.data;
        this.region = value.region;
        this.warningFlag = value.warningFlag;
        this.totalBeers = value.totalBeers;
        this.cellaredBeers = value.cellaredBeers;
        this.containedInCellars = value.containedInCellars;
        this.localitySort = value.localitySort;
        this.collaboration = value.collaboration;
    }

    public Organization(
        Long          id,
        Integer       version,
        Long          photoId,
        String        type,
        String        slug,
        String        name,
        String        description,
        Short         established,
        String        phone,
        String        website,
        String        address,
        String        address2,
        String        locality,
        String        postalCode,
        String        country,
        String        breweryDbId,
        LocalDateTime breweryDbLastUpdated,
        Boolean       locked,
        Boolean       needsModeration,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate,
        JSON          data,
        String        region,
        Boolean       warningFlag,
        Short         totalBeers,
        Short         cellaredBeers,
        Short         containedInCellars,
        String        localitySort,
        Boolean       collaboration
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
        this.breweryDbId = breweryDbId;
        this.breweryDbLastUpdated = breweryDbLastUpdated;
        this.locked = locked;
        this.needsModeration = needsModeration;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.data = data;
        this.region = region;
        this.warningFlag = warningFlag;
        this.totalBeers = totalBeers;
        this.cellaredBeers = cellaredBeers;
        this.containedInCellars = containedInCellars;
        this.localitySort = localitySort;
        this.collaboration = collaboration;
    }

    @Id
    @Column(name = "id", nullable = false, precision = 64)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "version", nullable = false, precision = 32)
    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name = "photo_id", precision = 64)
    public Long getPhotoId() {
        return this.photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    @Column(name = "type", nullable = false, length = 16)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "slug", nullable = false, length = 100)
    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "established", precision = 16)
    public Short getEstablished() {
        return this.established;
    }

    public void setEstablished(Short established) {
        this.established = established;
    }

    @Column(name = "phone", length = 30)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "website", length = 100)
    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Column(name = "address", length = 100)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "address2", length = 100)
    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Column(name = "locality", length = 100)
    public String getLocality() {
        return this.locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    @Column(name = "postal_code", length = 20)
    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Column(name = "country", length = 100)
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "brewery_db_id", length = 64)
    public String getBreweryDbId() {
        return this.breweryDbId;
    }

    public void setBreweryDbId(String breweryDbId) {
        this.breweryDbId = breweryDbId;
    }

    @Column(name = "brewery_db_last_updated")
    public LocalDateTime getBreweryDbLastUpdated() {
        return this.breweryDbLastUpdated;
    }

    public void setBreweryDbLastUpdated(LocalDateTime breweryDbLastUpdated) {
        this.breweryDbLastUpdated = breweryDbLastUpdated;
    }

    @Column(name = "locked")
    public Boolean getLocked() {
        return this.locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @Column(name = "needs_moderation", nullable = false)
    public Boolean getNeedsModeration() {
        return this.needsModeration;
    }

    public void setNeedsModeration(Boolean needsModeration) {
        this.needsModeration = needsModeration;
    }

    @Column(name = "created_date", nullable = false)
    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "modified_date", nullable = false)
    public LocalDateTime getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Column(name = "data")
    public JSON getData() {
        return this.data;
    }

    public void setData(JSON data) {
        this.data = data;
    }

    @Column(name = "region", length = 100)
    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Column(name = "warning_flag", nullable = false)
    public Boolean getWarningFlag() {
        return this.warningFlag;
    }

    public void setWarningFlag(Boolean warningFlag) {
        this.warningFlag = warningFlag;
    }

    @Column(name = "total_beers", nullable = false, precision = 16)
    public Short getTotalBeers() {
        return this.totalBeers;
    }

    public void setTotalBeers(Short totalBeers) {
        this.totalBeers = totalBeers;
    }

    @Column(name = "cellared_beers", nullable = false, precision = 16)
    public Short getCellaredBeers() {
        return this.cellaredBeers;
    }

    public void setCellaredBeers(Short cellaredBeers) {
        this.cellaredBeers = cellaredBeers;
    }

    @Column(name = "contained_in_cellars", nullable = false, precision = 16)
    public Short getContainedInCellars() {
        return this.containedInCellars;
    }

    public void setContainedInCellars(Short containedInCellars) {
        this.containedInCellars = containedInCellars;
    }

    @Column(name = "locality_sort", length = 100)
    public String getLocalitySort() {
        return this.localitySort;
    }

    public void setLocalitySort(String localitySort) {
        this.localitySort = localitySort;
    }

    @Column(name = "collaboration", nullable = false)
    public Boolean getCollaboration() {
        return this.collaboration;
    }

    public void setCollaboration(Boolean collaboration) {
        this.collaboration = collaboration;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Organization (");

        sb.append(id);
        sb.append(", ").append(version);
        sb.append(", ").append(photoId);
        sb.append(", ").append(type);
        sb.append(", ").append(slug);
        sb.append(", ").append(name);
        sb.append(", ").append(description);
        sb.append(", ").append(established);
        sb.append(", ").append(phone);
        sb.append(", ").append(website);
        sb.append(", ").append(address);
        sb.append(", ").append(address2);
        sb.append(", ").append(locality);
        sb.append(", ").append(postalCode);
        sb.append(", ").append(country);
        sb.append(", ").append(breweryDbId);
        sb.append(", ").append(breweryDbLastUpdated);
        sb.append(", ").append(locked);
        sb.append(", ").append(needsModeration);
        sb.append(", ").append(createdDate);
        sb.append(", ").append(modifiedDate);
        sb.append(", ").append(data);
        sb.append(", ").append(region);
        sb.append(", ").append(warningFlag);
        sb.append(", ").append(totalBeers);
        sb.append(", ").append(cellaredBeers);
        sb.append(", ").append(containedInCellars);
        sb.append(", ").append(localitySort);
        sb.append(", ").append(collaboration);

        sb.append(")");
        return sb.toString();
    }
}
