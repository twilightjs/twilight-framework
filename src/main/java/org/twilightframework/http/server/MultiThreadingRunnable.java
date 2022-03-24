package org.twilightframework.http.server;

import org.twilightframework.http.handler.HandlerSelector;
import org.twilightframework.http.server.data.ServerData;
import org.twilightframework.http.server.io.InputOutputExchanger;
import org.twilightframework.http.server.io.InputOutputExchangerNetImplementation;

import java.io.IOException;
import java.net.Socket;

public class MultiThreadingRunnable implements Runnable {

    private Socket socket;
    private ServerData serverData;

    public MultiThreadingRunnable(Socket socket, ServerData serverData) {
        this.socket = socket;
        this.serverData = serverData;
    }

    @Override
    public void run() {
        InputOutputExchanger inputOutputExchanger = new InputOutputExchangerNetImplementation(this.socket);
        HandlerSelector handlerSelector = new HandlerSelector(serverData.handlers(), inputOutputExchanger);
        serverData.notificationBehavior().notifyHandler(handlerSelector.select(), inputOutputExchanger);
        socketClose();
    }

    private void socketClose() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
