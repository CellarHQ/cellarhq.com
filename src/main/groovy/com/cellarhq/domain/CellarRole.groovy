package com.cellarhq.domain

import com.cellarhq.auth.Role
import com.cellarhq.ratpack.hibernate.Entity

import javax.persistence.Column
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Version

@Entity(name = 'cellar_role')
class CellarRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Version
    Long version

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = 'cellar_id', nullable = false)
    Cellar cellar

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    Role role
}
