package com.cellarhq.support.userinput

import groovy.transform.CompileStatic

@CompileStatic
class TextPromptType implements PromptType {

  final String hint = null
  final Map<String, Class> allowedValues = [:]
}
