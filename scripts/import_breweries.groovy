/**
 * This is meant to be run 1 time to import breweries from SimpleDB into postgres SQL.
 *
 * groovy scripts/import_breweries.groovy >> brewerys.sql
 *
 * Then run the result sql load. It will generate ~4800 inserts.
 *
 * The brewerydbLastUpdate is failing to insert right now. Need to update
 * the date format. Also still having problems with some UTF8 characters.
 */

@Grab('com.amazonaws:aws-java-sdk:1.3.6')

import com.amazonaws.AmazonServiceException
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.simpledb.AmazonSimpleDB
import com.amazonaws.services.simpledb.AmazonSimpleDBClient
import com.amazonaws.services.simpledb.model.*

String accessKeyId = 'AKIAIXBP2ORLESIX5CIQ'
String secretKey = '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'

attrName = "name"
attrDescription0 = "description0"
attrDescription2 = "description2"
attrDescription3 = "description3"
attrDescription4 = "description4"
attrPhotoUrl = "photoUrl"
attrPhotoFullSizeUrl = "photoFullSizeUrl"
attrPhotoScreenName = "photoScreenName"
attrPhotoCreated = "photoCreated"
attrEstablished = "established"
attrWebsite = "website"
attrPhone = "phone"
attrAddress = "address"
attrExtendedAddress = "extendedAddress"
attrLocality = "locality"
attrRegion = "region"
attrPostalCode = "postalCode"
attrCountry = "country"
attrSearchable = "searchable"
attrCreated = "created"
attrUpdated = "updated"
attrNeedsModeration = "needsModeration"
attrBrewerydbId = "brewerydbId"
attrBrewerydbLastUpdate = "brewerydbLastUpdate"



AmazonSimpleDB simpleDB = new AmazonSimpleDBClient(new BasicAWSCredentials(
        accessKeyId,
        secretKey))

String nextToken = null

String selectAllBreweriesQuery = 'select * from cellar_PROD_breweries limit 2500'
SelectRequest request = new SelectRequest().withSelectExpression(selectAllBreweriesQuery).withNextToken(nextToken)
SelectResult result = simpleDB.select(request)
nextToken = result.nextToken

int total = result.items.size()
while (nextToken != null) {
    request = new SelectRequest().withSelectExpression(selectAllBreweriesQuery).withNextToken(nextToken)
    result = simpleDB.select(request)

    nextToken = result.nextToken
    total += result.items.size()

    result.items.each {
        println buildSqlInsert(it)
    }
}

public String buildSqlInsert(Item item) {
    """INSERT INTO organization
     (type, slug, name, description, established, phone, website, address, address2, locality, postal_code, country, brewery_db_id, brewery_db_last_updated, locked)
     VALUES ('BREWERY', '${generateSlug(item.attributes)}', '${getAttribute(item.attributes, attrName)}', '${
        buildDescription(item.attributes)
    }', ${getNumberAttribute(item.attributes, attrEstablished)},
      '${getAttribute(item.attributes, attrPhone)}', '${getAttribute(item.attributes, attrWebsite)}', '${
        getAttribute(item.attributes, attrAddress)
    }', '${getAttribute(item.attributes, attrExtendedAddress)}',
      '${getAttribute(item.attributes, attrLocality)}', '${getAttribute(item.attributes, attrPostalCode)}', '${
        getAttribute(item.attributes, attrCountry)
    }', '${getAttribute(item.attributes, attrBrewerydbId)}',
        ${buildDateString(item.attributes, attrBrewerydbLastUpdate)}, true);
"""

}

public String buildDescription(List<Attribute> attributes) {
    desc = getAttribute(attributes, attrDescription0)

    if (getAttribute(attributes, attrDescription2)) {
        desc += ' ' + getAttribute(attributes, attrDescription2)
    }

    if (getAttribute(attributes, attrDescription3)) {
        desc += ' ' + getAttribute(attributes, attrDescription3)
    }

    if (getAttribute(attributes, attrDescription4)) {
        desc += ' ' + getAttribute(attributes, attrDescription4)
    }



    return desc
}

public String buildDateString(List<Attribute> attributes, String name) {
    def attribute = attributes.find {
        name.equalsIgnoreCase(it.name)
    }
    return attribute?.value ? "'${attribute?.value}'" : 'null'
}

/**
 * This probably needs enhanced a bit.
 *
 * @param attributes [description]
 * @return [description]
 */
public String generateSlug(List<Attribute> attributes) {
    getAttribute(attributes, attrName).replaceAll('[^A-Za-z0-9\\s]', '')
            .replaceAll('\\s', '-')
            .toLowerCase()
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