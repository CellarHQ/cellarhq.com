package com.cellarhq.domain

import com.cellarhq.ratpack.hibernate.Entity

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity(name = 'account')
class Cellar extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = 'photo_id')
    Photo photo

    @Column(name = 'screen_name', nullable = false, updatable = false)
    String screenName

    @Column(length = 60, nullable = true)
    String name

    @Column(length = 100, nullable = true)
    String location

    @Column(length = 100, nullable = true)
    String website

    @Column(length = 1000, nullable = true)
    String bio

    @Column(name = 'update_from_network', nullable = false)
    boolean updateFromNetwork = false

    @Column(name = 'contact_email', nullable = true)
    String contactEmail

    @SuppressWarnings('PropertyName')
    @Column(nullable = false)
    boolean _private = false

    // TODO Java8 / Joda?
    @Column(name = 'last_login', nullable = true)
    Date lastLogin

    @Column(name = 'last_login_ip', nullable = true)
    String lastLoginIp

    @OneToMany(cascade = CascadeType.ALL, mappedBy = 'cellar', fetch = FetchType.LAZY)
    Set<CellarRole> roles = []

    @OneToMany(cascade = CascadeType.ALL, mappedBy = 'cellar', fetch = FetchType.LAZY)
    Set<Activity> activities = []

    @OneToMany(cascade = CascadeType.ALL, mappedBy = 'cellar', fetch = FetchType.LAZY)
    Set<OpenIdAccount> openIdAccounts = []

    @OneToMany(cascade = CascadeType.ALL, mappedBy = 'cellar', fetch = FetchType.LAZY)
    Set<EmailAccount> emailAccounts  = []

    @OneToMany(cascade = CascadeType.ALL, mappedBy = 'cellar', fetch = FetchType.LAZY)
    Set<CellaredDrink> drinks = []

    boolean isPrivate() {
        return _private
    }
}
