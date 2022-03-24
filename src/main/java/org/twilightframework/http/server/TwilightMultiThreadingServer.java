package org.twilightframework.http.server;

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
        while (true) threadPool.execute(new MultiThreadingRunnable(configureSocket(serverSocket), serverData));
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
}
