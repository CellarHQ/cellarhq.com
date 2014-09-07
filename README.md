CellarHQ
========

This is the repository for the CellarHQ.com v2 website.

Chat
----

We have a Slack account for talking with each other about things. Ask Rob for an invite.

Development
-----------

Requirements:

* Java 8
* Gradle 2.0
* Vagrant
* docker (and boot2docker if on osx)

In order to get started, you will need to run `vagrant up` in the root directory of this project. This vagrant image
will bring up the development PostgreSQL database. After that has been provisioned, you will need to run 
`gradle update` to run the Liquibase migrations. This will run the migrations against the development and testing
databases which are contained in the vagrant image. If you want to run the migrations locally against the production
RDS instance, you need to run `gradle update -PrunList=production`. This assumes you have set cellarhqprodpasswd in
`~/.gradle/gradle.properties`. Ask Rob or Kyle for this password if you need it.

`gradle build` will build and test the entire application including creating a docker container for deployment or
local testing.

You can run the docker container locally against the production RDS database as well. After running `gradle build` you
can start the container like this:

```
 docker run -e dataSource.serverName='cellarhq-prod.c7ovcjlune88.us-east-1.rds.amazonaws.com' \
 -e dataSource.port='5432' \
 -e dataSource.password='PASSWORDGOESHERE' \
 -p 5050:5050 com.cellarhq/cellarhq:0.1
```


Deployments
-----------

The application relies on passing in environment variables for configuration. The current env vars are:

* `deploymentEnv`: Either `production` or `development`. If no value is provided, `development` will be selected as 
  the default. This configuration item is used for injecting different classes in Guice (ex: AmazonEmailService vs. 
  LogEmailService).

