package com.twilight.server.io.response;

import com.twilight.server.http.io.InputOutputExchanger;
import com.twilight.server.io.components.builder.ResponseBuilder;
import com.twilight.server.io.components.builder.ResponseBuilderImplementation;

public abstract class HttpResponse {

    protected InputOutputExchanger inputOutputExchanger;

    public HttpResponse(InputOutputExchanger inputOutputExchanger) {
        this.inputOutputExchanger = inputOutputExchanger;
    }

    public abstract void write(ResponseBuilder responseBuilder);

    public abstract ResponseBuilder getResponseBuilder();
}
