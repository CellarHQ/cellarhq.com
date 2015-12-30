package com.cellarhq.domain

public enum Availability {
  LIMITED('limited'),
  ONE_TIME('one time'),
  SEASONAL('seasonal'),
  SEASONAL_SPRING('seasonal spring'),
  SEASONAL_SUMMER('seasonal summer'),
  SEASONAL_FALL('seasonal fall'),
  SEASONAL_WINTER('seasonal winter'),
  YEAR_ROUND('year round')

  private final String value

  Availability(String value) {
    this.value = value
  }

  String toString() {
    return value
  }

  // Groovy complains about not being able to apply the category method.
  @SuppressWarnings('UnnecessaryCollectCall')
  static List<Map<String, String>> toHandlebars() {
    return values().collect {
      String val = it.toString()
      return [
        value: val,
        label: val.tokenize().collect { it.capitalize() }.join(' ')
      ]
    }
  }
}
