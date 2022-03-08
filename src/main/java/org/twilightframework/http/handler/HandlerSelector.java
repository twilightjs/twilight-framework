package org.twilightframework.http.handler;


import org.twilightframework.http.handler.annotation.DefaultListener;
import org.twilightframework.http.handler.annotation.HttpMethod;
import org.twilightframework.http.handler.annotation.Listener;
import org.twilightframework.http.server.io.InputOutputExchanger;
import org.twilightframework.http.servlet.request.HttpRequest;
import org.twilightframework.http.servlet.response.HttpResponse;
import org.twilightframework.tools.parsers.http.HttpParser;
import org.twilightframework.tools.parsers.uri.URI;

import java.util.List;

public class HandlerSelector {
    private List<Handler> handlers;
    private HttpParser parser;

    public HandlerSelector(List<Handler> handlers, InputOutputExchanger inputOutputExchanger) {
        this.handlers = handlers;
        this.parser = new HttpParser(inputOutputExchanger.getRequest());
    }

    public Handler select() {
        return handlers.stream()
                .filter(this::isHandler)
                .findAny()
                .orElse(handlers.stream()
                        .filter(this::isDefaultHandler)
                        .findAny()
                        .orElse(new DefaultHandlerTwilight()));
    }

    private boolean isHandler(Handler handler) {
        return (getListenerAnnotation(handler) != null &&
                isPathEquals(this.parser.getURI(), getListenerAnnotation(handler)) &&
                isMethodEquals(getHttpMethodAnnotation(handler)));
    }

    private boolean isDefaultHandler(Handler handler) {
        return (getDefaultListener(handler) != null && isMethodEquals(getHttpMethodAnnotation(handler)) ||
                getDefaultListener(handler) != null && getHttpMethodAnnotation(handler).value().get().equals("ALL"));
    }


    private DefaultListener getDefaultListener(Handler handler) {
        return handler.getClass().getAnnotation(DefaultListener.class);
    }

    private boolean isPathEquals(URI uri, Listener listener) {
//        System.out.println("Path: " + uri.getPath());
//        System.out.println("Listener: " + listener.uri());
//        System.out.println("Equals: " + uri.getPath().equals(listener.uri()));
        return uri.getPath().equals(listener.value());
    }

    private Listener getListenerAnnotation(Handler handler) {
        return handler.getClass().getAnnotation(Listener.class);
    }

    private boolean isMethodEquals(HttpMethod method) {
        return parser.getMethod().equals(method.value().get().toUpperCase());
    }

    private HttpMethod getHttpMethodAnnotation(Handler handler) {
        HttpMethod method = null;
        try {
            method = handler.getClass().getMethod("handleRequest", HttpRequest.class, HttpResponse.class).getAnnotation(HttpMethod.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        isHttpMethod(method);
        return method;
    }

    private void isHttpMethod(HttpMethod method) {
        if (method == null)
            throw new IllegalArgumentException("The handler did not specify an http method to listen on, specify @AlternativeHttpMethod or select an existing one from HttpMethods.");
    }
}
