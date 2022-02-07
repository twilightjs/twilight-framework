package org.twilightframework.http.servlet.request;

import org.twilightframework.http.server.io.InputOutputExchanger;
import org.twilightframework.tools.parsers.http.HttpParser;

public class HttpRequestImplementation extends HttpRequest {
    public HttpRequestImplementation(InputOutputExchanger inputOutputExchanger) {
        super(inputOutputExchanger);
    }

    public HttpParser getHttpParser() {
        return new HttpParser(this.inputOutputExchanger.read());
    }
}
