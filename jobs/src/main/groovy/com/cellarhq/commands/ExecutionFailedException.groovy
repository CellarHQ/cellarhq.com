package com.cellarhq.commands

import groovy.transform.CompileStatic

@CompileStatic
class ExecutionFailedException extends RuntimeException {

    ExecutionFailedException(String message) {
        super(message)
    }
}
