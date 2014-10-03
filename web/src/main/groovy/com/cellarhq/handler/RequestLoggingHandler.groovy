package com.cellarhq.handler

import groovy.util.logging.Slf4j
import ratpack.func.Action
import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.handling.RequestOutcome
import ratpack.http.Request
import ratpack.http.SentResponse

@Slf4j
class RequestLoggingHandler implements Handler {
    private final Handler rest

    public RequestLoggingHandler(Handler rest) {
        this.rest = rest
    }

    @SuppressWarnings('VariableName')
    @Override
    void handle(Context context) throws Exception {
        Request request = context.request

        context.onClose(new Action<RequestOutcome>() {
            public void execute(RequestOutcome thing) throws Exception {
                log.info(getRequestLogEntry(context, request, thing.response))
            }
        })
        context.insert(rest)
    }

    private String getRequestLogEntry(Context context, Request request, SentResponse response) {
        return "${getRequestIP(request)} ${request.method} /${request.path} ${response.status.code}"
    }

    private String getRequestIP(Request request) {
        request.headers['X-FORWARDED-FOR'] ?: 'UNKNOWN'
    }
}
