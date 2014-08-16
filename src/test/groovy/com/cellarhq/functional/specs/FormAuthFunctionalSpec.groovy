package com.cellarhq.functional.specs

import com.cellarhq.SpecFlags
import com.cellarhq.functional.pages.HomePage
import com.cellarhq.functional.pages.LoginPage
import com.cellarhq.functional.pages.LogoutPage
import com.cellarhq.functional.pages.RegisterPage
import com.cellarhq.functional.pages.YourCellarPage
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
import spock.lang.Unroll

import javax.sql.DataSource

@Slf4j
@Stepwise
@IgnoreIf({ SpecFlags.isTrue(SpecFlags.NO_FUNCTIONAL) })
class FormAuthFunctionalSpec extends GebReportingSpec {

    @Shared
    ApplicationUnderTest aut = new LocalScriptApplicationUnderTest('other.remoteControl.enabled': 'true')

    @Shared
    RemoteControl remote = new RemoteControl(aut)

    def setup() {
        browser.baseUrl = aut.address.toString()
    }

    def cleanupSpec() {
        remote.exec {
            try {
                Sql sql = new Sql(get(DataSource))
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

    def 'verify no email accounts exist'() {
        when:
        long emailAccounts = (long) remote.exec {
            Sql sql = new Sql(get(DataSource))
            long result = sql.firstRow('select count(1) as accounts from account_email').accounts
            sql.close()
            return result
        }

        then:
        emailAccounts == 0
    }

    @Unroll("submitting with '#email' and '#password2' should #description")
    def 'submit registration page'() {
        when:
        RegisterPage page = to RegisterPage

        then:
        page.twitterLoginLink
        page.fillForm(email, screenName, password, password2)

        when:
        page.submitForm()

        then:
        noExceptionThrown()
        if (shouldRegister) {
            at YourCellarPage
        } else {
            at RegisterPage
            page.errorMessages
        }

        where:
        screenName | email               | password    | password2   | shouldRegister
        'test'     | 'test@cellarhq.com' | 'password1' | 'invalid'   | false
        'test'     | 'test@cellarhq.com' | 'password1' | 'password1' | true

        description = shouldRegister ? 'login successfully' : 'show validation errors'
    }

    def 'logout takes user back to home page'() {
        when:
        to LogoutPage

        then:
        HomePage page = at HomePage
        page.unauthenticatedMenu
    }

    @Unroll("submitting with email '#email' and password '#password' user should #description")
    def 'submit login page'() {
        given:
        to LogoutPage

        when:
        LoginPage page = to LoginPage
        page.fillForm(email, password)
        page.submitForm()

        then:
        noExceptionThrown()

        // TODO: Redirection is a little funky right now. Feel it's good enough, though.
        if (shouldLogin) {
            page.authenticatedMenu
            at HomePage
        } else {
            assert isAt(LoginPage) || isAt(HomePage)
            page.errorMessages
            page.unauthenticatedMenu
        }

        where:
        email               | password    | shouldLogin
        'invalid'           | 'invalid'   | false
        'test@cellarhq.com' | 'invalid'   | false
        'test@cellarhq.com' | 'password1' | true

        description = shouldLogin ? 'login successfully' : 'fail login'
    }
}
