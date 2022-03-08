package org.twilightframework.http.servlet.response;


import org.twilightframework.http.server.io.InputOutputExchanger;
import org.twilightframework.http.servlet.components.builder.ResponseBuilder;
import org.twilightframework.http.servlet.components.builder.ResponseBuilderImplementation;

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
    public ResponseBuilder builder() {
        return new ResponseBuilderImplementation();
    }
}
