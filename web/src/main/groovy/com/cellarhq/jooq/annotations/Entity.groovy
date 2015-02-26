package com.cellarhq.jooq.annotations

import groovy.transform.AnnotationCollector
import groovy.transform.CompileStatic

import javax.persistence.Table

@CompileStatic
@javax.persistence.Entity
@Table
@AnnotationCollector
@interface Entity {}