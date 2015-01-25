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
* Gradle 2.1
* Vagrant
* docker (and boot2docker if on osx)

In order to get started, you will need to run `vagrant up` from the `deployment` directory of this project. This vagrant
image will bring up the development PostgreSQL database. After that has been provisioned, you will need to run
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

Configuration
-------------

A number of system environment variables must be set in order to run certain parts of the application:

* `DB_HOST`: Database server host.
* `DB_PORT`: Database port number.
* `DB_NAME`: Database name.
* `DB_USERNAME`: Database username.
* `DB_PASSWORD`: Database password.
* `TWITTER_API_KEY`: Twitter API key. Necessary only for logging in with Twitter.
* `TWITTER_API_SECRET`: Twitter API secret key. Necessary only for logging in with Twitter.
* `AWS_ACCESS_KEY_ID`: Used for production; local development should always have mocked functionality.
* `AWS_SECRET_ACCESS_KEY`: Used for production; local development should always have mocked functionality.

SimpleDB Data
-------------

If you want to work from the SimpleDB (old production) data, you will need to run `$ gradle :model:run`. This will
take awhile to run, but at the end you'll have the entire production DB on your machine.

Running Local
-------------

`window 1$ gradle :web:run`
`window 2$ gradle :web:watch`

Deployments
-----------

The application relies on passing in environment variables for configuration. The current env vars are:

* `deploymentEnv`: Either `production` or `development`. If no value is provided, `development` will be selected as 
  the default. This configuration item is used for injecting different classes in Guice (ex: AmazonEmailService vs. 
  LogEmailService).

