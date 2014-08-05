package com.cellarhq.domain

import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.Version

/**
 * Base Entity from which most of the entities will extend.
 */
@MappedSuperclass
@CompileStatic
abstract class AbstractEntity {

    @Version
    Long version

    // TODO Upgrade to Java8 & avoid Joda?
    @Column(name = 'created_date', nullable = false)
    Date createdDate = new Date()

    // TODO Upgrade to Java8 & avoid Joda?
    @Column(name = 'modified_date', nullable = false)
    Date modifiedDate = new Date()
}
