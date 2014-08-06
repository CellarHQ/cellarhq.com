package com.cellarhq.domain

import com.cellarhq.ratpack.hibernate.Entity

import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = 'account_email')
class EmailAccount extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = 'cellar_id', nullable = false, updatable = false)
    Cellar cellar

    @Column(nullable = false, unique = true)
    String email

    @Column(length = 64, nullable = false)
    String password

    @Column(length = 64, nullable = false)
    String salt
}
