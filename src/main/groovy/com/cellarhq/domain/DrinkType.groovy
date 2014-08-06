package com.cellarhq.domain

/**
 * The greatest enum of all.
 */
public enum DrinkType {
    BEER('beer'),
    LIQUOR('liquor'),
    WINE('wine')

    private final value

    DrinkType(String value) {
        this.value = value
    }

    String toString() {
        return value
    }
}
