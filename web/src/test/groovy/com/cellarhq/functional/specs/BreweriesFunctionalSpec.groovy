package com.cellarhq.functional.specs

import com.cellarhq.SpecFlags
import com.cellarhq.auth.SecurityModule
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.EmailAccount
import com.cellarhq.functional.CellarHqApplication
import com.cellarhq.functional.LogInUserTrait
import com.cellarhq.functional.pages.AddBreweryPage
import com.cellarhq.functional.pages.BreweriesPage
import com.cellarhq.functional.pages.LoginPage
import com.cellarhq.functional.pages.LogoutPage
import com.cellarhq.functional.pages.RegisterPage
import com.cellarhq.functional.pages.ShowBreweryPage
import com.cellarhq.functional.pages.YourCellarPage
import com.cellarhq.services.AccountService
import geb.spock.GebReportingSpec
import groovy.sql.Sql
import groovy.transform.CompileStatic
import org.h2.jdbc.JdbcSQLException
import org.pac4j.http.profile.HttpProfile
import ratpack.pac4j.internal.SessionConstants
import ratpack.session.store.SessionStorage
import ratpack.test.ApplicationUnderTest
import ratpack.test.remote.RemoteControl
import spock.lang.IgnoreIf
import spock.lang.Shared
import spock.lang.Stepwise

import javax.sql.DataSource

@Stepwise
@IgnoreIf({ SpecFlags.isTrue(SpecFlags.NO_FUNCTIONAL) })
class BreweriesFunctionalSpec extends GebReportingSpec implements LogInUserTrait {
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
