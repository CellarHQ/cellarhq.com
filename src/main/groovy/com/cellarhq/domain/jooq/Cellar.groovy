package com.cellarhq.domain.jooq

import com.github.slugify.Slugify
import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.validation.constraints.Pattern
import java.sql.Timestamp

@CompileStatic
@SuppressWarnings(['LineLength', 'ParameterName'])
class Cellar extends com.cellarhq.generated.tables.pojos.Cellar {

    Cellar(Long id, Integer version, Long photoId, String screenName, String displayName, String location, String website, String bio, Boolean updateFromNetwork, String contactEmail, Boolean private_, Timestamp lastLogin, Object lastLoginIp, Timestamp createdDate, Timestamp modifiedDate) {
        super(id, version, photoId, screenName, displayName, location, website, bio, updateFromNetwork, contactEmail, private_, lastLogin, lastLoginIp, createdDate, modifiedDate)
    }

    Cellar() {
    }

    @Override
    @Pattern(regexp = /[a-zA-Z0-9_-]{1,20}/)
    @Column(name = 'screen_name')
    String getScreenName() {
        return super.screenName
    }

    @Override
    void setScreenName(String screenName) {
        super.screenName = new Slugify().slugify(screenName)
    }

    @Override
    void setUpdateFromNetwork(Boolean updateFromNetwork) {
        super.updateFromNetwork = updateFromNetwork ?: false
    }

    @Override
    void setPrivate(Boolean private_) {
        super.private = private_ ?: false
    }

}
