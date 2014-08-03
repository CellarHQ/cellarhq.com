package com.cellarhq.liquibase

/**
 * Thrown when Liquibase fails to complete successfully and fail on error is enabled.
 */
class FailedExecutionException extends RuntimeException {

    FailedExecutionException(Throwable e) {
        super(e)
    }
}
