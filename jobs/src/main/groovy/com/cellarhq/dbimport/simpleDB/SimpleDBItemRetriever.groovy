package com.cellarhq.dbimport.simpledb

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.simpledb.AmazonSimpleDB
import com.amazonaws.services.simpledb.AmazonSimpleDBClient
import com.amazonaws.services.simpledb.model.SelectRequest
import com.amazonaws.services.simpledb.model.SelectResult

class SimpleDBItemRetriever {
  final AmazonSimpleDB simpleDB

  SimpleDBItemRetriever(String accessKey, String secretKey) {
    simpleDB = new AmazonSimpleDBClient(new BasicAWSCredentials(accessKey, secretKey))
  }

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
