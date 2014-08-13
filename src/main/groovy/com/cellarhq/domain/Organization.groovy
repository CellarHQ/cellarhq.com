package com.cellarhq.domain

import com.cellarhq.ratpack.hibernate.Entity
import com.github.slugify.Slugify

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.UniqueConstraint

@Entity(name = 'organization', uniqueConstraints = [
        @UniqueConstraint(name = 'unq_organization_slug', columnNames = ['slug'])
], indexes = [
        @Index(name = 'gist_idx_organization_name', columnList = 'name')
])
class Organization extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    OrganizationType type

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = 'photo_id')
    Photo photo

    @Column(name = 'slug', length = 100, nullable = false, unique = true)
    String slug

    @Column(length = 100, nullable = false)
    String name

    @Column(length = 2048, nullable = true)
    String description

    @Column(nullable = true)
    Date established

    @Column(length = 20, nullable = true)
    String phone

    @Column(length = 100, nullable = true)
    String website

    @Column(length = 100, nullable = true)
    String address

    @Column(length = 100, nullable = true)
    String address2

    @Column(nullable = true)
    String locality

    @Column(name = 'postal_code', length = 20, nullable = true)
    String postalCode

    @Column(nullable = true)
    String country

    @Column(nullable = false)
    boolean searchable = true

    @Column(name = 'brewery_db_id', length = 64, nullable = true)
    String breweryDbId

    @Column(name = 'brewery_db_last_updated', nullable = true)
    Date breweryDbLastUpdated

    @Column(nullable = false)
    boolean locked = false

    @Column(name = 'needs_moderation', nullable = false)
    boolean needsModeration = true

    @OneToMany(cascade = CascadeType.ALL, mappedBy = 'organization', fetch = FetchType.LAZY)
    Set<Drink> drinks = []

    void setSlug(String slug) {
        this.slug = new Slugify().slugify(slug)
    }
}
