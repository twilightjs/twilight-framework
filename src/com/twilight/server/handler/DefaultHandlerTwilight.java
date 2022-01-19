package com.twilight.server.handler;

import com.twilight.server.io.components.builder.Http;
import com.twilight.server.io.components.builder.ResponseBuilder;
import com.twilight.server.io.response.HttpResponse;
import com.twilight.server.io.request.HttpRequest;

public class DefaultHandlerTwilight implements Handler {
    @Override
    public void handleRequest(HttpRequest request, HttpResponse response) {
        System.out.println(request.getHttpParser().getRequest());
        response.write(response.getResponseBuilder()
                .setStatusCode(Http.HttpCodes.NOT_FOUND, "NOT FOUND")
                .setHeader("Connection", "close")
                .build());
    }
}
