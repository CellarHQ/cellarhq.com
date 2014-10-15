package com.cellarhq.functional.specs

import com.cellarhq.SpecFlags
import com.cellarhq.domain.EmailAccount
import com.cellarhq.functional.BaseFunctionalSpecification
import com.cellarhq.functional.CellarHqApplication
import com.cellarhq.functional.LogInUserTrait
import com.cellarhq.functional.pages.AddBreweryPage
import com.cellarhq.functional.pages.BreweriesPage
import com.cellarhq.functional.pages.ShowBreweryPage
import ratpack.test.ApplicationUnderTest
import ratpack.test.remote.RemoteControl
import spock.lang.IgnoreIf
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
@IgnoreIf({ SpecFlags.isTrue(SpecFlags.NO_FUNCTIONAL) })
class BreweriesFunctionalSpec extends BaseFunctionalSpecification implements LogInUserTrait {
    @Shared
    ApplicationUnderTest aut = new CellarHqApplication()

    @Shared
    RemoteControl remote = new RemoteControl(aut)

    def setupSpec() {
        browser.baseUrl = aut.getAddress().toString()
        EmailAccount emailAccount = anEmailAccountUser()
        logInUser(emailAccount)
    }

    def cleanupSpec() {
        cleanUpUsers()
    }

    def 'verify can get to an empty list page'() {
        when:
        to BreweriesPage

        then:
        at BreweriesPage
    }

    def 'can add a new brewery'() {
        when: 'Navigate to the add brewery page'
        AddBreweryPage page2 = to AddBreweryPage

        and:
        page2.fillForm()

        and:
        page2.submitForm()

        then:
        noExceptionThrown()

        and:
        at ShowBreweryPage
    }
}
