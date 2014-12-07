package com.cellarhq.auth.callbacks

import com.cellarhq.util.LogUtil
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.pac4j.core.exception.TechnicalException
import ratpack.handling.Context

import java.util.function.BiConsumer

@Slf4j
@CompileStatic
class AuthFailCallback<C extends Context, E extends Throwable> implements BiConsumer<C, E> {
    @Override
    void accept(C context, E throwable) {
        if (throwable.message == 'name cannot be blank')  {
            log.error(LogUtil.toLog(context.request, 'AuthenticationError', [
                    requestParams: context.request.queryParams,
                    requestPath: context.request.path,
                    rawUri: context.request.rawUri,
                    method: context.request.method,
                    msg: throwable.message]))

        }

        throw new TechnicalException('Failed to get user profile', throwable)

    }
}
