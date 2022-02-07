package org.twilightframework.http.handler;


import org.twilightframework.http.handler.annotation.DefaultListener;
import org.twilightframework.http.handler.annotation.HttpMethod;
import org.twilightframework.http.handler.annotation.Listener;
import org.twilightframework.http.server.io.InputOutputExchanger;
import org.twilightframework.http.servlet.request.HttpRequest;
import org.twilightframework.http.servlet.response.HttpResponse;
import org.twilightframework.tools.parsers.http.HttpParser;
import org.twilightframework.tools.parsers.uri.URI;

import java.util.ArrayList;

public class HandlerSelector {
    private ArrayList<Handler> handlers;
    private HttpParser parser;

    public HandlerSelector(ArrayList<Handler> handlers, InputOutputExchanger inputOutputExchanger) {
        this.handlers = handlers;
        this.parser = new HttpParser(inputOutputExchanger.getRequest());
    }

    public Handler select() {
        Handler definedHandler = new DefaultHandlerTwilight();
        for (Handler handler : handlers) {
            if (isDefaultExecutor(handler)) {
                definedHandler = handler;
            }
            if (isExecutor(handler)) {
                definedHandler = handler;
                break;
            }
        }
        return definedHandler;
    }

    private boolean isDefaultExecutor(Handler handler) {
        boolean isDefaultExecutor = false;
        try {
            isDefaultExecutor = (getDefaultListener(handler) != null && isMethodEquals(getHttpMethodAnnotation(handler)) ||
                    getDefaultListener(handler) != null && getHttpMethodAnnotation(handler).method().equals("ALL"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return isDefaultExecutor;
    }

    private boolean isExecutor(Handler handler) {
        boolean isExecutor = false;
        try {
            isExecutor = getListenerAnnotation(handler) != null && isPathEquals(this.parser.getURI(), getListenerAnnotation(handler)) && isMethodEquals(getHttpMethodAnnotation(handler));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return isExecutor;
    }

    private DefaultListener getDefaultListener(Handler handler) {
        return handler.getClass().getAnnotation(DefaultListener.class);
    }

    private boolean isPathEquals(URI uri, Listener listener) {
//        System.out.println("Path: " + uri.getPath());
//        System.out.println("Listener: " + listener.uri());
//        System.out.println("Equals: " + uri.getPath().equals(listener.uri()));
        return uri.getPath().equals(listener.uri());
    }

    private Listener getListenerAnnotation(Handler handler) {
        return handler.getClass().getAnnotation(Listener.class);
    }

    private boolean isMethodEquals(HttpMethod method) {
        return this.parser.getMethod().equals(method.method().toUpperCase());
    }

    private HttpMethod getHttpMethodAnnotation(Handler handler) throws NoSuchMethodException {
        return handler.getClass().getMethod("handleRequest", HttpRequest.class, HttpResponse.class).getAnnotation(HttpMethod.class);
    }

}
