package com.cellarhq.functional.specs

import com.cellarhq.SpecFlags
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.EmailAccount
import com.cellarhq.functional.BaseFunctionalSpecification
import com.cellarhq.functional.CellarHqApplication
import com.cellarhq.functional.pages.*
import com.cellarhq.services.AccountService
import groovy.sql.Sql
import groovy.util.logging.Slf4j
import org.h2.jdbc.JdbcSQLException
import ratpack.test.ApplicationUnderTest
import ratpack.test.remote.RemoteControl
import spock.lang.IgnoreIf
import spock.lang.Shared
import spock.lang.Stepwise

import javax.sql.DataSource

@Slf4j
@Stepwise
@IgnoreIf({ SpecFlags.isTrue(SpecFlags.NO_FUNCTIONAL) })
class ForgotPasswordFunctionalSpec extends BaseFunctionalSpecification {

    @Shared
    ApplicationUnderTest aut = new CellarHqApplication()

    @Shared
    RemoteControl remote = new RemoteControl(aut)

    def setup() {
        browser.baseUrl = aut.address.toString()
    }

    def cleanupSpec() {
        remote.exec {
            try {
                println get(DataSource).properties
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
            get(AccountService).create(emailAccount, null)
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
        isAt(YourCellarPage)
        ((BasePage) page).authenticatedMenu
    }
}
