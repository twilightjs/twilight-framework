package com.twilight.server.dispatcher.handlers;

import com.twilight.server.handler.Handler;
import com.twilight.server.handler.Manager;
import com.twilight.tools.parsers.http.HttpParserM;

public class HandlerRequestRequestMediator extends HandlerRequestImplementation implements HandlerRequest {

    public static HandlerRequest createHandler() {
        return HandlerRequestImplementation.createHandler();
    }

    @Override
    public Handler checkForMatches(Manager manager, HttpParserM httpParserM) {
        return super.checkForMatches(manager, httpParserM);
    }
}
