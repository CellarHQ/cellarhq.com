/*
 * This file is generated by jOOQ.
 */
package com.cellarhq.generated.tables.daos;


import com.cellarhq.generated.tables.Cellar;
import com.cellarhq.generated.tables.records.CellarRecord;

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
public class CellarDao extends DAOImpl<CellarRecord, com.cellarhq.generated.tables.pojos.Cellar, Long> {

    /**
     * Create a new CellarDao without any configuration
     */
    public CellarDao() {
        super(Cellar.CELLAR, com.cellarhq.generated.tables.pojos.Cellar.class);
    }

    /**
     * Create a new CellarDao with an attached configuration
     */
    public CellarDao(Configuration configuration) {
        super(Cellar.CELLAR, com.cellarhq.generated.tables.pojos.Cellar.class, configuration);
    }

    @Override
    public Long getId(com.cellarhq.generated.tables.pojos.Cellar object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Cellar.CELLAR.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchById(Long... values) {
        return fetch(Cellar.CELLAR.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.cellarhq.generated.tables.pojos.Cellar fetchOneById(Long value) {
        return fetchOne(Cellar.CELLAR.ID, value);
    }

    /**
     * Fetch records that have <code>version BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfVersion(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Cellar.CELLAR.VERSION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>version IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByVersion(Integer... values) {
        return fetch(Cellar.CELLAR.VERSION, values);
    }

    /**
     * Fetch records that have <code>photo_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfPhotoId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Cellar.CELLAR.PHOTO_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>photo_id IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByPhotoId(Long... values) {
        return fetch(Cellar.CELLAR.PHOTO_ID, values);
    }

    /**
     * Fetch records that have <code>screen_name BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfScreenName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Cellar.CELLAR.SCREEN_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>screen_name IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByScreenName(String... values) {
        return fetch(Cellar.CELLAR.SCREEN_NAME, values);
    }

    /**
     * Fetch a unique record that has <code>screen_name = value</code>
     */
    public com.cellarhq.generated.tables.pojos.Cellar fetchOneByScreenName(String value) {
        return fetchOne(Cellar.CELLAR.SCREEN_NAME, value);
    }

    /**
     * Fetch records that have <code>display_name BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfDisplayName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Cellar.CELLAR.DISPLAY_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>display_name IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByDisplayName(String... values) {
        return fetch(Cellar.CELLAR.DISPLAY_NAME, values);
    }

    /**
     * Fetch records that have <code>location BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfLocation(String lowerInclusive, String upperInclusive) {
        return fetchRange(Cellar.CELLAR.LOCATION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>location IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByLocation(String... values) {
        return fetch(Cellar.CELLAR.LOCATION, values);
    }

    /**
     * Fetch records that have <code>website BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfWebsite(String lowerInclusive, String upperInclusive) {
        return fetchRange(Cellar.CELLAR.WEBSITE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>website IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByWebsite(String... values) {
        return fetch(Cellar.CELLAR.WEBSITE, values);
    }

    /**
     * Fetch records that have <code>bio BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfBio(String lowerInclusive, String upperInclusive) {
        return fetchRange(Cellar.CELLAR.BIO, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>bio IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByBio(String... values) {
        return fetch(Cellar.CELLAR.BIO, values);
    }

    /**
     * Fetch records that have <code>update_from_network BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfUpdateFromNetwork(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(Cellar.CELLAR.UPDATE_FROM_NETWORK, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>update_from_network IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByUpdateFromNetwork(Boolean... values) {
        return fetch(Cellar.CELLAR.UPDATE_FROM_NETWORK, values);
    }

    /**
     * Fetch records that have <code>contact_email BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfContactEmail(String lowerInclusive, String upperInclusive) {
        return fetchRange(Cellar.CELLAR.CONTACT_EMAIL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>contact_email IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByContactEmail(String... values) {
        return fetch(Cellar.CELLAR.CONTACT_EMAIL, values);
    }

    /**
     * Fetch records that have <code>private BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfPrivate(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(Cellar.CELLAR.PRIVATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>private IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByPrivate(Boolean... values) {
        return fetch(Cellar.CELLAR.PRIVATE, values);
    }

    /**
     * Fetch records that have <code>last_login BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfLastLogin(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Cellar.CELLAR.LAST_LOGIN, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>last_login IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByLastLogin(LocalDateTime... values) {
        return fetch(Cellar.CELLAR.LAST_LOGIN, values);
    }

    /**
     * Fetch records that have <code>last_login_ip BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfLastLoginIp(String lowerInclusive, String upperInclusive) {
        return fetchRange(Cellar.CELLAR.LAST_LOGIN_IP, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>last_login_ip IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByLastLoginIp(String... values) {
        return fetch(Cellar.CELLAR.LAST_LOGIN_IP, values);
    }

    /**
     * Fetch records that have <code>created_date BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfCreatedDate(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Cellar.CELLAR.CREATED_DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_date IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByCreatedDate(LocalDateTime... values) {
        return fetch(Cellar.CELLAR.CREATED_DATE, values);
    }

    /**
     * Fetch records that have <code>modified_date BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfModifiedDate(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Cellar.CELLAR.MODIFIED_DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modified_date IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByModifiedDate(LocalDateTime... values) {
        return fetch(Cellar.CELLAR.MODIFIED_DATE, values);
    }

    /**
     * Fetch records that have <code>twitter BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfTwitter(String lowerInclusive, String upperInclusive) {
        return fetchRange(Cellar.CELLAR.TWITTER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>twitter IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByTwitter(String... values) {
        return fetch(Cellar.CELLAR.TWITTER, values);
    }

    /**
     * Fetch records that have <code>reddit BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfReddit(String lowerInclusive, String upperInclusive) {
        return fetchRange(Cellar.CELLAR.REDDIT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>reddit IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByReddit(String... values) {
        return fetch(Cellar.CELLAR.REDDIT, values);
    }

    /**
     * Fetch records that have <code>beeradvocate BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfBeeradvocate(String lowerInclusive, String upperInclusive) {
        return fetchRange(Cellar.CELLAR.BEERADVOCATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>beeradvocate IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByBeeradvocate(String... values) {
        return fetch(Cellar.CELLAR.BEERADVOCATE, values);
    }

    /**
     * Fetch records that have <code>ratebeer BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfRatebeer(String lowerInclusive, String upperInclusive) {
        return fetchRange(Cellar.CELLAR.RATEBEER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>ratebeer IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByRatebeer(String... values) {
        return fetch(Cellar.CELLAR.RATEBEER, values);
    }

    /**
     * Fetch records that have <code>total_beers BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfTotalBeers(Short lowerInclusive, Short upperInclusive) {
        return fetchRange(Cellar.CELLAR.TOTAL_BEERS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>total_beers IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByTotalBeers(Short... values) {
        return fetch(Cellar.CELLAR.TOTAL_BEERS, values);
    }

    /**
     * Fetch records that have <code>unique_beers BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfUniqueBeers(Short lowerInclusive, Short upperInclusive) {
        return fetchRange(Cellar.CELLAR.UNIQUE_BEERS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>unique_beers IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByUniqueBeers(Short... values) {
        return fetch(Cellar.CELLAR.UNIQUE_BEERS, values);
    }

    /**
     * Fetch records that have <code>unique_breweries BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfUniqueBreweries(Short lowerInclusive, Short upperInclusive) {
        return fetchRange(Cellar.CELLAR.UNIQUE_BREWERIES, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>unique_breweries IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByUniqueBreweries(Short... values) {
        return fetch(Cellar.CELLAR.UNIQUE_BREWERIES, values);
    }

    /**
     * Fetch records that have <code>has_tradeable_beers BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfHasTradeableBeers(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(Cellar.CELLAR.HAS_TRADEABLE_BEERS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>has_tradeable_beers IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByHasTradeableBeers(Boolean... values) {
        return fetch(Cellar.CELLAR.HAS_TRADEABLE_BEERS, values);
    }

    /**
     * Fetch records that have <code>slug BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfSlug(String lowerInclusive, String upperInclusive) {
        return fetchRange(Cellar.CELLAR.SLUG, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>slug IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchBySlug(String... values) {
        return fetch(Cellar.CELLAR.SLUG, values);
    }

    /**
     * Fetch a unique record that has <code>slug = value</code>
     */
    public com.cellarhq.generated.tables.pojos.Cellar fetchOneBySlug(String value) {
        return fetchOne(Cellar.CELLAR.SLUG, value);
    }

    /**
     * Fetch records that have <code>role BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchRangeOfRole(String lowerInclusive, String upperInclusive) {
        return fetchRange(Cellar.CELLAR.ROLE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>role IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.Cellar> fetchByRole(String... values) {
        return fetch(Cellar.CELLAR.ROLE, values);
    }
}
