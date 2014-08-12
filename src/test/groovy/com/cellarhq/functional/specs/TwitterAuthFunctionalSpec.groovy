package com.cellarhq.functional.specs

import static com.cellarhq.HibernateDSL.transaction

import com.cellarhq.SpecFlags
import com.cellarhq.dao.OAuthAccountDAO
import com.cellarhq.domain.OAuthAccount
import com.cellarhq.functional.pages.HomePage
import com.cellarhq.functional.pages.LoginPage
import com.cellarhq.functional.pages.YourCellarPage
import com.cellarhq.functional.pages.thirdparty.TwitterOAuthPage
import geb.spock.GebReportingSpec
import groovy.sql.Sql
import org.hibernate.SessionFactory
import ratpack.groovy.test.LocalScriptApplicationUnderTest
import ratpack.handling.Context
import ratpack.test.ApplicationUnderTest
import ratpack.test.remote.RemoteControl
import spock.lang.IgnoreIf
import spock.lang.Shared
import spock.lang.Stepwise

import javax.sql.DataSource

@Stepwise
@IgnoreIf({ SpecFlags.isTrue(SpecFlags.NO_FUNCTIONAL) || SpecFlags.isTrue(SpecFlags.NO_INTERNET) })
class TwitterAuthFunctionalSpec extends GebReportingSpec {

    @Shared
    ApplicationUnderTest aut = new LocalScriptApplicationUnderTest('other.remoteControl.enabled': 'true')

    @Shared
    RemoteControl remote = new RemoteControl(aut)

    def setup() {
        browser.baseUrl = aut.address.toString()
    }

    def cleanupSpec() {
        remote.exec {
            Sql sql = new Sql(get(DataSource))
            sql.execute('delete from account_oauth where 1=1')
            sql.execute('delete from cellar_role where 1=1')
            sql.execute('delete from cellar where 1=1')
            sql.close()
        }
    }

    def 'verify no oauth accounts exist'() {
        when:
        long oauthAccounts = (long) remote.exec {
            transaction(get(SessionFactory)) {
                get(OAuthAccountDAO).countAll()
            }
        }

        then:
        oauthAccounts == 0
    }

    def 'login menu is rendered without session'() {
        when:
        HomePage page = to HomePage

        then:
        page.unauthenticatedMenu
        !page.authenticatedMenu
    }

    def 'login page renders the twitter login button'() {
        when:
        LoginPage page = to LoginPage

        then:
        page.twitterLoginLink.displayed
        page.twitterLoginLink.@href.endsWith('/login-twitter')
    }

    def 'accessing twitter login endpoint logs a user in and sends them to Your Cellar'() {
        when:
        LoginPage page = to LoginPage

        then:
        page.twitterLoginLink.click()

        when:
        TwitterOAuthPage twitter = at TwitterOAuthPage

        then:
        twitter.login()
        waitFor 30, { at YourCellarPage }

        when:
        YourCellarPage yourCellarPage = to YourCellarPage

        then:
        yourCellarPage.authenticatedMenu
        !yourCellarPage.unauthenticatedMenu
    }

    def 'verify oauth account was created'() {
        when:
        // This is a tad gnarly. Domains aren't serializable.
        Map account = (Map) remote.exec {
            OAuthAccount oAuthAccount = transaction(get(SessionFactory)) {
                get(OAuthAccountDAO).findByClientAndUsername(
                        OAuthAccount.Client.TWITTER,
                        TwitterOAuthPage.TWITTER_VALID_USERNAME)
            }

            return [
                    username: oAuthAccount.username,
                    cellar: [
                            screenName: oAuthAccount.cellar.screenName,
                            displayName: oAuthAccount.cellar.displayName,
                            bio: oAuthAccount.cellar.bio,
                            website: oAuthAccount.cellar.website,
                            location: oAuthAccount.cellar.location,
                            lastLogin: oAuthAccount.cellar.lastLogin?.toString()
                    ]
            ]
        }

        then:
        account
        account.cellar.screenName == account.username
        account.cellar.displayName == 'Rob'
        account.cellar.bio == 'We use this account for functional testing our twitter integrations.'
        account.cellar.website != null // TODO: Twitter sends us a t.co shortened URL.
        account.cellar.location == 'an automated test somewhere'
        account.cellar.lastLogin != null
    }
}
