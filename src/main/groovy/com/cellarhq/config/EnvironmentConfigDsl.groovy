package com.cellarhq.config

import groovy.transform.CompileStatic
import ratpack.launch.LaunchConfig

/**
 * Offers a couple simple DSL methods for supporting environment-specific configurations.
 */
@CompileStatic
abstract class EnvironmentConfigDsl {

    final static String DEFAULT_ENV = 'production'

    /**
     * Get a single configuration property. If it doesn't exist, it will fallback to the defaultValue.
     *
     * @param launchConfig
     * @param key
     * @param defaultValue
     * @return
     */
    static String envConfig(LaunchConfig launchConfig, String key, String defaultValue) {
        String env = System.getenv('env') ?: DEFAULT_ENV
        return launchConfig.getOther("${env}.${key}", defaultValue)
    }

    /**
     * Get a map of configuration properties from the provided key prefix.
     *
     * @param launchConfig
     * @param keyPrefix
     * @return
     */
    static Map<String, String> envPrefixConfig(LaunchConfig launchConfig, String keyPrefix) {
        String env = System.getenv('env') ?: DEFAULT_ENV
        return launchConfig.getOtherPrefixedWith("${env}.${keyPrefix}.")
    }
}
