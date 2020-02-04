/*
 * This file is generated by jOOQ.
 */
package com.cellarhq.generated.tables.daos;


import com.cellarhq.generated.tables.PasswordChangeRequest;
import com.cellarhq.generated.tables.records.PasswordChangeRequestRecord;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

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
public class PasswordChangeRequestDao extends DAOImpl<PasswordChangeRequestRecord, com.cellarhq.generated.tables.pojos.PasswordChangeRequest, String> {

    /**
     * Create a new PasswordChangeRequestDao without any configuration
     */
    public PasswordChangeRequestDao() {
        super(PasswordChangeRequest.PASSWORD_CHANGE_REQUEST, com.cellarhq.generated.tables.pojos.PasswordChangeRequest.class);
    }

    /**
     * Create a new PasswordChangeRequestDao with an attached configuration
     */
    public PasswordChangeRequestDao(Configuration configuration) {
        super(PasswordChangeRequest.PASSWORD_CHANGE_REQUEST, com.cellarhq.generated.tables.pojos.PasswordChangeRequest.class, configuration);
    }

    @Override
    public String getId(com.cellarhq.generated.tables.pojos.PasswordChangeRequest object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.PasswordChangeRequest> fetchRangeOfId(String lowerInclusive, String upperInclusive) {
        return fetchRange(PasswordChangeRequest.PASSWORD_CHANGE_REQUEST.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.PasswordChangeRequest> fetchById(String... values) {
        return fetch(PasswordChangeRequest.PASSWORD_CHANGE_REQUEST.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.cellarhq.generated.tables.pojos.PasswordChangeRequest fetchOneById(String value) {
        return fetchOne(PasswordChangeRequest.PASSWORD_CHANGE_REQUEST.ID, value);
    }

    /**
     * Fetch records that have <code>account_email_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.PasswordChangeRequest> fetchRangeOfAccountEmailId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(PasswordChangeRequest.PASSWORD_CHANGE_REQUEST.ACCOUNT_EMAIL_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>account_email_id IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.PasswordChangeRequest> fetchByAccountEmailId(Long... values) {
        return fetch(PasswordChangeRequest.PASSWORD_CHANGE_REQUEST.ACCOUNT_EMAIL_ID, values);
    }

    /**
     * Fetch records that have <code>created_date BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.cellarhq.generated.tables.pojos.PasswordChangeRequest> fetchRangeOfCreatedDate(Timestamp lowerInclusive, Timestamp upperInclusive) {
        return fetchRange(PasswordChangeRequest.PASSWORD_CHANGE_REQUEST.CREATED_DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_date IN (values)</code>
     */
    public List<com.cellarhq.generated.tables.pojos.PasswordChangeRequest> fetchByCreatedDate(Timestamp... values) {
        return fetch(PasswordChangeRequest.PASSWORD_CHANGE_REQUEST.CREATED_DATE, values);
    }
}