package com.cellarhq.handlers;

import java.util.UUID;
import ratpack.handling.Context;
import ratpack.handling.Handler;

public class CorrelationIdHandler implements Handler {

    private final Handler rest;

    public CorrelationIdHandler(Handler rest) {
        this.rest = rest;
    }

    @Override
    public void handle(final Context context) throws Exception {
        context.getRequest().add(UUID.class,  UUID.randomUUID());
        context.insert(rest);
    }
}
