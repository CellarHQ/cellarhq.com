package com.cellarhq.s3

import com.amazonaws.services.simpledb.model.Item
import com.cellarhq.generated.tables.pojos.AccountEmail
import com.cellarhq.generated.tables.pojos.AccountOauth
import com.cellarhq.generated.tables.pojos.Cellar
import org.joda.time.DateTime
import org.jooq.DSLContext

import java.sql.Timestamp

class S3ToCellarMapper {
    static final String attrScreenName = 'screenName'
    static final String attrName = "name"
    static final String attrLocation = "location"
    static final String attrWebsite = "website"
    static final String attrBio = "bio"
    static final String attrUpdateFromNetwork = "updateFromNetwork"
    static final String attrPrivate = "_private"
    static final String attrEmail = "email"
    static final String attrLastLogin = "lastLogin"
    static final String attrCreated = "created"
    static final String attrUpdated = "updated"

    AmazonHelper helper = new AmazonHelper()

    Cellar mapItemToCellar(DSLContext dslContext, Item item) {
        return new Cellar().with { Cellar self ->
            self.contactEmail = helper.getAttribute(item.attributes, attrEmail)
            self.displayName = helper.getAttribute(item.attributes, attrName)
            self.screenName = helper.getAttribute(item.attributes, attrScreenName)
            self.bio = helper.getAttribute(item.attributes, attrBio)
            self.location = helper.getAttribute(item.attributes, attrLocation)
            self.updateFromNetwork = helper.getAttribute(item.attributes, attrUpdateFromNetwork)
            self.private = helper.getAttribute(item.attributes, attrPrivate)
            self.website  = helper.getAttribute(item.attributes, attrWebsite)

            // todo: should I trust what is in simpledb or recalculate after import?
            // leaning towards recalculation
            self.uniqueBeers = 0
            self.uniqueBreweries = 0
            self.totalBeers = 0
            self.hasTradeableBeers = false

            DateTime lastLogin = helper.getDateAttribute(item.attributes, attrLastLogin)
            if (lastLogin) {
                self.lastLogin = new Timestamp(lastLogin.millis)
            }

            DateTime created = helper.getDateAttribute(item.attributes, attrCreated)
            if (created) {
                self.createdDate = new Timestamp(created.millis)
            }

            DateTime updated = helper.getDateAttribute(item.attributes, attrUpdated)
            if (updated) {
                self.modifiedDate = new Timestamp(updated.millis)
            }

            self
        }
    }

    AccountOauth mapItemToAccountOAuth(DSLContext dslContext, Item item) {
        return new AccountOauth().with { AccountOauth self ->
            self.client = 'TWITTER'
            self.username = helper.getAttribute(item.attributes, attrScreenName)
            DateTime created = helper.getDateAttribute(item.attributes, attrCreated)
            if (created) {
                self.createdDate = new Timestamp(created.millis)
            }

            DateTime updated = helper.getDateAttribute(item.attributes, attrUpdated)
            if (updated) {
                self.modifiedDate = new Timestamp(updated.millis)
            }

            self
        }
    }

    AccountEmail mapItemToAccountEmail(DSLContext dslContext, Item item) {
        return new AccountEmail().with { AccountEmail self ->
            self.email = helper.getAttribute(item.attributes, attrEmail)
            self.password = 'unclaimed'

            DateTime created = helper.getDateAttribute(item.attributes, attrCreated)
            if (created) {
                self.createdDate = new Timestamp(created.millis)
            }

            DateTime updated = helper.getDateAttribute(item.attributes, attrUpdated)
            if (updated) {
                self.modifiedDate = new Timestamp(updated.millis)
            }

            self
        }
    }
}
