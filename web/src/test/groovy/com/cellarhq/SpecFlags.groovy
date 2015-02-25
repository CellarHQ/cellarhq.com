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
        return System.getenv('ratpack.twitterApiKey') != 'YOUR_TWITTER_API_TOKEN' &&
                System.getenv('ratpack.twitterApiSecret') != 'YOUR_TWITTER_API_SECRET'
    }
}
