CellarHQ
========

This is the repository for the CellarHQ.com v2 website.

Development
-----------

Requirements:

* Java 11
* Gradle 6.1.1
* docker-compose

`gradle build` will build and test the entire application


Configuration
-------------

A number of system environment variables must be set in order to run certain parts of the application:

* `TWITTER_API_KEY`: Twitter API key. Necessary only for logging in with Twitter.
* `TWITTER_API_SECRET`: Twitter API secret key. Necessary only for logging in with Twitter.
* `AWS_ACCESS_KEY_ID`: Used for production; local development should always have mocked functionality.
* `AWS_SECRET_ACCESS_KEY`: Used for production; local development should always have mocked functionality.

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
  
  
## Snapshoting Production Database

```
pg_dump -h restored.c7ovcjlune88.us-east-1.rds.amazonaws.com -U cellarhq ebdb > snapshots/12-30-2015.sql
```

You will be prompted for the password.

## Loading a snapshot for local development

```
psql -h localhost -p 15432 -U cellarhq cellarhq < snapshots/12-30-2015.sql
```

