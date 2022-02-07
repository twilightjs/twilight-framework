package org.twilightframework.http.server;

import org.twilightframework.http.handler.Handler;
import org.twilightframework.http.server.io.InputOutputExchanger;

public interface NotificationBehavior {

    public void notifyHandler(Handler handler, InputOutputExchanger inputOutputExchanger);

}
