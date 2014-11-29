package com.cellarhq.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.func.Action;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.handling.RequestOutcome;
import ratpack.http.Request;
import ratpack.http.SentResponse;
import ratpack.registry.NotInRegistryException;

import java.util.UUID;

public class RequestLoggingHandler implements Handler {
    private final Handler rest;
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestLoggingHandler.class);

    public RequestLoggingHandler(Handler rest) {
        this.rest = rest;
    }

    @Override
    public void handle(Context context) throws Exception {
        Request request = context.getRequest();

        context.onClose(new Action<RequestOutcome>() {
            public void execute(RequestOutcome thing) throws Exception {
                LOGGER.info(getRequestLogEntry(context, request, thing.getResponse()));
            }
        });
        context.insert(rest);
    }

    private String getRequestLogEntry(Context context, Request request, SentResponse response) {
        StringBuilder sb = new StringBuilder();
        sb.append(request.getMethod().toString());
        sb.append(" ");
        sb.append(request.getPath());
        sb.append(" ");
        sb.append(response.getStatus().getCode());

        try {
            UUID potentialUUID = request.get(UUID.class);
            if (potentialUUID != null) {
                sb.append(" correlationId=");
                sb.append(potentialUUID.toString());
            }
        } catch (NotInRegistryException e) {
            LOGGER.debug("No UUID in the registry");
        }

        return sb.toString();
    }
}
