package com.cellarhq.functional.specs

import com.cellarhq.SpecFlags
import com.cellarhq.functional.CellarHqApplication
import com.cellarhq.functional.pages.BreweriesPage
import geb.spock.GebReportingSpec
import ratpack.test.ApplicationUnderTest
import spock.lang.IgnoreIf
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
@IgnoreIf({ SpecFlags.isTrue(SpecFlags.NO_FUNCTIONAL) })
class BreweriesFunctionalSpec extends GebReportingSpec {

    @Shared
    ApplicationUnderTest aut = new CellarHqApplication()

    def setup() {
        browser.baseUrl = aut.getAddress().toString()
    }

    def 'verify can get to an empty list page'() {

        when:
        to BreweriesPage

        then:
        at BreweriesPage
    }
}
