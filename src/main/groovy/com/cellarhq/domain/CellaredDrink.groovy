package com.cellarhq.domain

import com.cellarhq.ratpack.hibernate.Entity

import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(name = 'cellared_drink', indexes = [
        @Index(name = 'idx_cellared_drink_drink_by_date', columnList = 'drink_by_date')
])
class CellaredDrink extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = 'cellar_id', nullable = false, updatable = false)
    Cellar cellar

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = 'drink_id', nullable = false, updatable = false)
    Drink drink

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = 'style_id', nullable = false, updatable = false)
    Style style

    @Column(name = 'bottle_date', nullable = true)
    LocalDate bottleDate

    @Column(nullable = true)
    String size

    @Column(nullable = false)
    int quantity = 0

    @Column(nullable = true)
    String notes

    @Column(name = 'drink_by_date', nullable = true)
    LocalDateTime drinkByDate

    @SuppressWarnings('PropertyName')
    @Column(name = 'private', nullable = false)
    boolean _private = false

    boolean isPrivate() {
        return _private
    }
}
