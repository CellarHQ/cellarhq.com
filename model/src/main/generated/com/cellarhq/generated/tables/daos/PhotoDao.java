/*
 * This file is generated by jOOQ.
 */
package com.cellarhq.generated.tables.daos;


import com.cellarhq.generated.tables.Photo;
import com.cellarhq.generated.tables.records.PhotoRecord;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Configuration;
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
public class PhotoDao extends DAOImpl<PhotoRecord, com.cellarhq.generated.tables.pojos.Photo, Long> {

    /**
     * Create a new PhotoDao without any configuration
     */
    public PhotoDao() {
        super(Photo.PHOTO, com.cellarhq.generated.tables.pojos.Photo.class);
    }

    /**
     * Create a new PhotoDao with an attached configuration
     */
    public PhotoDao(Configuration configuration) {
        super(Photo.PHOTO, com.cellarhq.generated.tables.pojos.Photo.class, configuration);
    }

    @Override
    public Long getId(com.cellarhq.generated.tables.pojos.Photo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Photo.PHOTO.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchById(Long... values) {
        return fetch(Photo.PHOTO.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.cellarhq.generated.tables.pojos.Photo fetchOneById(Long value) {
        return fetchOne(Photo.PHOTO.ID, value);
    }

    /**
     * Fetch records that have <code>version BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchRangeOfVersion(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Photo.PHOTO.VERSION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>version IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchByVersion(Integer... values) {
        return fetch(Photo.PHOTO.VERSION, values);
    }

    /**
     * Fetch records that have <code>description BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchRangeOfDescription(String lowerInclusive, String upperInclusive) {
        return fetchRange(Photo.PHOTO.DESCRIPTION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>description IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchByDescription(String... values) {
        return fetch(Photo.PHOTO.DESCRIPTION, values);
    }

    /**
     * Fetch records that have <code>original_url BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchRangeOfOriginalUrl(String lowerInclusive, String upperInclusive) {
        return fetchRange(Photo.PHOTO.ORIGINAL_URL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>original_url IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchByOriginalUrl(String... values) {
        return fetch(Photo.PHOTO.ORIGINAL_URL, values);
    }

    /**
     * Fetch a unique record that has <code>original_url = value</code>
     */
    public com.cellarhq.generated.tables.pojos.Photo fetchOneByOriginalUrl(String value) {
        return fetchOne(Photo.PHOTO.ORIGINAL_URL, value);
    }

    /**
     * Fetch records that have <code>thumb_url BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchRangeOfThumbUrl(String lowerInclusive, String upperInclusive) {
        return fetchRange(Photo.PHOTO.THUMB_URL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>thumb_url IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchByThumbUrl(String... values) {
        return fetch(Photo.PHOTO.THUMB_URL, values);
    }

    /**
     * Fetch records that have <code>thumb_width BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchRangeOfThumbWidth(Short lowerInclusive, Short upperInclusive) {
        return fetchRange(Photo.PHOTO.THUMB_WIDTH, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>thumb_width IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchByThumbWidth(Short... values) {
        return fetch(Photo.PHOTO.THUMB_WIDTH, values);
    }

    /**
     * Fetch records that have <code>thumb_height BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchRangeOfThumbHeight(Short lowerInclusive, Short upperInclusive) {
        return fetchRange(Photo.PHOTO.THUMB_HEIGHT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>thumb_height IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchByThumbHeight(Short... values) {
        return fetch(Photo.PHOTO.THUMB_HEIGHT, values);
    }

    /**
     * Fetch records that have <code>large_url BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchRangeOfLargeUrl(String lowerInclusive, String upperInclusive) {
        return fetchRange(Photo.PHOTO.LARGE_URL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>large_url IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchByLargeUrl(String... values) {
        return fetch(Photo.PHOTO.LARGE_URL, values);
    }

    /**
     * Fetch records that have <code>large_width BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchRangeOfLargeWidth(Short lowerInclusive, Short upperInclusive) {
        return fetchRange(Photo.PHOTO.LARGE_WIDTH, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>large_width IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchByLargeWidth(Short... values) {
        return fetch(Photo.PHOTO.LARGE_WIDTH, values);
    }

    /**
     * Fetch records that have <code>large_height BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchRangeOfLargeHeight(Short lowerInclusive, Short upperInclusive) {
        return fetchRange(Photo.PHOTO.LARGE_HEIGHT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>large_height IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchByLargeHeight(Short... values) {
        return fetch(Photo.PHOTO.LARGE_HEIGHT, values);
    }

    /**
     * Fetch records that have <code>created_date BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchRangeOfCreatedDate(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Photo.PHOTO.CREATED_DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_date IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchByCreatedDate(LocalDateTime... values) {
        return fetch(Photo.PHOTO.CREATED_DATE, values);
    }

    /**
     * Fetch records that have <code>modified_date BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchRangeOfModifiedDate(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Photo.PHOTO.MODIFIED_DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modified_date IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Photo> fetchByModifiedDate(LocalDateTime... values) {
        return fetch(Photo.PHOTO.MODIFIED_DATE, values);
    }
}
