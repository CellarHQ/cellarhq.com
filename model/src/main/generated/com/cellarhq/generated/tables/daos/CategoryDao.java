/*
 * This file is generated by jOOQ.
 */
package com.cellarhq.generated.tables.daos;


import com.cellarhq.generated.tables.Category;
import com.cellarhq.generated.tables.records.CategoryRecord;

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
public class CategoryDao extends DAOImpl<CategoryRecord, com.cellarhq.generated.tables.pojos.Category, Long> {

    /**
     * Create a new CategoryDao without any configuration
     */
    public CategoryDao() {
        super(Category.CATEGORY, com.cellarhq.generated.tables.pojos.Category.class);
    }

    /**
     * Create a new CategoryDao with an attached configuration
     */
    public CategoryDao(Configuration configuration) {
        super(Category.CATEGORY, com.cellarhq.generated.tables.pojos.Category.class, configuration);
    }

    @Override
    public Long getId(com.cellarhq.generated.tables.pojos.Category object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Category.CATEGORY.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchById(Long... values) {
        return fetch(Category.CATEGORY.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.cellarhq.generated.tables.pojos.Category fetchOneById(Long value) {
        return fetchOne(Category.CATEGORY.ID, value);
    }

    /**
     * Fetch records that have <code>version BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchRangeOfVersion(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Category.CATEGORY.VERSION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>version IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchByVersion(Integer... values) {
        return fetch(Category.CATEGORY.VERSION, values);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Category.CATEGORY.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchByName(String... values) {
        return fetch(Category.CATEGORY.NAME, values);
    }

    /**
     * Fetch records that have <code>description BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchRangeOfDescription(String lowerInclusive, String upperInclusive) {
        return fetchRange(Category.CATEGORY.DESCRIPTION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>description IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchByDescription(String... values) {
        return fetch(Category.CATEGORY.DESCRIPTION, values);
    }

    /**
     * Fetch records that have <code>searchable BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchRangeOfSearchable(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(Category.CATEGORY.SEARCHABLE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>searchable IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchBySearchable(Boolean... values) {
        return fetch(Category.CATEGORY.SEARCHABLE, values);
    }

    /**
     * Fetch records that have <code>brewery_db_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchRangeOfBreweryDbId(String lowerInclusive, String upperInclusive) {
        return fetchRange(Category.CATEGORY.BREWERY_DB_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>brewery_db_id IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchByBreweryDbId(String... values) {
        return fetch(Category.CATEGORY.BREWERY_DB_ID, values);
    }

    /**
     * Fetch records that have <code>brewery_db_last_updated BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchRangeOfBreweryDbLastUpdated(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Category.CATEGORY.BREWERY_DB_LAST_UPDATED, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>brewery_db_last_updated IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchByBreweryDbLastUpdated(LocalDateTime... values) {
        return fetch(Category.CATEGORY.BREWERY_DB_LAST_UPDATED, values);
    }

    /**
     * Fetch records that have <code>created_date BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchRangeOfCreatedDate(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Category.CATEGORY.CREATED_DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_date IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchByCreatedDate(LocalDateTime... values) {
        return fetch(Category.CATEGORY.CREATED_DATE, values);
    }

    /**
     * Fetch records that have <code>modified_date BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchRangeOfModifiedDate(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Category.CATEGORY.MODIFIED_DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modified_date IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchByModifiedDate(LocalDateTime... values) {
        return fetch(Category.CATEGORY.MODIFIED_DATE, values);
    }

    /**
     * Fetch records that have <code>data BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchRangeOfData(JSON lowerInclusive, JSON upperInclusive) {
        return fetchRange(Category.CATEGORY.DATA, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>data IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Category> fetchByData(JSON... values) {
        return fetch(Category.CATEGORY.DATA, values);
    }
}
