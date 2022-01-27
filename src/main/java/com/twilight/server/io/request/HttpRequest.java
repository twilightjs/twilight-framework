package com.twilight.server.io.request;

import com.twilight.server.http.io.InputOutputExchanger;
import com.twilight.tools.parsers.http.HttpParser;

public abstract class HttpRequest {
    protected InputOutputExchanger inputOutputExchanger;

    public HttpRequest(InputOutputExchanger inputOutputExchanger) {
        this.inputOutputExchanger = inputOutputExchanger;
    }

    public abstract HttpParser getHttpParser();
}
