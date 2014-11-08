package com.cellarhq.support.userinput

import groovy.transform.CompileStatic

@CompileStatic
class OptionPromptType implements PromptType {

    Map<String, Class> allowedValues

    String getHint() {
        return allowedValues.keySet().join(',')
    }
}
