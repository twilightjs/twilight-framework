package org.twilightframework.http.servlet.request;

import org.twilightframework.http.server.io.InputOutputExchanger;
import org.twilightframework.tools.parsers.http.HttpParser;

public abstract class HttpRequest {
    protected InputOutputExchanger inputOutputExchanger;

    public HttpRequest(InputOutputExchanger inputOutputExchanger) {
        this.inputOutputExchanger = inputOutputExchanger;
    }

    public abstract HttpParser getHttpParser();
}
