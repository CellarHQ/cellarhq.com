package com.cellarhq

import org.jooq.tools.jdbc.MockConnection
import org.jooq.tools.jdbc.MockDataProvider
import org.jooq.tools.jdbc.MockExecuteContext
import org.jooq.tools.jdbc.MockResult
import spock.lang.Shared
import spock.lang.Specification

import java.sql.SQLException

abstract class JooqSpecification extends Specification {

  @Shared
  MockDataProvider provider
  @Shared
  MockConnection connection

  def setupSpec() {
    provider = createMockDataProvider()
    connection = new MockConnection(provider)
  }

  protected MockDataProvider createMockDataProvider() {
    return new MockDataProvider() {
      @Override
      MockResult[] execute(MockExecuteContext ctx) throws SQLException {
        return new MockResult[0]
      }
    }
  }
}
