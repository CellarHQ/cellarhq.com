package com.cellarhq.domain

import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.Version
import java.time.LocalDateTime

/**
 * Base Entity from which most of the entities will extend.
 */
@MappedSuperclass
@CompileStatic
abstract class AbstractEntity {

    @Version
    Long version

    @Column(name = 'created_date', nullable = false)
    LocalDateTime createdDate = LocalDateTime.now()

    @Column(name = 'modified_date', nullable = false)
    LocalDateTime modifiedDate = LocalDateTime.now()
}
