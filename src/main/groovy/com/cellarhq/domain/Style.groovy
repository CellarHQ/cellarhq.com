package com.cellarhq.domain

import com.cellarhq.ratpack.hibernate.Entity

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import java.time.LocalDateTime

@Entity(name = 'style', indexes = [
        @Index(name = 'idx_style_brewery_db_id', columnList = 'brewery_db_id')
])
class Style extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = 'category_id', nullable = false, updatable = false)
    DrinkCategory category

    @Column(length = 100, nullable = false)
    String name

    @Column(length = 2048, nullable = true)
    String description

    @Column(nullable = false)
    boolean searchable = true

    @Column(name = 'brewery_db_id', nullable = true)
    String breweryDbId

    @Column(name = 'brewery_db_last_updated', nullable = true)
    LocalDateTime breweryDbLastUpdated

    @OneToMany(cascade = CascadeType.ALL, mappedBy = 'style', fetch = FetchType.LAZY)
    Set<Drink> drinks = []

    @OneToMany(cascade = CascadeType.ALL, mappedBy = 'style', fetch = FetchType.LAZY)
    Set<CellaredDrink> cellars = []
}
