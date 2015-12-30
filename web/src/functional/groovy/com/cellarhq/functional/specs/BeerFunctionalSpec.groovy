package com.cellarhq.functional.specs

import com.cellarhq.SpecFlags
import com.cellarhq.api.services.OrganizationService
import com.cellarhq.domain.EmailAccount
import com.cellarhq.domain.Organization
import com.cellarhq.functional.BaseFunctionalSpecification
import com.cellarhq.functional.CellarHqApplication
import com.cellarhq.functional.LogInUserTrait
import com.cellarhq.functional.pages.beers.AddBeerPage
import com.cellarhq.functional.pages.breweries.ShowBreweryPage
import groovy.sql.Sql
import org.h2.jdbc.JdbcSQLException
import ratpack.rx.RxRatpack
import ratpack.test.ApplicationUnderTest
import ratpack.test.exec.ExecHarness
import ratpack.test.remote.RemoteControl
import spock.lang.Ignore
import spock.lang.IgnoreIf
import spock.lang.Shared
import spock.lang.Stepwise

import javax.sql.DataSource

@Stepwise
@IgnoreIf({ SpecFlags.isTrue(SpecFlags.NO_FUNCTIONAL) })
@Ignore
class BeerFunctionalSpec extends BaseFunctionalSpecification implements LogInUserTrait {

  @Shared
  ApplicationUnderTest aut = new CellarHqApplication()

  @Shared
  RemoteControl remote = new RemoteControl(aut)

  def setupSpec() {
    remote.exec {
      Organization org = new Organization(
        name: 'the business',
        slug: 'the-business'
      )
      ExecHarness.yieldSingle({ execution ->
        RxRatpack.asPromiseSingle(get(OrganizationService).save(org))
      }).complete
    }

    browser.baseUrl = aut.address.toString()
    EmailAccount emailAccount = anEmailAccountUser(remote, 'user', 'user@example.com', 'password1')
    logInUser(emailAccount.email, 'password1')
  }

  def cleanupSpec() {
    cleanUpUsers(remote)
    remote.exec {
      try {
        Sql sql = new Sql(get(DataSource))
        sql.execute('delete from drink where 1=1')
        sql.execute('delete from organization where 1=1')
        sql.close()
      } catch (JdbcSQLException e) {
      }
    }
  }

  def 'empty beer list'() {
    when:
    ShowBreweryPage page = to ShowBreweryPage, 'the-business'

    then:
    assert page.beerTable.find('tbody tr').size() == 0
  }

  def 'add new beer'() {
    when:
    AddBeerPage page = to AddBeerPage, 'the-business'

    and:
    page.fillForm(null)

    and:
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
