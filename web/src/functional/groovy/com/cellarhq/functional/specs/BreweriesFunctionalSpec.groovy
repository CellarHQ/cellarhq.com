package com.cellarhq.functional.specs

import com.cellarhq.SpecFlags
import com.cellarhq.functional.BaseFunctionalSpecification
import com.cellarhq.functional.CellarHqApplication
import com.cellarhq.functional.LogInUserTrait
import com.cellarhq.functional.pages.breweries.AddBreweryPage
import com.cellarhq.functional.pages.breweries.BreweriesPage
import com.cellarhq.functional.pages.breweries.EditBreweryPage
import com.cellarhq.functional.pages.breweries.ShowBreweryPage
import groovy.sql.Sql
import org.h2.jdbc.JdbcSQLException
import ratpack.groovy.test.embed.GroovyEmbeddedApp
import ratpack.test.ApplicationUnderTest
import ratpack.test.embed.EmbeddedApp
import ratpack.test.remote.RemoteControl
import spock.lang.IgnoreIf
import spock.lang.Shared
import spock.lang.Stepwise

import javax.sql.DataSource

@Stepwise
@IgnoreIf({ SpecFlags.isTrue(SpecFlags.NO_FUNCTIONAL) })
class BreweriesFunctionalSpec extends BaseFunctionalSpecification implements LogInUserTrait {
    @Shared
    ApplicationUnderTest aut = new CellarHqApplication()

    @Shared
    RemoteControl remote = new RemoteControl(aut)

    def setupSpec() {
        browser.baseUrl = aut.getAddress().toString()
        anEmailAccountUser(remote, 'someone', 'test@example.com', 'password1')
        logInUser('test@example.com', 'password1')
    }


    def cleanupSpec() {
        cleanUpUsers(remote)
        remote.exec {
            try {
                Sql sql = new Sql(get(DataSource))
                sql.execute('delete from organization where 1=1')
                sql.close()
            } catch (JdbcSQLException e) {
                // I don't think this should make the test fail: Will also be changed moving to jOOQ.
                log.error(e.message)
            }
        }
    }

    def 'verify can get to an empty list page'() {
        when:
        to BreweriesPage

        then:
        at BreweriesPage
    }

    def 'can add a new brewery'() {
        when: 'Navigate to the add brewery page'
        AddBreweryPage addBreweryPage = to AddBreweryPage

        and: 'the form is filled it'
        addBreweryPage.fillForm()

        and: 'the form is submitted'
        addBreweryPage.submitForm()

        then: 'the show brewery page is displayed'
        noExceptionThrown()
        at ShowBreweryPage
    }

    def 'can edit a brewery'() {
        when: 'Navigate to the add brewery page'
        ShowBreweryPage showBreweryPage = to(ShowBreweryPage, 'name')

        and: ''
        showBreweryPage.editBreweryButton.click()
        EditBreweryPage editBreweryPage = at (EditBreweryPage)

        and:
        editBreweryPage.submitForm()

        then:
        noExceptionThrown()

        and:
        at ShowBreweryPage
    }
}
