package org.twilightframework.http.handler;

import org.twilightframework.http.servlet.components.codes.HttpCodes;
import org.twilightframework.http.servlet.request.HttpRequest;
import org.twilightframework.http.servlet.response.HttpResponse;

public class DefaultHandlerTwilight implements Handler {
    @Override
    public void handleRequest(HttpRequest request, HttpResponse response) {
        System.out.println(request.getHttpParser().getRequest());
        response.write(response.builder()
                .setStatusCode(HttpCodes.NOT_FOUND, "NOT FOUND")
                .setHeader("Connection", "close")
                .build());
    }
}
