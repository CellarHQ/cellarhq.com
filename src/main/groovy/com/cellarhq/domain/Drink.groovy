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
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.UniqueConstraint

import java.time.LocalDateTime

@Entity(name = 'drink', uniqueConstraints = [
        @UniqueConstraint(name = 'unq_drink_slug', columnNames = ['slug'])
], indexes = [
        @Index(name = 'idx_drink_brewery_db_id', columnList = 'brewery_db_id'),
        @Index(name = 'idx_drink_needs_moderation', columnList = 'needs_moderation')
])
class Drink extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    DrinkType type

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = 'photo_id')
    Photo photo

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = 'organization_id', nullable = false, updatable = false)
    Organization organization

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = 'style_id', nullable = false, updatable = false)
    Style style

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = 'glassware_id', nullable = false, updatable = false)
    Glassware glassware

    @Column(length = 100, name = 'slug', nullable = false, unique = true)
    String slug

    @Column(length = 100, nullable = false)
    String name

    @Column(length = 2048, nullable = true)
    String description

    @Column(nullable = true)
    Integer srm

    @Column(nullable = true)
    Integer ibu

    @Column(nullable = true)
    Float abv

    @Column(nullable = true)
    @Enumerated(value = EnumType.STRING)
    Availability availability

    @Column(nullable = false)
    boolean searchable = true

    @Column(name = 'brewery_db_id', length = 64, nullable = true)
    String breweryDbId

    @Column(name = 'brewery_db_last_updated', nullable = true)
    LocalDateTime breweryDbLastUpdated

    @Column(nullable = false)
    boolean locked = false

    @Column(name = 'needs_moderation', nullable = false)
    boolean needsModeration = true

    @OneToMany(cascade = CascadeType.ALL, mappedBy = 'drink', fetch = FetchType.LAZY)
    Set<CellaredDrink> cellars = []

    void setSlug(String slug) {
        this.slug = new Slugify().slugify(slug)
    }
}
