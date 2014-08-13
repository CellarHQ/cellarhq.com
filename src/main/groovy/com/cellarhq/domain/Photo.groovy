package com.cellarhq.domain

import com.cellarhq.ratpack.hibernate.Entity

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.UniqueConstraint

@Entity(name = 'photo', uniqueConstraints = [
        @UniqueConstraint(name = 'unq_photo_original_url', columnNames = ['original_url'])
])
class Photo extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column(nullable = false)
    String description

    @Column(name = 'original_url', nullable = true)
    String originalUrl

    @Column(name = 'thumb_url', nullable = true)
    String thumbUrl

    @Column(name = 'thumb_width', nullable = true)
    int thumbWidth = 0

    @Column(name = 'thumb_height', nullable = true)
    int thumbHeight = 0

    @Column(name = 'large_url', nullable = true)
    String largeUrl

    @Column(name = 'large_width', nullable = true)
    int largeWidth = 0

    @Column(name = 'large_height', nullable = true)
    int largeHeight = 0

    @OneToOne(optional = true, mappedBy = 'photo')
    Drink drink

    @OneToOne(optional = true, mappedBy = 'photo')
    Organization organization

    @OneToOne(optional = true, mappedBy = 'photo')
    Cellar cellar
}
