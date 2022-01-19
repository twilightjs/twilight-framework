package com.twilight.server.dispatcher;

import com.twilight.server.handler.Manager;
import com.twilight.server.dispatcher.handlers.HandlerRequest;
import com.twilight.server.dispatcher.handlers.HandlerRequestRequestMediator;
import com.twilight.server.handler.Handler;
import com.twilight.tools.parsers.http.HttpMessageParserM;

public abstract class DispatcherExecutorImplementation {
    private Manager manager;
    private String request;

    protected DispatcherExecutorImplementation(Manager manager, String request) {
        this.manager = manager;
        this.request = request;
    }

    protected static Dispatcher createDispatcher(Manager manager, String request) {
        return new DispatcherExecutor(manager, request);
    }

    protected Handler defineExecutor() {
//        Directory directory = DirectoryParser.setDirectory(request);
        HandlerRequest handlerRequest = HandlerRequestRequestMediator.createHandler();
        return handlerRequest.checkForMatches(this.manager, HttpMessageParserM.getHttpParser(this.request));
    }

    protected void invokeDoExecute(Handler handler) {
//        executor.doExecute(new AsyncHttpRequest(this.request), new AsyncHttpResponse());
//        try {
//            Method doExecute = handler.getClass().getMethod("handleRequest", HttpRequest.class, HttpResponse.class);
//            doExecute.invoke(handler, new HttpRequest(this.request), new AsyncHttpResponse());
//        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }
}