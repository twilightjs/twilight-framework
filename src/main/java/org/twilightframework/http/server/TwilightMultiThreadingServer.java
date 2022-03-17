package org.twilightframework.http.server;

import org.twilightframework.http.handler.HandlerSelector;
import org.twilightframework.http.server.io.InputOutputExchanger;
import org.twilightframework.http.server.io.InputOutputExchangerNetImplementation;
import org.twilightframework.http.server.notification.NotificationBehavior;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TwilightMultiThreadingServer extends Twilight {

    private final ExecutorService threadPool = Executors.newCachedThreadPool();

    public TwilightMultiThreadingServer(TwilightBuilder twilightBuilder, NotificationBehavior notificationBehavior) {
        super(twilightBuilder, notificationBehavior);
    }

    @Override
    public void start() {
        ServerSocket serverSocket = configureServerSocket();
        while (true) threadPool.execute(() -> {
            Socket socket = configureSocket(serverSocket);
            InputOutputExchanger inputOutputExchanger = new InputOutputExchangerNetImplementation(socket);
            HandlerSelector handlerSelector = new HandlerSelector(serverData.handlers(), inputOutputExchanger);
            serverData.notificationBehavior().notifyHandler(handlerSelector.select(), inputOutputExchanger);
            socketClose(socket);
        });
    }

    private ServerSocket configureServerSocket() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(address);
            serverSocket.setPerformancePreferences(0, 1, 2);
            serverSocket.setReuseAddress(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverSocket;
    }

    private Socket configureSocket(ServerSocket serverSocket) {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
            socket.setPerformancePreferences(0, 1, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    private void socketClose(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
