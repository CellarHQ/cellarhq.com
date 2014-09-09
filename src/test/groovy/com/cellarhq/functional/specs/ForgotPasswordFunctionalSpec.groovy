package com.cellarhq.functional.specs

import com.cellarhq.SpecFlags
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.EmailAccount
import com.cellarhq.functional.pages.BasePage
import com.cellarhq.functional.pages.ChangePasswordPage
import com.cellarhq.functional.pages.ForgotPasswordPage
import com.cellarhq.functional.pages.HomePage
import com.cellarhq.functional.pages.LoginPage
import com.cellarhq.functional.pages.YourCellarPage
import com.cellarhq.services.AccountService
import geb.spock.GebReportingSpec
import groovy.sql.Sql
import groovy.util.logging.Slf4j
import org.h2.jdbc.JdbcSQLException
import ratpack.groovy.test.LocalScriptApplicationUnderTest
import ratpack.test.ApplicationUnderTest
import ratpack.test.remote.RemoteControl
import spock.lang.IgnoreIf
import spock.lang.Shared
import spock.lang.Stepwise

import javax.sql.DataSource

@Slf4j
@Stepwise
@IgnoreIf({ SpecFlags.isTrue(SpecFlags.NO_FUNCTIONAL) })
class ForgotPasswordFunctionalSpec extends GebReportingSpec {

    @Shared
    ApplicationUnderTest aut = new LocalScriptApplicationUnderTest(
            'other.remoteControl.enabled': 'true',
            'other.hikari.dataSourceProperties.databaseName': 'cellarhq_testing')

    @Shared
    RemoteControl remote = new RemoteControl(aut)

    def setup() {
        browser.baseUrl = aut.address.toString()
    }

    def cleanupSpec() {
        remote.exec {
            try {
                Sql sql = new Sql(get(DataSource))
                sql.execute('delete from password_change_request where 1=1')
                sql.execute('delete from account_email where 1=1')
                sql.execute('delete from cellar_role where 1=1')
                sql.execute('delete from cellar where 1=1')
                sql.close()
            } catch (JdbcSQLException e) {
                // I don't think this should make the test fail: Will also be changed moving to jOOQ.
                log.error(e.message)
            }
        }
    }

    def 'verify no change requests exist'() {
        when:
        long requests = (long) remote.exec {
            Sql sql = new Sql(get(DataSource))
            long result = sql.firstRow('select count(1) as requests from password_change_request').requests
            sql.close()
            return result
        }

        then:
        requests == 0

        when: 'setup tests with user'
        remote.exec {
            Cellar cellar = new Cellar(screenName: 'someone', displayName: 'Someone')
            EmailAccount emailAccount = new EmailAccount(email: 'test@example.com', password: 'password1')
            emailAccount.cellar = cellar
            get(AccountService).create(emailAccount)
            return null
        }

        then:
        noExceptionThrown()
    }

    def 'submit forgot password page'() {
        when:
        ForgotPasswordPage forgotPasswordPage = to ForgotPasswordPage
        forgotPasswordPage.fillForm('test@example.com')
        forgotPasswordPage.submit()

        then:
        at ForgotPasswordPage
        ((BasePage) page).successMessages
    }

    def 'receive hash and submit change password page'() {
        when:
        String hash = remote.exec {
            Sql sql = new Sql(get(DataSource))
            String result = sql.firstRow('select id from password_change_request').id
            sql.close()
            return result
        }

        then: 'hash was saved into database'
        hash

        when:
        go("forgot-password/${hash}")

        then:
        ChangePasswordPage changePasswordPage = at ChangePasswordPage

        when:
        changePasswordPage.fillForm('newnewnew')
        changePasswordPage.submit()

        then:
        LoginPage loginPage = at LoginPage

        when:
        loginPage.fillForm('test@example.com', 'newnewnew')
        loginPage.submitForm()

        then:
        isAt(YourCellarPage) || isAt(HomePage)
        ((BasePage) page).authenticatedMenu
    }
}
