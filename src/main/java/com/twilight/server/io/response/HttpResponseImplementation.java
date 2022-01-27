package com.twilight.server.io.response;

import com.twilight.server.http.io.InputOutputExchanger;
import com.twilight.server.io.components.builder.ResponseBuilder;
import com.twilight.server.io.components.builder.ResponseBuilderImplementation;

import java.nio.charset.StandardCharsets;

public class HttpResponseImplementation extends HttpResponse {
    public HttpResponseImplementation(InputOutputExchanger inputOutputExchanger) {
        super(inputOutputExchanger);
    }

    @Override
    public void write(ResponseBuilder responseBuilder) {
        System.out.println(inputOutputExchanger.getRequest());
        this.inputOutputExchanger.write(responseBuilder.getResponse().getBytes(StandardCharsets.UTF_8), responseBuilder.getBody());
        this.inputOutputExchanger.close();
    }

    @Override
    public ResponseBuilder getResponseBuilder() {
        return new ResponseBuilderImplementation();
    }
}