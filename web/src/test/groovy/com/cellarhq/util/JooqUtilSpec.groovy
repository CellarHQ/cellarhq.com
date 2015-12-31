package com.cellarhq.util

import com.cellarhq.generated.Tables
import org.jooq.Field
import spock.lang.Specification

class JooqUtilSpec extends Specification {

  def 'fields except filters a list of fields'() {
    given:
    Field[] allFields = [
      Tables.CELLAR.ID,
      Tables.CELLAR.SCREEN_NAME,
      Tables.CELLAR.CREATED_DATE,
      Tables.CELLAR.MODIFIED_DATE
    ]
    List<Field> exceptFields = [
      Tables.CELLAR.SCREEN_NAME
    ]

    when:
    Field[] result = JooqUtil.fieldsExcept(allFields, exceptFields)

    then:
    noExceptionThrown()
    assert [Tables.CELLAR.ID, Tables.CELLAR.CREATED_DATE, Tables.CELLAR.MODIFIED_DATE] == result
  }

  def 'fields except listed filters merges with another array of fields'() {
    given:
    Field[] allFields = [
      Tables.CELLAR.ID,
      Tables.CELLAR.SCREEN_NAME
    ]
    List<Field> exceptFields = [
      Tables.CELLAR.SCREEN_NAME
    ]
    Field[] mergeIntoFields = [
      Tables.ACCOUNT_EMAIL.ID,
      Tables.ACCOUNT_EMAIL.EMAIL
    ]

    when:
    Field[] result = JooqUtil.fieldsExcept(allFields, exceptFields, mergeIntoFields)

    then:
    noExceptionThrown()
    assert [
      Tables.ACCOUNT_EMAIL.ID,
      Tables.ACCOUNT_EMAIL.EMAIL,
      Tables.CELLAR.ID] == result

  }
}
