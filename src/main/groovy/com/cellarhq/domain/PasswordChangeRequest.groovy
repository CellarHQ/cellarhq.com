package com.cellarhq.domain

import com.cellarhq.ratpack.hibernate.Entity

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = 'password_change_request')
class PasswordChangeRequest {

    @Id
    String id

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = 'account_email_id', nullable = false, updatable = false)
    EmailAccount emailAccount

    @Column(name = 'created_date', nullable = false)
    Date createdDate = new Date()
}
