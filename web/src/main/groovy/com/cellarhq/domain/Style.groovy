package com.cellarhq.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
import org.jooq.JSON

@CompileStatic
@InheritConstructors
class Style extends com.cellarhq.generated.tables.pojos.Style {

  Style() {
    searchable = true
  }

  @Override
  @JsonIgnore
  JSON getData() {
    return data
  }
}
