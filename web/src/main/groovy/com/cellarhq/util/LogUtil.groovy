package com.cellarhq.util

import groovy.util.logging.Slf4j
import ratpack.http.Request

@Slf4j
abstract class LogUtil {

    static enum Level {
        TRACE('trace'),
        DEBUG('debug'),
        INFO('info'),
        WARN('warn'),
        ERROR('error')

        final String value

        Level(String value) {
            this.value = value
        }

        String toString() {
            return value
        }
    }

    static String toLog(Request request, String key, Map properties = [:]) {
        String correlationId = request.get(UUID).toString()
        return "KEY=${key}, CORRELATIONID=${correlationId} " + properties.collect { "${it.key}=${it.value}" }.join(', ')
    }

    static String toLog(String key, Map properties = [:]) {
        return "KEY=${key}, " + properties.collect { "${it.key}=${it.value}" }.join(', ')
    }

    static <T> T withPerformance(String name, Level level, Closure<T> op) {
        long start = System.currentTimeMillis()

        T result = op.call()

        log."${level}"(toLog('Performance', [
                name: name,
                elapsedMs: System.currentTimeMillis() - start
        ]))

        return result
    }

    static <T> T withPerformance(String name, Closure<T> op) {
        return withPerformance(name, Level.DEBUG, op)
    }
}
