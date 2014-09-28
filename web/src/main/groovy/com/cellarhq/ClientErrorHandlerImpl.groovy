package com.cellarhq

import static ratpack.handlebars.Template.handlebarsTemplate
import static ratpack.jackson.Jackson.json

import com.cellarhq.util.LogUtil
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import ratpack.error.ClientErrorHandler
import ratpack.handling.Context
import ratpack.http.Request

@Slf4j
@CompileStatic
class ClientErrorHandlerImpl implements ClientErrorHandler {

    private class ClientErrorMessages {
        String headTitle
        String pageTitle

        ClientErrorMessages(Map<String, String> messages) {
            headTitle = messages?.headTitle
            pageTitle = messages?.pageTitle
        }
    }

    @Override
    void error(Context context, int statusCode) throws Exception {
        ClientErrorMessages messages = getStatusCodeContent(context.request, statusCode)
        context.with {
            response.status(statusCode)
            if (request.headers.get('Accept').contains('application/json')) {
                render json([
                        title: message.headTitle,
                        message: messages.pageTitle
                ])
            } else {
                render handlebarsTemplate('client-error.html',
                        title: messages.headTitle,
                        statusCode: statusCode,
                        message: messages.pageTitle,
                        pageId: 'client.error')
            }
        }
    }

    private ClientErrorMessages getStatusCodeContent(Request request, int statusCode) {
        switch (statusCode) {
            case 400:
                log.warn(LogUtil.toLog('ClientError', [
                        statusCode: statusCode,
                        method: request.method,
                        uri: request.uri
                ]))
                return new ClientErrorMessages(
                        headTitle: 'Bad request',
                        pageTitle: 'Request cannot be fulfilled due to bad syntax'
                )

            case 401:
                return new ClientErrorMessages(
                        headTitle: 'Authentication failed',
                        pageTitle: 'We were unable to authenticate your request. Log in or try again.'
                )

            case 404:
                log.warn(LogUtil.toLog('ClientError', [
                        statusCode: statusCode,
                        method: request.method,
                        uri: request.uri
                ]))
                return new ClientErrorMessages(
                        headTitle: 'Page not found',
                        pageTitle: "We're lost!"
                )

            default:
                log.warn(LogUtil.toLog('ClientError', [
                        statusCode: statusCode,
                        method: request.method,
                        uri: request.uri,
                        msg: 'ClientErrorHandler missing content for status code: Using generic messages'
                ]))
                return new ClientErrorMessages(
                        headTitle: 'Client error',
                        pageTitle: 'Oops! Looks like something went wrong'
                )
        }
    }
}
