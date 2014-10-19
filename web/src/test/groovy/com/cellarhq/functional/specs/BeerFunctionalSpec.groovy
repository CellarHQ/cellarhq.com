package com.cellarhq.functional.specs

import com.cellarhq.SpecFlags
import com.cellarhq.domain.EmailAccount
import com.cellarhq.domain.Organization
import com.cellarhq.functional.BaseFunctionalSpecification
import com.cellarhq.functional.CellarHqApplication
import com.cellarhq.functional.LogInUserTrait
import com.cellarhq.functional.pages.beers.AddBeerPage
import com.cellarhq.functional.pages.breweries.ShowBreweryPage
import com.cellarhq.services.OrganizationService
import groovy.sql.Sql
import org.h2.jdbc.JdbcSQLException
import ratpack.test.ApplicationUnderTest
import ratpack.test.remote.RemoteControl
import spock.lang.IgnoreIf
import spock.lang.Shared
import spock.lang.Stepwise

import javax.sql.DataSource

@Stepwise
@IgnoreIf({ SpecFlags.isTrue(SpecFlags.NO_FUNCTIONAL) })
class BeerFunctionalSpec extends BaseFunctionalSpecification implements LogInUserTrait {

    @Shared
    ApplicationUnderTest aut = new CellarHqApplication()

    @Shared
    RemoteControl remote = new RemoteControl(aut)

    def setupSpec() {
        browser.baseUrl = aut.address.toString()
        EmailAccount emailAccount = anEmailAccountUser()
        logInUser(emailAccount)

        remote.exec {
            Organization org = new Organization(
                    name: 'the business',
                    slug: 'the-business'
            )
            get(OrganizationService).save(org)
        }
    }

    def cleanupSpec() {
        cleanUpUsers()
        remote.exec {
            try {
                Sql sql = new Sql(get(DataSource))
                sql.execute('delete from drink where 1=1')
                sql.execute('delete from organization where = 1=1')
                sql.close()
            } catch (JdbcSQLException e) {
            }
        }
    }

    def 'empty beer list'() {
        when:
        ShowBreweryPage page = to ShowBreweryPage

        then:
        assert page.beerTable.find('tbody tr').size() == 0
    }

    def 'add new beer'() {
        when:
        AddBeerPage page = to AddBeerPage, 'the-business'
        page.fillForm(null)
        page.submitForm()

        then:
        at ShowBreweryPage
    }

//    def 'beer shows on brewery page'() {
//
//    }

//    def 'can edit existing beer'() {
//
//    }

//    def 'cannot edit uneditable page'() {
//
//    }
}
