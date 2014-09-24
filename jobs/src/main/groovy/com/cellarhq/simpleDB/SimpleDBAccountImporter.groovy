package com.cellarhq.simpleDB

import com.amazonaws.services.simpledb.model.Item
import com.cellarhq.generated.tables.pojos.AccountOauth
import com.cellarhq.generated.tables.pojos.AccountEmail
import com.cellarhq.generated.tables.pojos.Cellar
import com.cellarhq.generated.tables.records.AccountEmailRecord
import com.cellarhq.generated.tables.records.AccountOauthRecord
import com.cellarhq.generated.tables.records.CellarRecord
import org.jooq.DSLContext
import org.jooq.exception.DataAccessException

import static com.cellarhq.generated.Tables.ACCOUNT_EMAIL
import static com.cellarhq.generated.Tables.ACCOUNT_OAUTH
import static com.cellarhq.generated.Tables.CELLAR

class SimpleDBAccountImporter {
    AmazonHelper helper = new AmazonHelper()

    void importAccountsFromS3(DSLContext dslContext) {
        String selectAllAccounts = 'select * from cellar_PROD_accounts limit 2500'

        SimpleDBItemRetriever itemRetriever = new SimpleDBItemRetriever()
        SimpleDBToCellarMapper cellarMapper = new SimpleDBToCellarMapper()

        itemRetriever.withEachItem(selectAllAccounts) { Item item ->
            Cellar cellar = cellarMapper.mapItemToCellar(dslContext, item)

            CellarRecord cellarRecord = dslContext.newRecord(CELLAR, cellar)
            cellarRecord.reset(CELLAR.ID)

            if (!cellarRecord.createdDate) cellarRecord.reset(CELLAR.CREATED_DATE)
            if (!cellarRecord.modifiedDate) cellarRecord.reset(CELLAR.MODIFIED_DATE)

            try {
                cellarRecord.store()
            } catch (DataAccessException e) {
                println "Error inserting because ${e.message}"
            }

            if (isTwitterAccount(item)) {
                Integer cellarId = dslContext.select(CELLAR.ID)
                    .from(CELLAR)
                    .where(
                    CELLAR.SCREEN_NAME.eq(
                        helper.getAttribute(item.attributes, 'screenName')))
                    .fetchOneInto(Integer)

                AccountOauth oauth = cellarMapper.mapItemToAccountOAuth(dslContext, item)
                AccountOauthRecord oauthRecord = dslContext.newRecord(ACCOUNT_OAUTH, oauth)
                oauthRecord.reset(ACCOUNT_OAUTH.ID)
                oauthRecord.cellarId = cellarId

                if (!oauthRecord.createdDate) oauthRecord.reset(ACCOUNT_OAUTH.CREATED_DATE)
                if (!oauthRecord.modifiedDate) oauthRecord.reset(ACCOUNT_OAUTH.MODIFIED_DATE)

                try {
                    oauthRecord.store()
                } catch (DataAccessException e) {
                    println "Error inserting because ${e.message}"
                }
            } else {
                Integer cellarId = dslContext.select(CELLAR.ID)
                    .from(CELLAR)
                    .where(
                        CELLAR.SCREEN_NAME.eq(
                            helper.getAttribute(item.attributes, 'screenName')))
                    .fetchOneInto(Integer)

                AccountEmail email = cellarMapper.mapItemToAccountEmail(dslContext, item)
                AccountEmailRecord emailRecord = dslContext.newRecord(ACCOUNT_EMAIL, email)
                emailRecord.reset(ACCOUNT_EMAIL.ID)
                emailRecord.cellarId = cellarId

                if (!emailRecord.createdDate) emailRecord.reset(ACCOUNT_EMAIL.CREATED_DATE)
                if (!emailRecord.modifiedDate) emailRecord.reset(ACCOUNT_EMAIL.MODIFIED_DATE)

                try {
                    emailRecord.store()
                } catch (DataAccessException e) {
                    println "Error inserting because ${e.message}"
                }
            }
        }
    }

    boolean isTwitterAccount(Item item) {
        helper.getAttribute(item.attributes, 'source') == 'twitter'
    }
}
