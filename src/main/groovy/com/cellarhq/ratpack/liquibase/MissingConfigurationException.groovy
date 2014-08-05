package com.cellarhq.ratpack.liquibase

/**
 * Thrown when a required configuration property has not been set.
 */
class MissingConfigurationException extends RuntimeException {

    MissingConfigurationException(String message) {
        super(message)
    }
}
