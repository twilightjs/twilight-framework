package com.twilight.server.dispatcher.handlers;

import com.twilight.server.dispatcher.handlers.annotation.DefaultListener;
import com.twilight.server.dispatcher.handlers.annotation.HttpMethod;
import com.twilight.server.dispatcher.handlers.annotation.Listener;
import com.twilight.server.handler.DefaultHandlerTwilight;
import com.twilight.server.handler.Handler;
import com.twilight.server.handler.Manager;
import com.twilight.server.handler.exception.ExecutorNotFound;
import com.twilight.server.io.request.HttpRequest;
import com.twilight.server.io.response.HttpResponse;
import com.twilight.tools.parsers.http.HttpParserM;
import com.twilight.tools.parsers.uri.URI;

public abstract class HandlerRequestImplementation {

    protected static HandlerRequest createHandler() {
        return new HandlerRequestRequestMediator();
    }


    protected Handler checkForMatches(Manager manager, HttpParserM httpParserM) {
//        isEmpty(manager);
        Handler definedHandler = new DefaultHandlerTwilight();
        for (Handler handler : manager.getHttpExecutorList()) {
            if (isDefaultExecutor(httpParserM, handler)) {
                definedHandler = handler;
            }
            if (isExecutor(httpParserM, handler)) {
                definedHandler = handler;
                break;
            }
        }
        return definedHandler;
    }


    private void isEmpty(Manager manager) throws ExecutorNotFound {
        if (manager.getHttpExecutorList().isEmpty()) {
            throw new ExecutorNotFound("Executor not found, specify new executors");
        }
    }

    private boolean isDefaultExecutor(HttpParserM httpParserM, Handler handler) {
        boolean isDefaultExecutor = false;
        try {
            isDefaultExecutor = (getDefaultListener(handler) != null && isMethodEquals(httpParserM, getHttpMethodAnnotation(handler)) ||
                    getDefaultListener(handler) != null && getHttpMethodAnnotation(handler).method().equals("ALL"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return isDefaultExecutor;
    }

    private boolean isExecutor(HttpParserM httpParserM, Handler handler) {
        boolean isExecutor = false;
        try {
            isExecutor = getListenerAnnotation(handler) != null && isPathEquals(httpParserM.getURI(), getListenerAnnotation(handler)) && isMethodEquals(httpParserM, getHttpMethodAnnotation(handler));
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

    private boolean isMethodEquals(HttpParserM httpParserM, HttpMethod method) {
        return httpParserM.getMethod().equals(method.method().toUpperCase());
    }

    private HttpMethod getHttpMethodAnnotation(Handler handler) throws NoSuchMethodException {
        return handler.getClass().getMethod("handleRequest", HttpRequest.class, HttpResponse.class).getAnnotation(HttpMethod.class);
    }
}
