package org.twilightframework.http.server.notification;

import org.twilightframework.http.handler.Handler;
import org.twilightframework.http.server.io.InputOutputExchanger;
import org.twilightframework.http.servlet.request.HttpRequestImplementation;
import org.twilightframework.http.servlet.response.HttpResponseImplementation;

public class NotifierTwilightBlockingServer implements NotificationBehavior {
    @Override
    public void notifyHandler(Handler handler, InputOutputExchanger inputOutputExchanger) {
        handler.handleRequest(new HttpRequestImplementation(inputOutputExchanger), new HttpResponseImplementation(inputOutputExchanger));
    }
}
