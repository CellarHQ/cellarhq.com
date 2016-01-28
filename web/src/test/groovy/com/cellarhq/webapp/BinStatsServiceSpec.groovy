package com.cellarhq.webapp

import com.cellarhq.domain.CellaredDrink
import spock.lang.Specification

class BinStatsServiceSpec extends Specification {
  BinStatsService binStatsService = new BinStatsService()

  def "Builds map of single unique bin identifiers and the count of times it occur"() {
    given: 'a single drink in a bin'
    def drinks = [new CellaredDrink(binIdentifier: "3"),
                  new CellaredDrink(),
                  new CellaredDrink(binIdentifier: "1"),
                  new CellaredDrink(binIdentifier: "2"),
                  new CellaredDrink(binIdentifier: "1"),
                  new CellaredDrink(binIdentifier: "1"),
                  new CellaredDrink(binIdentifier: "2"),
                  new CellaredDrink(binIdentifier: ""),
                  new CellaredDrink()]

    expect:
    binStatsService.calculateBinStats(drinks) == [
      "1": 3,
      "2": 2,
      "Unknown": 3,
      "3": 1
    ]
  }

  def "can tell if a list of cellareddrinks has bin identifiers"() {
    expect:
    result == binStatsService.binsPresent(drinks)

    where:
    drinks | result
    [new CellaredDrink(binIdentifier: "")] | false
    [new CellaredDrink(binIdentifier: "1")]| true
    [new CellaredDrink(binIdentifier: "1"), new CellaredDrink(binIdentifier: "")]| true
    [] | false
    [new CellaredDrink()] | false
  }
}
