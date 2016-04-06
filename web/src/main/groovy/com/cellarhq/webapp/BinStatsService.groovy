package com.cellarhq.webapp

import com.cellarhq.domain.CellaredDrink
import groovy.transform.CompileStatic

/**
 * Calculates the number of drinks in each bin from a list of drinks
 */
@CompileStatic
class BinStatsService {
  BinStats calculateBinStats(List<CellaredDrink> drinks) {
    List bins = drinks.binIdentifier
    List uniqueBins = drinks.binIdentifier.unique()

    BinStats stats = uniqueBins.inject(new BinStats()) { BinStats stats, String value ->
      stats[value] = bins.count(value) as Integer
      return stats
    } as BinStats

    //special case for null and empty string, combine then and make the key "Unknown"
    Integer emptyStringCount = stats.remove('') ?: 0
    Integer nullCount = stats.remove(null) ?: 0

    if (emptyStringCount + nullCount > 0) {
      stats['Unknown'] = emptyStringCount + nullCount
    }

    return stats
  }

  boolean binsPresent(List<CellaredDrink> cellaredDrinks) {
    cellaredDrinks*.binIdentifier.find { it }
  }
}

class BinStats extends HashMap<String, Integer> {
  Set<Map.Entry> getEntries() {
    this.entrySet()
  }
}
