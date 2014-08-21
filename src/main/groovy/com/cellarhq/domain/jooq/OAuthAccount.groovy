package com.cellarhq.domain.jooq

import com.cellarhq.domain.OAuthClient
import com.cellarhq.generated.tables.pojos.AccountOauth
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

import java.sql.Timestamp
import java.time.LocalDateTime

@CompileStatic
@InheritConstructors
class OAuthAccount extends AccountOauth {

    Cellar cellar

    OAuthAccount() {
        client = OAuthClient.TWITTER.toString()
        createdDate = Timestamp.valueOf(LocalDateTime.now())
        modifiedDate = createdDate
    }
}
