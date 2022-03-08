package org.twilightframework.http.servlet.response;

import org.twilightframework.http.server.io.InputOutputExchanger;
import org.twilightframework.http.servlet.components.builder.ResponseBuilder;

public abstract class HttpResponse {

    protected InputOutputExchanger inputOutputExchanger;

    public HttpResponse(InputOutputExchanger inputOutputExchanger) {
        this.inputOutputExchanger = inputOutputExchanger;
    }

    public abstract void write(ResponseBuilder responseBuilder);

    public abstract ResponseBuilder builder();
}
