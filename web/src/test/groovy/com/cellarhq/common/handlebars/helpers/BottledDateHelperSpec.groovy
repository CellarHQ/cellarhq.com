package com.cellarhq.common.handlebars.helpers

import com.github.jknack.handlebars.Options
import spock.lang.Specification

import java.time.LocalDate


class BottledDateHelperSpec extends Specification {
  BottledDateHelper bottledDateHelper = new BottledDateHelper()

  Options options

  def setup() {
    options = Mock(Options)
  }

  def 'first of year dates display year only'() {
    given: 'a date on the first of the year'
    LocalDate localDate = new LocalDate(2014, 1, 1)

    when: 'the helper is applied'
    String output = bottledDateHelper.apply(null, options)

    then: 'the output is just the year'
    1 * options.hash('date') >> localDate
    output == '2014'
  }

  def 'first of month displays the year and month'() {
    given: 'a date on the first of the year'
    LocalDate localDate = new LocalDate(2014, 3, 1)

    when: 'the helper is applied'
    String output = bottledDateHelper.apply(null, options)

    then: 'the output is just the year'
    1 * options.hash('date') >> localDate
    output == '2014-03'
  }

  def 'non first of month dates display entire date'() {
    given: 'a date on the first of the year'
    LocalDate localDate = new LocalDate(2014, 3, 2)

    when: 'the helper is applied'
    String output = bottledDateHelper.apply(null, options)

    then: 'the output is just the year'
    1 * options.hash('date') >> localDate
    output == '2014-03-02'
  }
}
