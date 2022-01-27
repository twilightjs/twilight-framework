package com.twilight.server.io.request;

import com.twilight.server.http.io.InputOutputExchanger;
import com.twilight.tools.parsers.http.HttpParser;

public class HttpRequestImplementation extends HttpRequest {
    public HttpRequestImplementation(InputOutputExchanger inputOutputExchanger) {
        super(inputOutputExchanger);
    }

    public HttpParser getHttpParser() {
        return new HttpParser(this.inputOutputExchanger.read());
    }
}
