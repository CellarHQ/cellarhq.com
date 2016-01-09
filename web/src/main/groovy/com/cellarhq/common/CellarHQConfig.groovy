package com.cellarhq.common

import groovy.transform.ToString

@ToString
class CellarHQConfig implements Serializable {
  String environment
  String hostname
  String googleAnalyticsTrackingCode

  String awsAccessKey
  String awsSecretKey

  String twitterApiKey
  String twitterApiSecret

  String s3StorageBucket

  String cookieSecret

  final static String ENV_DEPLOYMENT_PRODUCTION = 'production'

  boolean isProductionEnv() {
    return environment == ENV_DEPLOYMENT_PRODUCTION
  }
}
