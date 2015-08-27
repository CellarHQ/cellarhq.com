package com.cellarhq.functional

import com.cellarhq.LiquibaseSupport
import com.cellarhq.auth.services.AccountService
import com.cellarhq.common.CellarHQConfig
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.EmailAccount
import geb.spock.GebReportingSpec
import groovy.sql.Sql
import org.h2.jdbc.JdbcSQLException
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import ratpack.test.remote.RemoteControl
import spock.lang.Shared

import javax.sql.DataSource
import java.sql.Connection
import java.sql.DriverManager

abstract class BaseFunctionalSpecification extends GebReportingSpec implements LiquibaseSupport {

  @Shared
  public DSLContext create

  @Shared
  public Connection conn

  def setupSpec() {
    Class.forName("org.postgresql.ds.PGSimpleDataSource").newInstance()
    conn = DriverManager.getConnection(getJdbcUrl())
    create = DSL.using(conn, SQLDialect.POSTGRES)

    runLiquibase()
  }

  CellarHQConfig getCellarHQConfig() {
    remote.exec { get(CellarHQConfig) }
  }

  protected String getJdbcUrl() {
    "jdbc:postgresql://${cellarHQConfig.databaseServerName}:${cellarHQConfig.databasePortNumber}/" +
      "${cellarHQConfig.databaseName}?user=${cellarHQConfig.databaseUser}&password=${cellarHQConfig.databasePassword}"
  }

  EmailAccount anEmailAccountUser(RemoteControl remote, String screenName, String email, String password) {
    remote.exec {
      Cellar cellar = new Cellar(screenName: screenName, displayName: screenName)
      EmailAccount emailAccount = new EmailAccount(email: email, password: password)
      emailAccount.cellar = cellar
      get(AccountService).create(emailAccount, null)
    }
  }

  void cleanUpUsers(RemoteControl remote) {
    remote.exec {
        Sql sql = new Sql(get(DataSource))
        sql.execute('delete from account_link_request where 1=1')
        sql.execute('delete from account_email where 1=1')
        sql.execute('delete from cellar where 1=1')
        sql.close()
    }
  }
}
