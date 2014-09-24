package com.cellarhq.simpleDB

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.simpledb.AmazonSimpleDB
import com.amazonaws.services.simpledb.AmazonSimpleDBClient
import com.amazonaws.services.simpledb.model.SelectRequest
import com.amazonaws.services.simpledb.model.SelectResult

class SimpleDBItemRetriever {
    static final String accessKeyId = 'AKIAIXBP2ORLESIX5CIQ'
    static final String secretKey = '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'

    static AmazonSimpleDB simpleDB = new AmazonSimpleDBClient(
        new BasicAWSCredentials(
            accessKeyId,
            secretKey))

    void withEachItem(String simpleDbQuery, Closure itemHandler) {
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
                        itemHandler(it)
            }
        }
    }

    Boolean doesQueryReturnResult(String query) {
        SelectRequest verifyRequest = new SelectRequest().withSelectExpression(query)
        SelectResult verifyResult = simpleDB.select(verifyRequest)
        return verifyResult.items.size() > 0
    }
}
