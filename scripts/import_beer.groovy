@Grab('com.amazonaws:aws-java-sdk:1.3.6')

import com.amazonaws.AmazonServiceException
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.simpledb.AmazonSimpleDB
import com.amazonaws.services.simpledb.AmazonSimpleDBClient
import com.amazonaws.services.simpledb.model.*

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

String selectAllBeersQuery = 'select * from cellar_PROD_beers where brewerydbId is not null limit 2500'
	SelectRequest request = new SelectRequest().withSelectExpression(selectAllBeersQuery).withNextToken(nextToken)
	SelectResult result = simpleDB.select(request)
	nextToken = result.nextToken  

int total = result.items.size()
while(nextToken != null) {
	request = new SelectRequest().withSelectExpression(selectAllBeersQuery).withNextToken(nextToken)
	result = simpleDB.select(request)
    
    nextToken = result.nextToken         
    total += result.items.size()

    result.items.each {
    	println buildSqlInsert(it)
	}
} 

println "Found ${total} total beers"
println 'done!'

public String buildSqlInsert(Item item) {
	"insert into drink name=${getAttribute(it.attributes, attrName)}"

}

public String getAttribute(List<Attribute> attributes, String name) {
        def attribute = attributes.find {
            name.equalsIgnoreCase(it.name)
        }
        return attribute?.value
}