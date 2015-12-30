package com.cellarhq.common

import com.cellarhq.util.LogUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.codehaus.groovy.runtime.StackTraceUtils
import ratpack.error.ServerErrorHandler
import ratpack.handling.Context
import ratpack.handling.RequestId

import static ratpack.handlebars.Template.handlebarsTemplate

/**
 * Standard ErrorHandler for the application.
 */
@Slf4j
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
      method     : context.request.method,
      msg        : throwable.message
    ]), throwable)

    context.with {
      Optional<RequestId> uuid = request.maybeGet(RequestId)
      String correlationId = uuid.isPresent() ? uuid.get().id : 'UNKNOWN'

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
