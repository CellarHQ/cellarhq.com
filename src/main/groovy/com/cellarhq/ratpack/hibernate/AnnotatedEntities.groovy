package com.cellarhq.ratpack.hibernate

import com.google.common.collect.ImmutableList
import groovy.transform.CompileStatic

/**
 * Simple ImmutableList wrapper so the list of annotated hibernate entities can be passed around via DI.
 */
@CompileStatic
class AnnotatedEntities {

    ImmutableList<Class<?>> entities

    AnnotatedEntities(ImmutableList<Class<?>> entities) {
        this.entities = entities
    }
}
