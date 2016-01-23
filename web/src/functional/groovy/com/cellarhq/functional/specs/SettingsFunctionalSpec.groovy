package com.cellarhq.functional.specs

import com.cellarhq.SpecFlags
import com.cellarhq.domain.Drink
import com.cellarhq.domain.EmailAccount
import com.cellarhq.domain.Organization
import com.cellarhq.functional.BaseFunctionalSpecification
import com.cellarhq.functional.CellarHqApplication
import com.cellarhq.functional.LogInUserTrait
import com.cellarhq.functional.builders.CellaredDrinkBuilder
import com.cellarhq.functional.builders.DrinkBuilder
import com.cellarhq.functional.builders.OrganizationBuilder
import com.cellarhq.functional.pages.RegisterPage
import com.cellarhq.functional.pages.SettingsPage
import com.cellarhq.functional.pages.YourCellarPage
import ratpack.test.ApplicationUnderTest
import ratpack.test.remote.RemoteControl
import spock.lang.IgnoreIf
import spock.lang.Shared
import spock.lang.Stepwise

import static com.cellarhq.generated.Tables.ACCOUNT_EMAIL
import static com.cellarhq.generated.Tables.CELLAR
import static com.cellarhq.generated.Tables.CELLARED_DRINK
import static com.cellarhq.generated.Tables.DRINK
import static com.cellarhq.generated.Tables.ORGANIZATION

@Stepwise
@IgnoreIf({ SpecFlags.isTrue(SpecFlags.NO_FUNCTIONAL) })
class SettingsFunctionalSpec extends BaseFunctionalSpecification implements LogInUserTrait {
  @Shared
  ApplicationUnderTest aut = new CellarHqApplication()

  @Shared
  RemoteControl remote = new RemoteControl(aut)

  def setupSpec() {
    browser.baseUrl = aut.getAddress().toString()
    EmailAccount account = anEmailAccountUser(remote, 'someone', 'test@example.com', 'password1')
    Organization organization = new OrganizationBuilder().build(create)
    Drink drink = new DrinkBuilder().withOrganization(organization).build(create)
    new CellaredDrinkBuilder().withCellar(account.cellar).withDrink(drink).build(create)
    new CellaredDrinkBuilder(quantity: 2).withCellar(account.cellar).withDrink(drink).build(create)

    logInUser('test@example.com', 'password1')
  }

  def cleanupSpec() {
    create.delete(CELLARED_DRINK).execute()
    create.delete(ACCOUNT_EMAIL).execute()
    create.delete(CELLAR).execute()
    create.delete(DRINK).execute()
    create.delete(ORGANIZATION).execute()
  }

  def 'can change settings'() {
    when: 'navigate to settings page'
    SettingsPage settingsPage = to SettingsPage

    and: 'fill in the form'
    settingsPage.fillForm('New Display Name',
      'New Location',
      'http://www.localhost.com',
      'bio bio bio',
      'test@test.com',
      '@kyleboon',
      'redditusername',
      'http://www.localhost.com',
      'http://www.localhost.com',
      true)

    and: 'submit the form'
    settingsPage.updateSettings()

    then: 'at the settings page with the new settings filled in'
    SettingsPage updatedPage = at SettingsPage

    updatedPage.successMessages.displayed
    updatedPage.settingsForm.displayName == "New Display Name"
  }

  def 'can empty cellar'() {
    when: 'navigate to settings page'
    SettingsPage settingsPage = to SettingsPage

    and: "click the empty cellar paage"
    settingsPage.emptyCellar()

    then: 'at the your cellar page and there is 0 items in the list'
    at YourCellarPage

    YourCellarPage cellarPage = page
    cellarPage.cellarItems.size() == 0
  }

  def 'can delete the account'() {
    when: 'navigate to settings page'
    SettingsPage settingsPage = to SettingsPage

    and: "click the delete account button"
    settingsPage.deleteAccount()

    then: 'at the your cellar page and there is 0 items in the list'
    at RegisterPage
  }


}
