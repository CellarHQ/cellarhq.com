package com.cellarhq.functional.specs

import com.cellarhq.domain.Drink
import com.cellarhq.domain.EmailAccount
import com.cellarhq.domain.Organization
import com.cellarhq.functional.BaseFunctionalSpecification
import com.cellarhq.functional.CellarHqApplication
import com.cellarhq.functional.LogInUserTrait
import com.cellarhq.functional.builders.CellaredDrinkBuilder
import com.cellarhq.functional.builders.DrinkBuilder
import com.cellarhq.functional.builders.OrganizationBuilder
import com.cellarhq.functional.pages.YourCellarPage
import ratpack.test.ApplicationUnderTest
import ratpack.test.remote.RemoteControl
import spock.lang.Shared

import static com.cellarhq.generated.Tables.ACCOUNT_EMAIL
import static com.cellarhq.generated.Tables.CELLAR
import static com.cellarhq.generated.Tables.CELLARED_DRINK
import static com.cellarhq.generated.Tables.DRINK
import static com.cellarhq.generated.Tables.ORGANIZATION

/**
 * Tests exporting a cellar into a CSV
 */
class ExportCellarFunctionalSpec extends BaseFunctionalSpecification implements LogInUserTrait {
  @Shared
  ApplicationUnderTest aut = new CellarHqApplication()

  @Shared
  RemoteControl remote = new RemoteControl(aut)

  static final EXPECTED_CSV =
    """Brewery,Beer,Style,size,quantity,num_tradeable,date_acquired,bottle_date,drink_by_date,bin_identifier,notes
brewery1,Beer 1,"","",1,"","","","","",""
brewery2,Beer 2,"","",2,"","","","","",""
"""

  def setupSpec() {
    browser.baseUrl = aut.getAddress().toString()
    EmailAccount account = anEmailAccountUser(remote, 'someone', 'test@example.com', 'password1')
    Organization organization = new OrganizationBuilder(name: 'brewery1').build(create)
    Organization organization2 = new OrganizationBuilder(name: 'brewery2').build(create)
    Drink drink = new DrinkBuilder(name: 'Beer 1').withOrganization(organization).build(create)
    Drink drink2 = new DrinkBuilder(name: 'Beer 2').withOrganization(organization2).build(create)
    new CellaredDrinkBuilder(quantity: 1).withCellar(account.cellar).withDrink(drink).build(create)
    new CellaredDrinkBuilder(quantity: 2).withCellar(account.cellar).withDrink(drink2).build(create)

    logInUser('test@example.com', 'password1')
  }

  def cleanupSpec() {
    create.delete(CELLARED_DRINK).execute()
    create.delete(ACCOUNT_EMAIL).execute()
    create.delete(CELLAR).execute()
    create.delete(DRINK).execute()
    create.delete(ORGANIZATION).execute()
  }

  def 'can click link from yourcellar to yourarchive'() {
    when: 'navigate from the yourcellar page to yourarchive page'
    YourCellarPage yourCellarPage = to YourCellarPage

    HttpURLConnection urlConnection = download(yourCellarPage.exportLink.attr('href'))
    urlConnection.connect()
    InputStreamReader isr = new InputStreamReader((InputStream) urlConnection.getContent())
    String csv = isr.text


    then: 'at the yourarchive page and there is 1 item in the list'
    csv == EXPECTED_CSV
  }


}
