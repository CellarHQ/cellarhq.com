package com.cellarhq

import static ratpack.handlebars.Template.handlebarsTemplate


import groovy.transform.CompileStatic
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
@CompileStatic
class ErrorHandler implements ServerErrorHandler {

    @Override
    void error(Context context, Throwable throwable) throws Throwable {
        log.error('Caught error', throwable)
        context.with {
            render handlebarsTemplate('error.html',
                    title: 'Exception',
                    pageId: 'server.error',
                    exception: throwable,
                    sanitizedException: StackTraceUtils.deepSanitize(throwable))
        }

    }
}
