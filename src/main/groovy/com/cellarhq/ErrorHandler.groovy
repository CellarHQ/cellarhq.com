package com.cellarhq

import static ratpack.groovy.Groovy.groovyMarkupTemplate

import groovy.util.logging.Slf4j
import org.codehaus.groovy.runtime.StackTraceUtils
import ratpack.error.ServerErrorHandler
import ratpack.handling.Context

/**
 * Standard ErrorHandler for the application.
 *
 * @todo Add environments support; production should not be rendering out an exception.
 */
@Slf4j
class ErrorHandler implements ServerErrorHandler {

    @Override
    void error(Context context, Exception exception) throws Exception {
        log.error('Caught error', exception)
        context.with {
            render groovyMarkupTemplate('error.gtpl',
                    title: 'Exception',
                    exception: exception,
                    sanitizedException: StackTraceUtils.deepSanitize(exception))
        }
    }
}
