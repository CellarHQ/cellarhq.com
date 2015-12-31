package com.cellarhq.support.userinput

import groovy.transform.CompileStatic

@CompileStatic
class ContinuePromptType implements PromptType {

  final String hint = 'enter'
  final Map<String, Class> allowedValues = [:]
}
