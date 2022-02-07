package org.twilightframework.http.handler;

import org.twilightframework.http.servlet.request.HttpRequest;
import org.twilightframework.http.servlet.response.HttpResponse;

public interface Handler {
    public void handleRequest(HttpRequest request, HttpResponse response);
}
