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


String accessKeyId = 'AKIAIXBP2ORLESIX5CIQ'
String secretKey = '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'

AmazonSimpleDB simpleDB = new AmazonSimpleDBClient(new BasicAWSCredentials(
            accessKeyId,
            secretKey))

String nextToken = null

String selectAllBeersQuery = 'select * from cellar_PROD_beers where name="R&D Golden Ale" limit 2500'
SelectRequest request = new SelectRequest().withSelectExpression(selectAllBeersQuery).withNextToken(nextToken)
SelectResult result = simpleDB.select(request)
result.items.each {
    println it
}
