package com.cellarhq.domain

import com.cellarhq.annotations.Sanitize
import com.cellarhq.auth.Role
import com.cellarhq.generated.tables.records.CellarRecord
import com.github.slugify.Slugify
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotEmpty
import org.pac4j.oauth.profile.twitter.TwitterProfile

import javax.persistence.Column
import javax.validation.constraints.Pattern
import java.sql.Timestamp
import java.time.LocalDateTime

@Sanitize(recordType = CellarRecord, fields = [
        'displayName',
        'location',
        'website',
        'bio',
        'twitter',
        'reddit',
        'beeradvocate',
        'ratebeer'
])
@CompileStatic
@InheritConstructors
class Cellar extends com.cellarhq.generated.tables.pojos.Cellar {

    static Cellar makeFrom(TwitterProfile profile) {
        return new Cellar().with { Cellar self ->
            screenName = profile.username
            displayName = profile.displayName
            location = profile.location
            website = profile.profileUrl
            bio = profile.description
            lastLogin = Timestamp.valueOf(LocalDateTime.now())
            return self
        }
    }

    Photo photo

    Cellar() {
        setPrivate(false)
        updateFromNetwork = false
        createdDate = Timestamp.valueOf(LocalDateTime.now())
        modifiedDate = createdDate
        totalBeers = 0
        uniqueBeers = 0
        uniqueBreweries = 0
        hasTradeableBeers = false
        role = Role.MEMBER
    }

    @Override
    @NotEmpty
    @Length(min = 1, max = 64)
    @Column(name = 'display_name')
    String getDisplayName() {
        super.displayName
    }

    @Override
    @NotEmpty
    @Length(min = 1, max = 64)
    @Pattern(regexp = /[a-zA-Z0-9_-]{1,20}/)
    @Column(name = 'screen_name')
    String getScreenName() {
        return super.screenName
    }

    @Override
    void setUpdateFromNetwork(Boolean updateFromNetwork) {
        super.updateFromNetwork = updateFromNetwork ?: false
    }

    @Override
    void setScreenName(String screenName) {
        super.screenName = screenName
        slug = new Slugify().slugify(screenName)
    }

    @Override
    @SuppressWarnings('ParameterName')
    void setPrivate(Boolean private_) {
        super.private = private_ ?: false
    }

    void setRole(Role role) {
        super.role = role.toString()
    }

    boolean hasContactInfo() {
        twitter || reddit || beeradvocate
    }

    boolean getHasContactInfo() {
        return hasContactInfo()
    }

}
