package com.cellarhq.util

abstract class LogUtil {

    static String toLog(String key, Map properties) {
        return "KEY=${key} " + properties.collect { "${it.key}=${it.value}" }.join(' ')
    }
}
