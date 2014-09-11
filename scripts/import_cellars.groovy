/**
 * This is meant to be run 1 time to import cellars and accounts from SimpleDB into postgres SQL.
 *
 * groovy scripts/import_cellars.groovy >> cellars.sql
 *
 * Then run the result sql load.
 */

@Grab('com.amazonaws:aws-java-sdk:1.3.6')
@Grab('com.github.slugify:slugify:2.1.2')

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.simpledb.AmazonSimpleDB
import com.amazonaws.services.simpledb.AmazonSimpleDBClient
import com.amazonaws.services.simpledb.model.*

String accessKeyId = 'AKIAIXBP2ORLESIX5CIQ'
String secretKey = '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'

attrId = "accountId"
attrScreenName = 'screenName'
attrScreenNameLookup = "screenNameLookup"
attrName = "name"
attrLocation = "location"
attrWebsite = "website"
attrBio = "bio"

attrUpdateFromNetwork = "updateFromNetwork"
attrPrivate = "_private"

attrEmail = "email"
attrPassword = "password"
attrSource = "source"

attrCountTotalBeers = "countTotalBeers"
attrCountUniqueBeers = "countUniqueBeers"
attrCountUniqueBreweries = "countUniqueBreweries"

attrLastLogin = "lastLogin"
attrSearchable = "searchable"
attrCreated = "created"
attrUpdated = "updated"

AmazonSimpleDB simpleDB = new AmazonSimpleDBClient(new BasicAWSCredentials(
        accessKeyId,
        secretKey))

String nextToken = null

String selectAllAccountsQuery = 'select * from cellar_PROD_accounts limit 2500'
SelectRequest request = new SelectRequest().withSelectExpression(selectAllAccountsQuery).withNextToken(nextToken)
SelectResult result = simpleDB.select(request)
result.items.each {
    println buildSqlInsert(it)

    if (isTwitter(it)) {
        println buildTwitterAccount(it)
    } else {
        println buildEmailAccount(it)
    }
}

nextToken = result.nextToken

while (nextToken != null) {
    request = new SelectRequest().withSelectExpression(selectAllAccountsQuery).withNextToken(nextToken)
    result = simpleDB.select(request)

    nextToken = result.nextToken

    result.items.each {
        println buildSqlInsert(it)

        if (isTwitter(it)) {
            println buildTwitterAccount(it)
        } else {
            println buildEmailAccount(it)
        }
    }
}

Boolean isTwitter(Item item) {
    getAttribute(item.attributes, attrSource) == 'twitter'
}

String buildSqlInsert(Item item) {
    """INSERT INTO cellar (screen_name, display_name, location, website, bio, update_from_network, contact_email,
    private, last_login, last_login_ip, created_date, modified_date)
    VALUES (
    '${getAttribute(item.attributes, attrScreenName)}',
    '${getAttribute(item.attributes, attrName)}',
    '${getAttribute(item.attributes, attrLocation)}',
    '${getAttribute(item.attributes, attrWebsite)}',
    '${getAttribute(item.attributes, attrBio)}',
    ${getBooleanAttribute(item.attributes, attrUpdateFromNetwork)},
    '${getAttribute(item.attributes, attrEmail)}',
    ${getBooleanAttribute(item.attributes, attrPrivate)},
     ${buildDateString(item.attributes, attrLastLogin)},
    null,
     ${buildDateString(item.attributes, attrCreated)},
    ${buildDateString(item.attributes, attrUpdated)});"""
}

String buildTwitterAccount(Item item) {
    """INSERT INTO account_oauth (client, username, cellar_id, created_date, modified_date)
    VALUES (
    'TWITTER',
    '${getAttribute(item.attributes, attrScreenName)}',
    (select id from cellar where screen_name = '${getAttribute(item.attributes, attrScreenName)}'),
    ${buildDateString(item.attributes, attrCreated)},
    ${buildDateString(item.attributes, attrUpdated)});"""
}

String buildEmailAccount(Item item) {

    """INSERT INTO account_email (email, password, cellar_id, created_date, modified_date)
    VALUES (
    '${getAttribute(item.attributes, attrEmail)}',
    'unclaimed+',
    (select id from cellar where screen_name = '${getAttribute(item.attributes, attrScreenName)}'),
    ${buildDateString(item.attributes, attrCreated)},
    ${buildDateString(item.attributes, attrUpdated)});"""
}

public String buildDateString(List<Attribute> attributes, String name) {
    def attribute = attributes.find {
        name.equalsIgnoreCase(it.name)
    }
    return attribute?.value ? "'${attribute?.value}'" : 'null'
}

public String getAttribute(List<Attribute> attributes, String name) {
    def attribute = attributes.find {
        name.equalsIgnoreCase(it.name)
    }
    return escape(attribute?.value ?: '')
}

public Boolean getBooleanAttribute(List<Attribute> attributes, String name) {
    def attribute = attributes.find {
        name.equalsIgnoreCase(it.name)
    }

    String str = attribute?.value

    return str == 'true'

}

String escape(String string) {
    return string.replaceAll("'", "''")
}