package com.cellarhq.functional.specs

import com.cellarhq.SpecFlags
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.EmailAccount
import com.cellarhq.domain.OAuthAccount
import com.cellarhq.functional.BaseFunctionalSpecification
import com.cellarhq.functional.CellarHqApplication
import com.cellarhq.functional.pages.LoginPage
import com.cellarhq.functional.pages.LogoutPage
import com.cellarhq.functional.pages.YourCellarPage
import com.cellarhq.functional.pages.thirdparty.TwitterAuthorizePage
import com.cellarhq.functional.pages.thirdparty.TwitterOAuthPage
import com.cellarhq.services.AccountService
import groovy.sql.Sql
import org.h2.jdbc.JdbcSQLException
import ratpack.test.ApplicationUnderTest
import ratpack.test.remote.RemoteControl
import spock.lang.IgnoreIf
import spock.lang.Shared
import spock.lang.Stepwise

import javax.sql.DataSource

@Stepwise
@IgnoreIf({ SpecFlags.isTrue(SpecFlags.NO_FUNCTIONAL) || SpecFlags.isTrue(SpecFlags.NO_INTERNET) })
class CombinedAccountFunctionalSpec extends BaseFunctionalSpecification {

    @Shared
    ApplicationUnderTest aut = new CellarHqApplication()

    @Shared
    RemoteControl remote = new RemoteControl(aut)

    def setup() {
        browser.baseUrl = aut.address.toString()
    }

    def setupSpec() {
        remote.exec {
            Cellar cellar = new Cellar(screenName: 'combined', displayName: 'Combined')
            EmailAccount emailAccount = new EmailAccount(email: 'test-combined@example.com', password: 'password1')
            emailAccount.cellar = cellar
            emailAccount = get(AccountService).create(emailAccount, null)
            get(AccountService).attachOAuthAccount(new OAuthAccount(username: 'cellarhqtesting'), emailAccount.cellar)
        }
    }

    def cleanupSpec() {
        remote.exec {
            try {
                Sql sql = new Sql(get(DataSource))
                sql.execute('delete from account_email where 1=1')
                sql.execute('delete from account_oauth where 1=1')
                sql.execute('delete from cellar_role where 1=1')
                sql.execute('delete from cellar where 1=1')
                sql.close()
            } catch (JdbcSQLException e) {
                // I don't think this should make the test fail: Will also be changed moving to jOOQ.
                log.error(e.message)
            }
        }
    }

    def 'verify email and oauth accounts exist'() {
        when:
        long emailAccounts = (long) remote.exec {
            Sql sql = new Sql(get(DataSource))
            long result = sql.firstRow('select count(1) as accounts from account_email').accounts
            sql.close()
            return result
        }

        then:
        emailAccounts == 1

        when:
        long oauthAccounts = (long) remote.exec {
            Sql sql = new Sql(get(DataSource))
            long result = sql.firstRow('select count(1) as accounts from account_oauth').accounts
            sql.close()
            return result
        }

        then:
        oauthAccounts == 1
    }

    def 'user can authenticate with email'() {
        given:
        to LogoutPage

        when:
        LoginPage page = to LoginPage
        page.fillForm('test-combined@example.com', 'password1')
        page.submitForm()

        then:
        noExceptionThrown()
        page.authenticatedMenu
        at YourCellarPage
    }

    def 'user can authenticate with twitter'() {
        when:
        to LogoutPage
        LoginPage page = to LoginPage

        then:
        at LoginPage

        when:
        page.twitterLoginLink.click()

        if (isAt(TwitterOAuthPage)) {
            ((TwitterOAuthPage) browser.page).login()
        }

        if (isAt(TwitterAuthorizePage)) {
            ((TwitterAuthorizePage) browser.page).authorize()
        }

        then:
        waitFor 30, { at YourCellarPage }

        when:
        YourCellarPage yourCellarPage = to YourCellarPage

        then:
        yourCellarPage.authenticatedMenu
    }
}
