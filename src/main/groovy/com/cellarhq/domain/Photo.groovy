package com.cellarhq.domain

import com.cellarhq.ratpack.hibernate.Entity

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity(name = 'photo')
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
    Beer beer

    @OneToOne(optional = true, mappedBy = 'photo')
    Brewery brewery

    @OneToOne(optional = true, mappedBy = 'photo')
    Cellar cellar
}
