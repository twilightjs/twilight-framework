package com.twilight.server.dispatcher;

import com.twilight.server.handler.Handler;

public interface Dispatcher {
    public Handler defineExecutor();

    public void invokeDoExecute(Handler handler);
}
