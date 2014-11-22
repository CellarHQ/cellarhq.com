package com.cellarhq.functional.specs

import com.cellarhq.SpecFlags
import com.cellarhq.domain.CellaredDrink
import com.cellarhq.domain.Drink
import com.cellarhq.domain.EmailAccount
import com.cellarhq.domain.Organization
import com.cellarhq.functional.BaseFunctionalSpecification
import com.cellarhq.functional.CellarHqApplication
import com.cellarhq.functional.DatabaseTrait
import com.cellarhq.functional.LogInUserTrait
import com.cellarhq.functional.builders.CellaredDrinkBuilder
import com.cellarhq.functional.builders.DrinkBuilder
import com.cellarhq.functional.builders.OrganizationBuilder
import com.cellarhq.functional.pages.YourArchivePage
import com.cellarhq.functional.pages.YourCellarPage
import ratpack.test.ApplicationUnderTest
import ratpack.test.remote.RemoteControl
import spock.lang.IgnoreIf
import spock.lang.Shared
import spock.lang.Stepwise

import static com.cellarhq.generated.Tables.*

@Stepwise
@IgnoreIf({ SpecFlags.isTrue(SpecFlags.NO_FUNCTIONAL) })
class YourArchiveFunctionalSpec extends BaseFunctionalSpecification implements DatabaseTrait, LogInUserTrait  {
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

    def 'can click link from yourcellar to yourarchive'() {
        when: 'navigate from the yourcellar page to yourarchive page'
        YourCellarPage yourCellarPage = to YourCellarPage

        yourCellarPage.yourArchiveLink.click()


        then: 'at the yourarchive page and there is 1 item in the list'
        at YourArchivePage
        YourArchivePage archivePage = page
        archivePage.archtiveItems.size() == 1
    }


}
