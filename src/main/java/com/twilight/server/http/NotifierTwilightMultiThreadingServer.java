package com.twilight.server.http;

import com.twilight.server.handler.Handler;
import com.twilight.server.http.io.InputOutputExchanger;
import com.twilight.server.io.request.HttpRequestImplementation;
import com.twilight.server.io.response.HttpResponseImplementation;

public class NotifierTwilightMultiThreadingServer implements NotificationBehavior {
    @Override
    public void notifyHandler(Handler handler, InputOutputExchanger inputOutputExchanger) {
        handler.handleRequest(new HttpRequestImplementation(inputOutputExchanger), new HttpResponseImplementation(inputOutputExchanger));
    }
}
