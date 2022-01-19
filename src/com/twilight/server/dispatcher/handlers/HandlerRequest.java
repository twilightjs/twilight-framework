package com.twilight.server.dispatcher.handlers;

import com.twilight.server.handler.Handler;
import com.twilight.server.handler.Manager;
import com.twilight.tools.parsers.http.HttpParserM;

public interface HandlerRequest {
    public Handler checkForMatches(Manager manager, HttpParserM httpParserM);
}
