package com.cellarhq

/**
 * Helper methods for Spec IgnoreIf annotations.
 */
abstract class SpecFlags {
    final static String NO_FUNCTIONAL = 'noFunc'
    final static String NO_INTERNET = 'noInternet'

    static boolean has(String flag) {
        return System.getProperty(flag) != null
    }

    static boolean isTrue(String flag) {
        return System.getProperty(flag).asBoolean()
    }

    static boolean isFalse(String flag) {
        return has(flag) && !isTrue(flag)
    }

    static boolean missingTwitter() {
        return System.getenv('TWITTER_API_TOKEN') != 'YOUR_TWITTER_API_TOKEN' &&
                System.getenv('TWITTER_API_SECRET') != 'YOUR_TWITTER_API_SECRET'
    }
}
