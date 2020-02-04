/*
 * This file is generated by jOOQ.
 */
package com.cellarhq.generated.tables.daos;


import com.cellarhq.generated.tables.Organization;
import com.cellarhq.generated.tables.records.OrganizationRecord;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.JSON;
import org.jooq.impl.DAOImpl;


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
public class OrganizationDao extends DAOImpl<OrganizationRecord, com.cellarhq.generated.tables.pojos.Organization, Long> {

    /**
     * Create a new OrganizationDao without any configuration
     */
    public OrganizationDao() {
        super(Organization.ORGANIZATION, com.cellarhq.generated.tables.pojos.Organization.class);
    }

    /**
     * Create a new OrganizationDao with an attached configuration
     */
    public OrganizationDao(Configuration configuration) {
        super(Organization.ORGANIZATION, com.cellarhq.generated.tables.pojos.Organization.class, configuration);
    }

    @Override
    public Long getId(com.cellarhq.generated.tables.pojos.Organization object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchById(Long... values) {
        return fetch(Organization.ORGANIZATION.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.cellarhq.generated.tables.pojos.Organization fetchOneById(Long value) {
        return fetchOne(Organization.ORGANIZATION.ID, value);
    }

    /**
     * Fetch records that have <code>version BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfVersion(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.VERSION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>version IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByVersion(Integer... values) {
        return fetch(Organization.ORGANIZATION.VERSION, values);
    }

    /**
     * Fetch records that have <code>photo_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfPhotoId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.PHOTO_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>photo_id IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByPhotoId(Long... values) {
        return fetch(Organization.ORGANIZATION.PHOTO_ID, values);
    }

    /**
     * Fetch records that have <code>type BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfType(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.TYPE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>type IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByType(String... values) {
        return fetch(Organization.ORGANIZATION.TYPE, values);
    }

    /**
     * Fetch records that have <code>slug BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfSlug(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.SLUG, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>slug IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchBySlug(String... values) {
        return fetch(Organization.ORGANIZATION.SLUG, values);
    }

    /**
     * Fetch a unique record that has <code>slug = value</code>
     */
    public com.cellarhq.generated.tables.pojos.Organization fetchOneBySlug(String value) {
        return fetchOne(Organization.ORGANIZATION.SLUG, value);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByName(String... values) {
        return fetch(Organization.ORGANIZATION.NAME, values);
    }

    /**
     * Fetch records that have <code>description BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfDescription(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.DESCRIPTION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>description IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByDescription(String... values) {
        return fetch(Organization.ORGANIZATION.DESCRIPTION, values);
    }

    /**
     * Fetch records that have <code>established BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfEstablished(Short lowerInclusive, Short upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.ESTABLISHED, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>established IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByEstablished(Short... values) {
        return fetch(Organization.ORGANIZATION.ESTABLISHED, values);
    }

    /**
     * Fetch records that have <code>phone BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfPhone(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.PHONE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>phone IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByPhone(String... values) {
        return fetch(Organization.ORGANIZATION.PHONE, values);
    }

    /**
     * Fetch records that have <code>website BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfWebsite(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.WEBSITE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>website IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByWebsite(String... values) {
        return fetch(Organization.ORGANIZATION.WEBSITE, values);
    }

    /**
     * Fetch records that have <code>address BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfAddress(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.ADDRESS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>address IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByAddress(String... values) {
        return fetch(Organization.ORGANIZATION.ADDRESS, values);
    }

    /**
     * Fetch records that have <code>address2 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfAddress2(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.ADDRESS2, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>address2 IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByAddress2(String... values) {
        return fetch(Organization.ORGANIZATION.ADDRESS2, values);
    }

    /**
     * Fetch records that have <code>locality BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfLocality(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.LOCALITY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>locality IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByLocality(String... values) {
        return fetch(Organization.ORGANIZATION.LOCALITY, values);
    }

    /**
     * Fetch records that have <code>postal_code BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfPostalCode(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.POSTAL_CODE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>postal_code IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByPostalCode(String... values) {
        return fetch(Organization.ORGANIZATION.POSTAL_CODE, values);
    }

    /**
     * Fetch records that have <code>country BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfCountry(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.COUNTRY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>country IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByCountry(String... values) {
        return fetch(Organization.ORGANIZATION.COUNTRY, values);
    }

    /**
     * Fetch records that have <code>brewery_db_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfBreweryDbId(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.BREWERY_DB_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>brewery_db_id IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByBreweryDbId(String... values) {
        return fetch(Organization.ORGANIZATION.BREWERY_DB_ID, values);
    }

    /**
     * Fetch records that have <code>brewery_db_last_updated BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfBreweryDbLastUpdated(Timestamp lowerInclusive, Timestamp upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.BREWERY_DB_LAST_UPDATED, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>brewery_db_last_updated IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByBreweryDbLastUpdated(Timestamp... values) {
        return fetch(Organization.ORGANIZATION.BREWERY_DB_LAST_UPDATED, values);
    }

    /**
     * Fetch records that have <code>locked BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfLocked(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.LOCKED, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>locked IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByLocked(Boolean... values) {
        return fetch(Organization.ORGANIZATION.LOCKED, values);
    }

    /**
     * Fetch records that have <code>needs_moderation BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfNeedsModeration(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.NEEDS_MODERATION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>needs_moderation IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByNeedsModeration(Boolean... values) {
        return fetch(Organization.ORGANIZATION.NEEDS_MODERATION, values);
    }

    /**
     * Fetch records that have <code>created_date BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfCreatedDate(Timestamp lowerInclusive, Timestamp upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.CREATED_DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_date IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByCreatedDate(Timestamp... values) {
        return fetch(Organization.ORGANIZATION.CREATED_DATE, values);
    }

    /**
     * Fetch records that have <code>modified_date BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfModifiedDate(Timestamp lowerInclusive, Timestamp upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.MODIFIED_DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modified_date IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByModifiedDate(Timestamp... values) {
        return fetch(Organization.ORGANIZATION.MODIFIED_DATE, values);
    }

    /**
     * Fetch records that have <code>data BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfData(JSON lowerInclusive, JSON upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.DATA, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>data IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByData(JSON... values) {
        return fetch(Organization.ORGANIZATION.DATA, values);
    }

    /**
     * Fetch records that have <code>region BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfRegion(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.REGION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>region IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByRegion(String... values) {
        return fetch(Organization.ORGANIZATION.REGION, values);
    }

    /**
     * Fetch records that have <code>warning_flag BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfWarningFlag(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.WARNING_FLAG, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>warning_flag IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByWarningFlag(Boolean... values) {
        return fetch(Organization.ORGANIZATION.WARNING_FLAG, values);
    }

    /**
     * Fetch records that have <code>total_beers BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfTotalBeers(Short lowerInclusive, Short upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.TOTAL_BEERS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>total_beers IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByTotalBeers(Short... values) {
        return fetch(Organization.ORGANIZATION.TOTAL_BEERS, values);
    }

    /**
     * Fetch records that have <code>cellared_beers BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfCellaredBeers(Short lowerInclusive, Short upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.CELLARED_BEERS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>cellared_beers IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByCellaredBeers(Short... values) {
        return fetch(Organization.ORGANIZATION.CELLARED_BEERS, values);
    }

    /**
     * Fetch records that have <code>contained_in_cellars BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfContainedInCellars(Short lowerInclusive, Short upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.CONTAINED_IN_CELLARS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>contained_in_cellars IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByContainedInCellars(Short... values) {
        return fetch(Organization.ORGANIZATION.CONTAINED_IN_CELLARS, values);
    }

    /**
     * Fetch records that have <code>locality_sort BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfLocalitySort(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.LOCALITY_SORT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>locality_sort IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByLocalitySort(String... values) {
        return fetch(Organization.ORGANIZATION.LOCALITY_SORT, values);
    }

    /**
     * Fetch records that have <code>collaboration BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchRangeOfCollaboration(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(Organization.ORGANIZATION.COLLABORATION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>collaboration IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Organization> fetchByCollaboration(Boolean... values) {
        return fetch(Organization.ORGANIZATION.COLLABORATION, values);
    }
}