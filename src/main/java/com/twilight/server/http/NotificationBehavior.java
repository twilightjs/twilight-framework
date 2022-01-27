package com.twilight.server.http;

import com.twilight.server.handler.Handler;
import com.twilight.server.http.io.InputOutputExchanger;

public interface NotificationBehavior {

    public void notifyHandler(Handler handler, InputOutputExchanger inputOutputExchanger);

}
