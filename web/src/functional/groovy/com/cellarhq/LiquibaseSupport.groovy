package com.cellarhq

import com.cellarhq.common.CellarHQConfig
import com.zaxxer.hikari.HikariConfig
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import liquibase.Contexts
import liquibase.Liquibase
import liquibase.configuration.ConfigurationValueProvider
import liquibase.configuration.LiquibaseConfiguration
import liquibase.configuration.SystemPropertyProvider
import liquibase.database.Database
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import liquibase.resource.CompositeResourceAccessor
import liquibase.resource.FileSystemResourceAccessor
import liquibase.resource.ResourceAccessor
import liquibase.util.NetUtil

import java.sql.Connection
import java.sql.DriverManager

trait LiquibaseSupport {

  private final static String LIQUIBASE_NAMESPACE = 'liquibase'
  private final static String LIQUIBASE_CHANGELOG = 'changelog'
  private final static String LIQUIBASE_CONTEXTS = 'contexts'
  private final static String LIQUIBASE_ONERROR_FAIL = 'onerror.fail'
  private final static String LIQUIBASE_SCHEMA_DEFAULT = 'schema.default'

  private static boolean ranLiquibase = false

  private String hostName
  private ConfigurationValueProvider valueProvider
  private Connection connection

  void runLiquibase() {
    if (!ranLiquibase) {
      valueProvider = new SystemPropertyProvider()
      LiquibaseConfiguration.instance.init(valueProvider)

      try {
        hostName = NetUtil.localHostName
      } catch (Exception e) {
        throw new RuntimeException(e)
      }

      if (hostName) {
        String failOnError = null
        try {
          failOnError = valueProvider.getValue(LIQUIBASE_NAMESPACE, LIQUIBASE_ONERROR_FAIL)
          executeUpdate()
        } catch (Exception e) {
          if (failOnError != null && failOnError.asBoolean()) {
            throw new RuntimeException(e)
          }
        }
      }
      ranLiquibase = true
    }
  }

  private void recreateSchema() {
    Sql sql = new Sql(getConnection())
    sql.rows("select table_name from information_schema.tables where table_schema = 'public'").each {
      GroovyRowResult row -> sql.execute((String) "drop table public.${row.table_name} cascade")
    }
  }

  private void executeUpdate() {
    String changeLogFile = valueProvider.getValue(LIQUIBASE_NAMESPACE, LIQUIBASE_CHANGELOG)
    if (changeLogFile == null) {
      throw new RuntimeException("Cannot run Liquibase: 'changeLogFile' is not set")
    }

    String contexts = valueProvider.getValue(LIQUIBASE_NAMESPACE, LIQUIBASE_CONTEXTS)
    String defaultSchema = valueProvider.getValue(LIQUIBASE_NAMESPACE, LIQUIBASE_SCHEMA_DEFAULT)

    Database database = null
    try {
      recreateSchema()

      Thread currentThread = Thread.currentThread()
      ClassLoader contextClassLoader = currentThread.contextClassLoader
      ResourceAccessor threadClFO = new ClassLoaderResourceAccessor(contextClassLoader)

      ResourceAccessor clFO = new ClassLoaderResourceAccessor()
      ResourceAccessor fsFO = new FileSystemResourceAccessor()

      database = DatabaseFactory.instance.findCorrectDatabaseImplementation(new JdbcConnection(connection))
      database.defaultSchemaName = defaultSchema

      Liquibase liquibase = new Liquibase(changeLogFile,
        new CompositeResourceAccessor(clFO, fsFO, threadClFO), database)
      liquibase.update(new Contexts(contexts))
    } finally {
      if (database != null) {
        database.close()
      } else if (connection != null) {
        connection.close()
      }
    }
  }

  /**
   * @todo Blehhh...
   */
  private Connection getConnection() {
    if (!connection) {
      Class.forName('org.postgresql.ds.PGSimpleDataSource')
      connection = DriverManager.getConnection(getJdbcUrl())
    }
    return connection
  }

  String getJdbcUrl() {
    return remote.exec {
      HikariConfig config = get(HikariConfig)
      String host = config.dataSourceProperties.serverName
      String port =  config.dataSourceProperties.portNumber
      String dbname =  config.dataSourceProperties.databaseName
      String user =  config.username
      String password =  config.password

      "jdbc:postgresql://${host}:${port}/${dbname}?user=${user}&password=${password}"

    }
  }
}
