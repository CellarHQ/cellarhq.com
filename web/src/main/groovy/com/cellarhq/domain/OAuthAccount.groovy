package com.cellarhq.domain

import com.cellarhq.generated.tables.pojos.AccountOauth
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

import java.sql.Timestamp
import java.time.LocalDateTime

@CompileStatic
@InheritConstructors
class OAuthAccount extends AccountOauth implements Account {

    Cellar cellar

    OAuthAccount() {
        client = OAuthClient.TWITTER.toString()
        createdDate = Timestamp.valueOf(LocalDateTime.now())
        modifiedDate = createdDate
    }
}
