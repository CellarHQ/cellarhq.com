package com.cellarhq.s3

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.simpledb.AmazonSimpleDB
import com.amazonaws.services.simpledb.AmazonSimpleDBClient
import com.amazonaws.services.simpledb.model.SelectRequest
import com.amazonaws.services.simpledb.model.SelectResult

class S3ItemRetriever {
    static final String accessKeyId = 'AKIAIXBP2ORLESIX5CIQ'
    static final String secretKey = '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'

    void withEachItem(String simpleDbQuery, Closure itemHandler) {
        AmazonHelper amazonHelper = new AmazonHelper()
        AmazonSimpleDB simpleDB = new AmazonSimpleDBClient(
            new BasicAWSCredentials(
                accessKeyId,
                secretKey))

        String nextToken = null

        SelectRequest request = new SelectRequest().withSelectExpression(simpleDbQuery).withNextToken(nextToken)
        SelectResult result = simpleDB.select(request)

        result.items.each { itemHandler(it) }

        nextToken = result.nextToken

        while (nextToken != null) {
            request = new SelectRequest().withSelectExpression(simpleDbQuery).withNextToken(nextToken)
            result = simpleDB.select(request)

            nextToken = result.nextToken

            result.items.each {
                String beerName = amazonHelper.getAttribute(it.attributes, 'name').replace('"', '""')

                String breweryName = amazonHelper.getAttribute(it.attributes, 'brewery').replace('"', '""')
                String verificationSelect = "select * from cellar_PROD_cellars  where beer=\"${beerName}\" and brewery=\"${breweryName}\" limit 1"

//                try {
//                    SelectRequest verifyRequest = new SelectRequest().withSelectExpression(verificationSelect)
//                    SelectResult verifyResult = simpleDB.select(verifyRequest)
//
//                    if (verifyResult.items.size() == 1) {
                        itemHandler(it)
//                    }
//                } catch (AmazonClientException e) {
//                    println "amazon exception ${e.message} for ${verificationSelect}"
//                }
            }
        }
    }
}
