/**
 * This is meant to be run 1 time to import beer from SimpleDB into postgres SQL.
 *
 * groovy scripts/import_beer.groovy >> beer.sql
 *
 * Then run the result sql load.
 */

@Grab('com.amazonaws:aws-java-sdk:1.3.6')
@Grab('com.github.slugify:slugify:2.1.2')

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.simpledb.AmazonSimpleDB
import com.amazonaws.services.simpledb.AmazonSimpleDBClient
import com.amazonaws.services.simpledb.model.*
import com.github.slugify.Slugify

String accessKeyId = 'AKIAIXBP2ORLESIX5CIQ'
String secretKey = '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'

attrBeerId = "beerId"

attrStyleId = "styleId"
attrStyle = "style"

   attrBreweryId = "breweryId"
   attrBrewery = "brewery"

   attrGlasswareId = "glasswareId"
   attrGlassware = "glassware"

   attrName = "name"

   attrDescription0 = "description0"
   attrDescription2 = "description2"
   attrDescription3 = "description3"
   attrDescription4 = "description4"

   attrSrm = "srm"
   attrAbv = "abv"
   attrIbu = "ibu"
   attrAvailability = "availability"

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

String selectAllBeersQuery = 'select * from cellar_PROD_beers limit 2500'
SelectRequest request = new SelectRequest().withSelectExpression(selectAllBeersQuery).withNextToken(nextToken)
SelectResult result = simpleDB.select(request)
result.items.each {
    println buildSqlInsert(it)
}

nextToken = result.nextToken

while(nextToken != null) {
	request = new SelectRequest().withSelectExpression(selectAllBeersQuery).withNextToken(nextToken)
	result = simpleDB.select(request)

    nextToken = result.nextToken

    result.items.each {
    	println buildSqlInsert(it)
	}
}

public String buildSqlInsert(Item item) {
    """INSERT INTO drink (organization_id, style_id, glassware_id, drink_type, slug, name, description, srm, ibu, abv,
    availability, searchable, brewery_db_id, brewery_db_last_updated, locked, needs_moderation)
    VALUES (
    (select id from organization where lower(name)='${getAttribute(item.attributes, attrBrewery).toLowerCase()}'),
    (select id from style where lower(name)='${getAttribute(item.attributes, attrStyle).toLowerCase()}'),
    (select id from glassware where lower(name)='${getAttribute(item.attributes, attrGlassware).toLowerCase()}'),
    'BEER',
    '${generateSlug(item.attributes)}',
    '${getAttribute(item.attributes, attrName)}',
    '${buildDescription(item.attributes)}',
    ${getNumberAttribute(item.attributes, attrSrm)},
    ${getNumberAttribute(item.attributes, attrIbu)},
    ${getNumberAttribute(item.attributes, attrAbv)},
    '${getAttribute(item.attributes, attrAvailability)}',
    true,
    '${getAttribute(item.attributes, attrBrewerydbId)}',
    ${buildDateString(item.attributes, attrBrewerydbLastUpdate)},
    true,
    false);"""
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

public String generateSlug(List<Attribute> attributes) {
    String name = getAttribute(attributes, attrName)
    return new Slugify().slugify(name)
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
