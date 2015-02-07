package com.cellarhq

import com.google.inject.Inject

import static ratpack.handlebars.Template.handlebarsTemplate

import com.cellarhq.util.LogUtil
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.codehaus.groovy.runtime.StackTraceUtils
import ratpack.error.ServerErrorHandler
import ratpack.handling.Context

/**
 * Standard ErrorHandler for the application.
 */
@Slf4j
@CompileStatic
class ServerErrorHandlerImpl implements ServerErrorHandler {

    private final boolean isProduction

    @Inject
    ServerErrorHandlerImpl(CellarHQConfig config) {
        isProduction = config.isProductionEnv()
    }


    @Override
    void error(Context context, Throwable throwable) throws Throwable {
        log.error(LogUtil.toLog(context.request, 'ServerError', [
                requestPath: context.request.path,
                method: context.request.method,
                msg: throwable.message
        ]), throwable)

        context.with {
            String correlationId = request.maybeGet(UUID).map { it.toString() }

            if (isProduction) {
                render handlebarsTemplate('error.html',
                        title: 'Error',
                        pageId: 'server.error',
                        correlationId: correlationId)
            } else {
                Throwable sanitized = StackTraceUtils.deepSanitize(throwable)
                Throwable rootCause = StackTraceUtils.extractRootCause(throwable)
                render handlebarsTemplate('error-development.html',
                        title: 'Exception',
                        pageId: 'server.error',
                        exception: throwable,
                        development: !isProduction,
                        request: context.request,
                        rootCauseMessage: rootCause.message,
                        rootCauseTrace: rootCause.stackTrace.collect {
                            [trace: it.toString()]
                        },
                        sanitizedMessage: sanitized.message,
                        sanitizedTrace: sanitized.stackTrace.collect {
                            [trace: it.toString()]
                        },
                        correlationId: correlationId)
            }
        }
    }
}
