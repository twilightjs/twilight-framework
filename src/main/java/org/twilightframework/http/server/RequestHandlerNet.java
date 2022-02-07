package org.twilightframework.http.server;

import org.twilightframework.http.handler.Handler;
import org.twilightframework.http.handler.HandlerSelector;
import org.twilightframework.http.server.io.InputOutputExchanger;
import org.twilightframework.http.server.io.InputOutputExchangerNetImplementation;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class RequestHandlerNet implements Runnable {

    private Socket socket;
    private ArrayList<Handler> handlers;
    private NotificationBehavior notificationBehavior;

    public RequestHandlerNet(Socket socket, ArrayList<Handler> handlers, NotificationBehavior notificationBehavior) {
        this.socket = socket;
        this.handlers = handlers;
        this.notificationBehavior = notificationBehavior;
    }

    @Override
    public void run() {
        InputOutputExchanger inputOutputExchanger = new InputOutputExchangerNetImplementation(this.socket);
        HandlerSelector handlerSelector = new HandlerSelector(this.handlers, inputOutputExchanger);
        this.notificationBehavior.notifyHandler(handlerSelector.select(), inputOutputExchanger);
        socketClose();
    }

    private void socketClose() {
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}