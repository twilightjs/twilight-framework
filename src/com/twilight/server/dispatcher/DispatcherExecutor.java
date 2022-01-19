package com.twilight.server.dispatcher;

import com.twilight.server.handler.Handler;
import com.twilight.server.handler.Manager;

public class DispatcherExecutor extends DispatcherExecutorImplementation implements Dispatcher {

    protected DispatcherExecutor(Manager manager, String request) {
        super(manager, request);
    }

    public static Dispatcher createDispatcher(Manager manager, String request) {
        return DispatcherExecutorImplementation.createDispatcher(manager, request);
    }

    @Override
    public Handler defineExecutor() {
        return super.defineExecutor();
    }

    @Override
    public void invokeDoExecute(Handler handler) {
        super.invokeDoExecute(handler);
    }
}
