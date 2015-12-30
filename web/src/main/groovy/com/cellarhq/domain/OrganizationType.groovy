package com.cellarhq.domain

public enum OrganizationType {
  BREWERY('brewery'),
  DISTILLERY('distillery'),
  WINERY('winery')

  private final String value

  OrganizationType(String value) {
    this.value = value
  }

  String toString() {
    return value
  }
}
