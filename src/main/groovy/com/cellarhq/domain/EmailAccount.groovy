package com.cellarhq.domain

import com.cellarhq.ratpack.hibernate.Entity
import org.hibernate.validator.constraints.Email

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Transient
import javax.validation.constraints.NotNull

@Entity(name = 'account_email')
class EmailAccount extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = 'cellar_id', nullable = false, updatable = false)
    Cellar cellar

    @Email
    @NotNull
    @Column(nullable = false, unique = true)
    String email

    @NotNull
    @Column(length = 64, nullable = false)
    String password

    // TODO Add cross-field validation.
    @Transient
    String passwordConfirm
}
