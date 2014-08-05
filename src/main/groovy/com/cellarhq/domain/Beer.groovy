package com.cellarhq.domain

import com.cellarhq.ratpack.hibernate.Entity

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity(name = 'beer')
class Beer extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = 'photo_id')
    Photo photo

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = 'brewery_id', nullable = false, updatable = false)
    Brewery brewery

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = 'style_id', nullable = false, updatable = false)
    Style style

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = 'glassware_id', nullable = false, updatable = false)
    Glassware glassware

    @Column(length = 100, name = 'url_name', nullable = false, unique = true)
    String urlName

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

    // TODO Enum?
    @Column(nullable = true)
    String availability

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = 'beer', fetch = FetchType.LAZY)
    Set<CellaredBeer> cellars = []
}
