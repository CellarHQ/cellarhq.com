/*
 * This file is generated by jOOQ.
 */
package com.cellarhq.generated.tables.records;


import com.cellarhq.generated.tables.Cellar;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name = "cellar", schema = "public", uniqueConstraints = {
    @UniqueConstraint(name = "pk_cellar", columnNames = {"id"}),
    @UniqueConstraint(name = "unq_cellar_screen_name", columnNames = {"screen_name"}),
    @UniqueConstraint(name = "cellar_slug_unique_constraint", columnNames = {"slug"})
}, indexes = {
    @Index(name = "cellar_slug_unique_constraint", unique = true, columnList = "slug ASC"),
    @Index(name = "pk_cellar", unique = true, columnList = "id ASC"),
    @Index(name = "unq_cellar_screen_name", unique = true, columnList = "screen_name ASC")
})
public class CellarRecord extends UpdatableRecordImpl<CellarRecord> {

    private static final long serialVersionUID = -1320003381;

    /**
     * Setter for <code>public.cellar.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.cellar.id</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, precision = 64)
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.cellar.version</code>.
     */
    public void setVersion(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.cellar.version</code>.
     */
    @Column(name = "version", nullable = false, precision = 32)
    public Integer getVersion() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.cellar.photo_id</code>.
     */
    public void setPhotoId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.cellar.photo_id</code>.
     */
    @Column(name = "photo_id", precision = 64)
    public Long getPhotoId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.cellar.screen_name</code>.
     */
    public void setScreenName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.cellar.screen_name</code>.
     */
    @Column(name = "screen_name", nullable = false, length = 64)
    public String getScreenName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.cellar.display_name</code>.
     */
    public void setDisplayName(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.cellar.display_name</code>.
     */
    @Column(name = "display_name", length = 64)
    public String getDisplayName() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.cellar.location</code>.
     */
    public void setLocation(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.cellar.location</code>.
     */
    @Column(name = "location", length = 100)
    public String getLocation() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.cellar.website</code>.
     */
    public void setWebsite(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.cellar.website</code>.
     */
    @Column(name = "website", length = 100)
    public String getWebsite() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.cellar.bio</code>.
     */
    public void setBio(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.cellar.bio</code>.
     */
    @Column(name = "bio")
    public String getBio() {
        return (String) get(7);
    }

    /**
     * Setter for <code>public.cellar.update_from_network</code>.
     */
    public void setUpdateFromNetwork(Boolean value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.cellar.update_from_network</code>.
     */
    @Column(name = "update_from_network", nullable = false)
    public Boolean getUpdateFromNetwork() {
        return (Boolean) get(8);
    }

    /**
     * Setter for <code>public.cellar.contact_email</code>.
     */
    public void setContactEmail(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.cellar.contact_email</code>.
     */
    @Column(name = "contact_email", length = 255)
    public String getContactEmail() {
        return (String) get(9);
    }

    /**
     * Setter for <code>public.cellar.private</code>.
     */
    public void setPrivate(Boolean value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.cellar.private</code>.
     */
    @Column(name = "private", nullable = false)
    public Boolean getPrivate() {
        return (Boolean) get(10);
    }

    /**
     * Setter for <code>public.cellar.last_login</code>.
     */
    public void setLastLogin(Timestamp value) {
        set(11, value);
    }

    /**
     * Getter for <code>public.cellar.last_login</code>.
     */
    @Column(name = "last_login")
    public Timestamp getLastLogin() {
        return (Timestamp) get(11);
    }

    /**
     * Setter for <code>public.cellar.last_login_ip</code>.
     */
    public void setLastLoginIp(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>public.cellar.last_login_ip</code>.
     */
    @Column(name = "last_login_ip", length = 30)
    public String getLastLoginIp() {
        return (String) get(12);
    }

    /**
     * Setter for <code>public.cellar.created_date</code>.
     */
    public void setCreatedDate(Timestamp value) {
        set(13, value);
    }

    /**
     * Getter for <code>public.cellar.created_date</code>.
     */
    @Column(name = "created_date", nullable = false)
    public Timestamp getCreatedDate() {
        return (Timestamp) get(13);
    }

    /**
     * Setter for <code>public.cellar.modified_date</code>.
     */
    public void setModifiedDate(Timestamp value) {
        set(14, value);
    }

    /**
     * Getter for <code>public.cellar.modified_date</code>.
     */
    @Column(name = "modified_date", nullable = false)
    public Timestamp getModifiedDate() {
        return (Timestamp) get(14);
    }

    /**
     * Setter for <code>public.cellar.twitter</code>.
     */
    public void setTwitter(String value) {
        set(15, value);
    }

    /**
     * Getter for <code>public.cellar.twitter</code>.
     */
    @Column(name = "twitter", length = 16)
    public String getTwitter() {
        return (String) get(15);
    }

    /**
     * Setter for <code>public.cellar.reddit</code>.
     */
    public void setReddit(String value) {
        set(16, value);
    }

    /**
     * Getter for <code>public.cellar.reddit</code>.
     */
    @Column(name = "reddit", length = 20)
    public String getReddit() {
        return (String) get(16);
    }

    /**
     * Setter for <code>public.cellar.beeradvocate</code>.
     */
    public void setBeeradvocate(String value) {
        set(17, value);
    }

    /**
     * Getter for <code>public.cellar.beeradvocate</code>.
     */
    @Column(name = "beeradvocate", length = 255)
    public String getBeeradvocate() {
        return (String) get(17);
    }

    /**
     * Setter for <code>public.cellar.ratebeer</code>.
     */
    public void setRatebeer(String value) {
        set(18, value);
    }

    /**
     * Getter for <code>public.cellar.ratebeer</code>.
     */
    @Column(name = "ratebeer", length = 255)
    public String getRatebeer() {
        return (String) get(18);
    }

    /**
     * Setter for <code>public.cellar.total_beers</code>.
     */
    public void setTotalBeers(Short value) {
        set(19, value);
    }

    /**
     * Getter for <code>public.cellar.total_beers</code>.
     */
    @Column(name = "total_beers", nullable = false, precision = 16)
    public Short getTotalBeers() {
        return (Short) get(19);
    }

    /**
     * Setter for <code>public.cellar.unique_beers</code>.
     */
    public void setUniqueBeers(Short value) {
        set(20, value);
    }

    /**
     * Getter for <code>public.cellar.unique_beers</code>.
     */
    @Column(name = "unique_beers", nullable = false, precision = 16)
    public Short getUniqueBeers() {
        return (Short) get(20);
    }

    /**
     * Setter for <code>public.cellar.unique_breweries</code>.
     */
    public void setUniqueBreweries(Short value) {
        set(21, value);
    }

    /**
     * Getter for <code>public.cellar.unique_breweries</code>.
     */
    @Column(name = "unique_breweries", nullable = false, precision = 16)
    public Short getUniqueBreweries() {
        return (Short) get(21);
    }

    /**
     * Setter for <code>public.cellar.has_tradeable_beers</code>.
     */
    public void setHasTradeableBeers(Boolean value) {
        set(22, value);
    }

    /**
     * Getter for <code>public.cellar.has_tradeable_beers</code>.
     */
    @Column(name = "has_tradeable_beers", nullable = false)
    public Boolean getHasTradeableBeers() {
        return (Boolean) get(22);
    }

    /**
     * Setter for <code>public.cellar.slug</code>.
     */
    public void setSlug(String value) {
        set(23, value);
    }

    /**
     * Getter for <code>public.cellar.slug</code>.
     */
    @Column(name = "slug", nullable = false, length = 64)
    public String getSlug() {
        return (String) get(23);
    }

    /**
     * Setter for <code>public.cellar.role</code>.
     */
    public void setRole(String value) {
        set(24, value);
    }

    /**
     * Getter for <code>public.cellar.role</code>.
     */
    @Column(name = "role", nullable = false)
    public String getRole() {
        return (String) get(24);
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
     * Create a detached CellarRecord
     */
    public CellarRecord() {
        super(Cellar.CELLAR);
    }

    /**
     * Create a detached, initialised CellarRecord
     */
    public CellarRecord(Long id, Integer version, Long photoId, String screenName, String displayName, String location, String website, String bio, Boolean updateFromNetwork, String contactEmail, Boolean private_, Timestamp lastLogin, String lastLoginIp, Timestamp createdDate, Timestamp modifiedDate, String twitter, String reddit, String beeradvocate, String ratebeer, Short totalBeers, Short uniqueBeers, Short uniqueBreweries, Boolean hasTradeableBeers, String slug, String role) {
        super(Cellar.CELLAR);

        set(0, id);
        set(1, version);
        set(2, photoId);
        set(3, screenName);
        set(4, displayName);
        set(5, location);
        set(6, website);
        set(7, bio);
        set(8, updateFromNetwork);
        set(9, contactEmail);
        set(10, private_);
        set(11, lastLogin);
        set(12, lastLoginIp);
        set(13, createdDate);
        set(14, modifiedDate);
        set(15, twitter);
        set(16, reddit);
        set(17, beeradvocate);
        set(18, ratebeer);
        set(19, totalBeers);
        set(20, uniqueBeers);
        set(21, uniqueBreweries);
        set(22, hasTradeableBeers);
        set(23, slug);
        set(24, role);
    }
}