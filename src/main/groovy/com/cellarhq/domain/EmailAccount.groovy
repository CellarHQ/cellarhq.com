package com.cellarhq.domain

import com.cellarhq.ratpack.hibernate.Entity
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotEmpty

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Transient
import javax.persistence.UniqueConstraint

@Entity(name = 'account_email', uniqueConstraints = [
        @UniqueConstraint(name = 'unq_account_email_email', columnNames = ['email'])
])
class EmailAccount extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = 'cellar_id', nullable = false, updatable = false)
    Cellar cellar

    @Email
    @NotEmpty
    @Column(nullable = false, unique = true)
    String email

    @Length(min = 4)
    @NotEmpty
    @Column(length = 64, nullable = false)
    String password

    @Transient
    String passwordConfirm

    @OneToMany(cascade = CascadeType.ALL, mappedBy = 'emailAccount', fetch = FetchType.LAZY)
    Set<PasswordChangeRequest> passwordChangeRequests = []
}
