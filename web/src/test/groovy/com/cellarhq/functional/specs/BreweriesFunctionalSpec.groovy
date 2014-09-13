package com.cellarhq.functional.specs

import com.cellarhq.SpecFlags
import com.cellarhq.functional.pages.BreweriesPage
import geb.spock.GebReportingSpec
import groovy.sql.Sql
import groovy.util.logging.Slf4j
import org.h2.jdbc.JdbcSQLException
import ratpack.groovy.test.LocalScriptApplicationUnderTest
import ratpack.test.ApplicationUnderTest
import ratpack.test.remote.RemoteControl
import spock.lang.IgnoreIf
import spock.lang.Shared
import spock.lang.Stepwise

import javax.sql.DataSource

@Slf4j
@Stepwise
@IgnoreIf({ SpecFlags.isTrue(SpecFlags.NO_FUNCTIONAL) })
class BreweriesFunctionalSpec extends GebReportingSpec {

    @Shared
    ApplicationUnderTest aut = new LocalScriptApplicationUnderTest(
            'other.remoteControl.enabled': 'true',
            'other.hikari.dataSourceProperties.databaseName': 'cellarhq_testing')

    @Shared
    RemoteControl remote = new RemoteControl(aut)

    def setup() {
        browser.baseUrl = aut.address.toString()
    }

    def 'verify can get to an empty list page'() {

        when:
        BreweriesPage breweriesPage = to BreweriesPage

        then:
        at BreweriesPage
    }
}
