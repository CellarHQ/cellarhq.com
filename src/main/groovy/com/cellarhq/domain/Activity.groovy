package com.cellarhq.domain

import com.cellarhq.ratpack.hibernate.Entity

import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = 'cellar_id', nullable = false)
    Cellar cellar

    @Column(nullable = false)
    String subject

    @Column(nullable = false)
    String action

    @SuppressWarnings('PropertyName')
    @Column(name = 'private', nullable = false)
    boolean _private = false

    // TODO JSON usertype
    @Column(nullable = true)
    String data

    // TODO Upgrade to Java8 & avoid Joda?
    @Column(nullable = false)
    Date createdDate

    boolean isPrivate() {
        return _private
    }
}
