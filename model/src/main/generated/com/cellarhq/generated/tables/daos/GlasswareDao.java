/*
 * This file is generated by jOOQ.
 */
package com.cellarhq.generated.tables.daos;


import com.cellarhq.generated.tables.Glassware;
import com.cellarhq.generated.tables.records.GlasswareRecord;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.processing.Generated;

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
public class GlasswareDao extends DAOImpl<GlasswareRecord, com.cellarhq.generated.tables.pojos.Glassware, Long> {

    /**
     * Create a new GlasswareDao without any configuration
     */
    public GlasswareDao() {
        super(Glassware.GLASSWARE, com.cellarhq.generated.tables.pojos.Glassware.class);
    }

    /**
     * Create a new GlasswareDao with an attached configuration
     */
    public GlasswareDao(Configuration configuration) {
        super(Glassware.GLASSWARE, com.cellarhq.generated.tables.pojos.Glassware.class, configuration);
    }

    @Override
    public Long getId(com.cellarhq.generated.tables.pojos.Glassware object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Glassware.GLASSWARE.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchById(Long... values) {
        return fetch(Glassware.GLASSWARE.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.cellarhq.generated.tables.pojos.Glassware fetchOneById(Long value) {
        return fetchOne(Glassware.GLASSWARE.ID, value);
    }

    /**
     * Fetch records that have <code>version BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchRangeOfVersion(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Glassware.GLASSWARE.VERSION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>version IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchByVersion(Integer... values) {
        return fetch(Glassware.GLASSWARE.VERSION, values);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Glassware.GLASSWARE.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchByName(String... values) {
        return fetch(Glassware.GLASSWARE.NAME, values);
    }

    /**
     * Fetch records that have <code>description BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchRangeOfDescription(String lowerInclusive, String upperInclusive) {
        return fetchRange(Glassware.GLASSWARE.DESCRIPTION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>description IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchByDescription(String... values) {
        return fetch(Glassware.GLASSWARE.DESCRIPTION, values);
    }

    /**
     * Fetch records that have <code>searchable BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchRangeOfSearchable(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(Glassware.GLASSWARE.SEARCHABLE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>searchable IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchBySearchable(Boolean... values) {
        return fetch(Glassware.GLASSWARE.SEARCHABLE, values);
    }

    /**
     * Fetch records that have <code>brewery_db_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchRangeOfBreweryDbId(String lowerInclusive, String upperInclusive) {
        return fetchRange(Glassware.GLASSWARE.BREWERY_DB_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>brewery_db_id IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchByBreweryDbId(String... values) {
        return fetch(Glassware.GLASSWARE.BREWERY_DB_ID, values);
    }

    /**
     * Fetch records that have <code>brewery_db_last_updated BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchRangeOfBreweryDbLastUpdated(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Glassware.GLASSWARE.BREWERY_DB_LAST_UPDATED, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>brewery_db_last_updated IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchByBreweryDbLastUpdated(LocalDateTime... values) {
        return fetch(Glassware.GLASSWARE.BREWERY_DB_LAST_UPDATED, values);
    }

    /**
     * Fetch records that have <code>created_date BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchRangeOfCreatedDate(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Glassware.GLASSWARE.CREATED_DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_date IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchByCreatedDate(LocalDateTime... values) {
        return fetch(Glassware.GLASSWARE.CREATED_DATE, values);
    }

    /**
     * Fetch records that have <code>modified_date BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchRangeOfModifiedDate(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Glassware.GLASSWARE.MODIFIED_DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modified_date IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchByModifiedDate(LocalDateTime... values) {
        return fetch(Glassware.GLASSWARE.MODIFIED_DATE, values);
    }

    /**
     * Fetch records that have <code>data BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchRangeOfData(JSON lowerInclusive, JSON upperInclusive) {
        return fetchRange(Glassware.GLASSWARE.DATA, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>data IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Glassware> fetchByData(JSON... values) {
        return fetch(Glassware.GLASSWARE.DATA, values);
    }
}
