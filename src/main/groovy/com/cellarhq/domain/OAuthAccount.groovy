package com.cellarhq.domain

import com.cellarhq.ratpack.hibernate.Entity

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.UniqueConstraint

@Entity(name = 'account_oauth', uniqueConstraints = [
        @UniqueConstraint(name = 'unq_oauth_client_username', columnNames = ['client', 'username'])
])
class OAuthAccount extends AbstractEntity {

    enum Client {
        TWITTER('TwitterClient')

        private final String value

        Client(String value) {
            this.value = value
        }

        String toString() {
            return value
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = 'cellar_id', nullable = false, updatable = false)
    Cellar cellar

    @Column(length = 20, nullable = false, updatable = false)
    @Enumerated(value = EnumType.STRING)
    Client client = Client.TWITTER

    @Column(name = 'username', nullable = false)
    String username
}
