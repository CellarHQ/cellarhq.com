CellarHQ
========

[![Gitter chat](https://badges.gitter.im/zerocontribution.png)](https://gitter.im/zerocontribution)

This is the repository for the CellarHQ.com v2 website.

Development
-----------

Requirements:

* Java 8
* Gradle 2.0
* Vagrant

In order to get started, you will need to run `vagrant up` in the root directory of this project. This vagrant image
will bring up the development PostgreSQL database. After that has been provisioned, you will need to run 
`gradle update` to run the Liquibase migrations.

Deployments
-----------

The application relies on passing in environment variables for configuration. The current env vars are:

* `deploymentEnv`: Either `production` or `development`. If no value is provided, `development` will be selected as 
  the default. This configuration item is used for injecting different classes in Guice (ex: AmazonEmailService vs. 
  LogEmailService).

