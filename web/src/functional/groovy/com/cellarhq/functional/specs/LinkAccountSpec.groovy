package com.cellarhq.functional.specs

import com.amazonaws.services.cloudsearch.model.DescribeStemmingOptionsRequest
import com.cellarhq.SpecFlags
import com.cellarhq.functional.BaseFunctionalSpecification
import com.cellarhq.functional.CellarHqApplication
import com.cellarhq.functional.LogInUserTrait
import com.cellarhq.functional.pages.LinkEmailAccountPage
import com.cellarhq.functional.pages.LinkEmailAccountPasswordPage
import com.cellarhq.functional.pages.LinkTwitterAccountPage
import com.cellarhq.functional.pages.SettingsPage
import com.cellarhq.functional.pages.YourCellarPage
import groovy.sql.Sql
import ratpack.test.ApplicationUnderTest
import ratpack.test.remote.RemoteControl
import spock.lang.Ignore
import spock.lang.IgnoreIf
import spock.lang.IgnoreRest
import spock.lang.Shared
import spock.lang.Stepwise

import javax.sql.DataSource

@Stepwise
//@IgnoreIf({ SpecFlags.isTrue(SpecFlags.NO_FUNCTIONAL) || SpecFlags.isTrue(SpecFlags.NO_INTERNET) })
class LinkAccountSpec { // extends BaseFunctionalSpecification implements LogInUserTrait {

//    @Shared
//    ApplicationUnderTest aut = new CellarHqApplication()
//
//    @Shared
//    RemoteControl remote = new RemoteControl(aut)
//
//    def setupSpec() {
//        browser.baseUrl = aut.address.toString()
//        anEmailAccountUser(remote, 'testuser', 'test@cellarhq.com', 'password1')
//        logInUser('test@cellarhq.com', 'password1')
//    }
//
//    def cleanupSpec() {
//        remote.exec {
//            Sql sql = new Sql(get(DataSource))
//            sql.execute('delete from account_oauth where 1=1')
//            sql.close()
//        }
//        cleanUpUsers(remote)
//    }
//
//  @Ignore
//    @IgnoreRest
//    def 'link account features displayed'() {
//        when:
//        SettingsPage settingsPage = to SettingsPage
//
//        then:
//        settingsPage.linkEmailAccountLink.displayed
//        settingsPage.linkTwitterAccountLink.displayed
//    }
//
//    def 'link email account'() {
//        when:
//        LinkEmailAccountPage linkEmailAccountPage = to LinkEmailAccountPage
//
//        then:
//        linkEmailAccountPage.fillForm('test.2@cellarhq.com')
//        linkEmailAccountPage.submitForm()
//
//        when:
//        String hash = remote.exec {
//            Sql sql = new Sql(get(DataSource))
//            String result = sql.firstRow('select id from account_link_request').id
//            sql.close()
//            return result
//        }
//
//        then:
//        assert hash
//
//        when:
//        go("/settings/link-email/${hash}")
//
//        then:
//        LinkEmailAccountPasswordPage linkEmailAccountPasswordPage = at LinkEmailAccountPasswordPage
//        linkEmailAccountPasswordPage.fillForm('password1', 'password1')
//
//        when:
//        linkEmailAccountPasswordPage.submit()
//
//        then:
//        at YourCellarPage
//        def accounts = remote.exec {
//            Sql sql = new Sql(get(DataSource))
//            int accounts = sql.firstRow('select count(1) as count from account_email').count
//            sql.close()
//            return accounts
//        }
//        assert accounts == 2
//    }

//    def 'link twitter account'() {
//        when:
//        LinkTwitterAccountPage linkTwitterAccountPage = to LinkTwitterAccountPage
//
//        then:
//        linkTwitterAccountPage.fillForm('cellarhqtesting')
//
//        when:
//        linkTwitterAccountPage.submitForm()
//
//        then:
//        at YourCellarPage
//        def accounts = remote.exec {
//            Sql sql = new Sql(get(DataSource))
//            int accounts = sql.firstRow('select count(1) as count from account_oauth').count
//            sql.close()
//            return accounts
//        }
//        assert accounts == 1
//    }
}
