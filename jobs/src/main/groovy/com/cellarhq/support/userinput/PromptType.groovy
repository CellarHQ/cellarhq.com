package com.cellarhq.support.userinput

import groovy.transform.CompileStatic

@CompileStatic
interface PromptType {

    static PromptType BOOLEAN = new BooleanPromptType()
    static PromptType CONTINUE = new ContinuePromptType()
    static PromptType TEXT = new TextPromptType()

    String getHint()
    Map<String, Class> getAllowedValues()

}
