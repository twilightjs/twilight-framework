package com.twilight.server.handler;

import com.twilight.server.handler.exception.ExecutorNotFound;

import java.util.List;

public class HandlerManager extends HandlerManagerImplementation implements Manager {
    protected HandlerManager(List<Handler> handlerList) {
        super(handlerList);
    }

    public static Manager registerHandlers(Handler... handlers) throws ExecutorNotFound {
        return HandlerManagerImplementation.registerHandlers(handlers);
    }

    @Override
    public List<Handler> getHttpExecutorList() {
        return super.getHttpExecutorList();
    }
}
