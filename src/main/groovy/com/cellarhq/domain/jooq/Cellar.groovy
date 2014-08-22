package com.cellarhq.domain.jooq

import com.github.slugify.Slugify
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

import javax.persistence.Column
import javax.validation.constraints.Pattern

@CompileStatic
@InheritConstructors
class Cellar extends com.cellarhq.generated.tables.pojos.Cellar {
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
    @SuppressWarnings('ParameterName')
    void setPrivate(Boolean private_) {
        super.private = private_ ?: false
    }

}
