/**
 * This is meant to be run 1 time to import cellars and accounts from SimpleDB into postgres SQL.
 *
 * groovy scripts/import_cellars.groovy >> cellars.sql
 *
 * Then run the result sql load.
 */

@Grab('com.amazonaws:aws-java-sdk:1.3.6')

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.simpledb.AmazonSimpleDB
import com.amazonaws.services.simpledb.AmazonSimpleDBClient
import com.amazonaws.services.simpledb.model.*
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeParser
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormatterBuilder

String accessKeyId = 'AKIAIXBP2ORLESIX5CIQ'
String secretKey = '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'
attrScreenName = 'screenName'
attrBeer = "beer"
attrBrewery = "brewery"
attrSize = "size"
attrQuantity = "quantity"
attrBottleDate = "bottleDate"
attrNotes = "notes"

AmazonSimpleDB simpleDB = new AmazonSimpleDBClient(new BasicAWSCredentials(
        accessKeyId,
        secretKey))

DateTimeParser[] parsers = [
    DateTimeFormat.forPattern( "yyyy-MM-dd HH" ).getParser(),
    DateTimeFormat.forPattern( "yyyy-MM-dd" ).getParser()
]

DateTimeFormatter formatter = new DateTimeFormatterBuilder().append( null, parsers ).toFormatter()


String nextToken = null

String selectAllAccountsQuery = 'select * from cellar_PROD_cellars where screenName="kyleboon" limit 2500 '
SelectRequest request = new SelectRequest().withSelectExpression(selectAllAccountsQuery).withNextToken(nextToken)
SelectResult result = simpleDB.select(request)
result.items.each {
    println buildSqlInsert(it)
}
nextToken = result.nextToken

while (nextToken != null) {
    request = new SelectRequest().withSelectExpression(selectAllAccountsQuery).withNextToken(nextToken)
    result = simpleDB.select(request)

    nextToken = result.nextToken

    result.items.each {
        println buildSqlInsert(it)
    }
}

String buildSqlInsert(Item item) {
    """INSERT INTO cellared_drink (
        cellar_id,
        drink_id,
        bottle_date,
        size,
        quantity,
        notes)
    VALUES (
    (select id from cellar where screen_name='${getAttribute(item.attributes, attrScreenName)}'),
    (select d.id from drink d inner join organization o on d.organization_id = o.id where lower(d.name)='${getAttribute(item.attributes, attrBeer).toLowerCase()}' and lower(o.name)='${getAttribute(item.attributes, attrBrewery).toLowerCase()}'),
    ${buildDateString(item.attributes, attrBottleDate)},
    '${getAttribute(item.attributes, attrSize)}',
    ${getNumberAttribute(item.attributes, attrQuantity)},
    '${getAttribute(item.attributes, attrNotes)}');"""
}

public String buildDateString(List<Attribute> attributes, String name, DateTimeFormatter formatter) {
    def attribute = attributes.find {
        name.equalsIgnoreCase(it.name)
    }

    if (attribute?.value) {
        String dataString = attribute.value

        try {
            formatter.parseDateTime(dataString)
            return date.toInstant().toString()
        }  catch (IllegalArgumentException e) {
            return 'null'
        }
    }

    return 'null'
}

public String getAttribute(List<Attribute> attributes, String name) {
    def attribute = attributes.find {
        name.equalsIgnoreCase(it.name)
    }
    return escape(attribute?.value ?: '')
}

public String getNumberAttribute(List<Attribute> attributes, String name) {
    def attribute = attributes.find {
        name.equalsIgnoreCase(it.name)
    }

    String str = attribute?.value

    if (!str) return 'null'

    try {
        return str.toInteger().toString()
    } catch (NumberFormatException e) {
        return 'null'
    }
}

String escape(String string) {
    return string.replaceAll("'", "''")
}
