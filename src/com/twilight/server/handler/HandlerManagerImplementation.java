package com.twilight.server.handler;

import com.twilight.server.handler.exception.ExecutorNotFound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class HandlerManagerImplementation {
    private List<Handler> handlerList;

    protected HandlerManagerImplementation(List<Handler> handlerList) {
        this.handlerList = handlerList;
    }

    protected static Manager registerHandlers(Handler... handlers) throws ExecutorNotFound {
        List<Handler> handlerArrayList = new ArrayList<>(Arrays.asList(handlers));
        isEmpty(handlerArrayList);
        return new HandlerManager(handlerArrayList);
    }

    protected List<Handler> getHttpExecutorList() {
        return handlerList;
    }

    private static void isEmpty(List<Handler> handlers) throws ExecutorNotFound {
        if (handlers.isEmpty()) {
            throw new ExecutorNotFound("Executor not found, specify new executors");
        }
    }
}
