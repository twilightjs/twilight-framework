package com.twilight.server.handler;

import com.twilight.server.io.request.HttpRequest;
import com.twilight.server.io.response.HttpResponse;

public interface Handler {
    public void handleRequest(HttpRequest request, HttpResponse response);
}
