package com.cellarhq.domain

import com.cellarhq.auth.Role
import com.cellarhq.ratpack.hibernate.Entity
import com.github.slugify.Slugify

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.UniqueConstraint
import javax.validation.constraints.NotNull

import java.time.LocalDateTime

@Entity(name = 'cellar', uniqueConstraints = [
        @UniqueConstraint(name = 'unq_cellar_screen_name', columnNames = ['screen_name'])
])
class Cellar extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = 'photo_id')
    Photo photo

    @NotNull
    @Column(name = 'screen_name', nullable = false, updatable = false)
    String screenName

    @Column(length = 60, nullable = true)
    String displayName

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

    @Column(name = 'last_login', nullable = true)
    LocalDateTime lastLogin

    // TODO Ratpack doesn't expose the remote IP yet.
    @Column(name = 'last_login_ip', nullable = true)
    String lastLoginIp

    @OneToMany(cascade = CascadeType.ALL, mappedBy = 'cellar', fetch = FetchType.LAZY)
    Set<CellarRole> roles = []

    @OneToMany(cascade = CascadeType.ALL, mappedBy = 'cellar', fetch = FetchType.LAZY)
    Set<Activity> activities = []

    @OneToOne(optional = true, mappedBy = 'cellar', cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    OAuthAccount openIdAccount

    @OneToMany(cascade = CascadeType.ALL, mappedBy = 'cellar', fetch = FetchType.LAZY)
    Set<EmailAccount> emailAccounts  = []

    @OneToMany(cascade = CascadeType.ALL, mappedBy = 'cellar', fetch = FetchType.LAZY)
    Set<CellaredDrink> drinks = []

    boolean isPrivate() {
        return _private
    }

    void addRole(Role role) {
        roles << new CellarRole(cellar: this, role: role)
    }

    void setScreenName(String screenName) {
        this.screenName = new Slugify().slugify(screenName)
    }
}
