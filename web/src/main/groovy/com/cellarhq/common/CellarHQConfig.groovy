package com.cellarhq.common

import groovy.transform.ToString

@ToString
class CellarHQConfig implements Serializable {
    String environment
    String hostName
    String googleAnalyticsTrackingCode

    String awsAccessKey
    String awsSecretKey

    String twitterApiKey
    String twitterApiSecret

    String s3StorageBucket

    String databaseServerName
    String databasePortNumber
    String databaseName
    String databaseUser
    String databasePassword

    final static String ENV_DEPLOYMENT_PRODUCTION = 'production'
    final static String ENV_HOSTNAME_PRODUCTION = 'www.cellarhq.com'

    boolean isProductionEnv() {
        return environment == ENV_DEPLOYMENT_PRODUCTION
    }

}
