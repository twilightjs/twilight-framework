package org.twilightframework.http.server.notification;

import org.twilightframework.http.handler.Handler;
import org.twilightframework.http.server.io.InputOutputExchanger;

public interface NotificationBehavior {

    void notifyHandler(Handler handler, InputOutputExchanger inputOutputExchanger);

}
