package com.cellarhq.ratpack.hibernate

import groovy.transform.AnnotationCollector
import groovy.transform.CompileStatic

import javax.persistence.Table

@CompileStatic
@javax.persistence.Entity
@Table
@AnnotationCollector
@interface Entity {}
