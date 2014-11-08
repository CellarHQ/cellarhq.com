package com.cellarhq.support.userinput

import groovy.transform.CompileStatic

@CompileStatic
class BooleanPromptType implements PromptType {

    final static String YES = 'y'
    final static String NO = 'n'

    final String hint = 'y/n'
    final Map<String, Class> allowedValues = [
            y: Boolean,
            n: Boolean
    ]
}
