package com.cellarhq.entities

import com.cellarhq.hibernate.Entity

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = 'account')
class Cellar {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id
}
