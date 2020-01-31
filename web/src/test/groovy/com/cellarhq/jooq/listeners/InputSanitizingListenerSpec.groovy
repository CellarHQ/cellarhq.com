package com.cellarhq.jooq.listeners

import com.cellarhq.generated.tables.records.ActivityRecord
import com.cellarhq.generated.tables.records.CellarRecord
import org.jooq.ExecuteType
import spock.lang.Specification
import spock.lang.Unroll

class InputSanitizingListenerSpec extends Specification {

  def 'record has flagged input sanitized'() {
    given:
    CellarRecord record = new CellarRecord().with { CellarRecord self ->
      bio = 'Hello!<script type="text/javascript">window.location = "somewhere-bad";</script>'
      displayName = '<strong>My name!</strong>'
      screenName = '<li>Unsanitized</li>'
      return self
    }

    when:
    InputSanitizingListener.instance.sanitizeRecord(record)

    then:
    noExceptionThrown()
    assert record.bio == 'Hello!'
    assert record.displayName == '<strong>My name!</strong>'
    assert record.screenName == '<li>Unsanitized</li>'
  }

  def 'CellarRecord is sanitized'() {
    expect:
    InputSanitizingListener.instance.shouldSanitizeRecord(new CellarRecord())
  }

  def 'ActivityRecord is not sanitized'() {
    expect:
    !InputSanitizingListener.instance.shouldSanitizeRecord(new ActivityRecord())
  }

  @Unroll("'#executeType' #description")
  def 'only write events should be processed'() {
    when:
    boolean result = InputSanitizingListener.instance.shouldProcessEvent(executeType)

    then:
    noExceptionThrown()
    assert result == shouldProcess

    where:
    executeType         | shouldProcess
    ExecuteType.READ    | false
    ExecuteType.WRITE   | true
    ExecuteType.DDL     | false
    ExecuteType.BATCH   | false
    ExecuteType.ROUTINE | false
    ExecuteType.OTHER   | false

    description = shouldProcess ? 'should be processed' : 'should not be processed'
  }
}
