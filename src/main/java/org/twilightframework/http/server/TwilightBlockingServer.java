package org.twilightframework.http.server;

import org.twilightframework.http.handler.HandlerSelector;
import org.twilightframework.http.server.io.InputOutputExchanger;
import org.twilightframework.http.server.io.InputOutputExchangerNioImplementation;
import org.twilightframework.http.server.notification.NotificationBehavior;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TwilightBlockingServer extends Twilight {
    public TwilightBlockingServer(TwilightBuilder twilightBuilder, NotificationBehavior notificationBehavior) {
        super(twilightBuilder, notificationBehavior);
    }

    @Override
    public void start() {
        ServerSocketChannel serverSocketChannel = configureServerSocketChannel();

        while (true) {
            SocketChannel socketChannel = configureSocketChannel(serverSocketChannel);
            InputOutputExchanger inputOutputExchanger = new InputOutputExchangerNioImplementation(socketChannel);
            HandlerSelector handlerSelector = new HandlerSelector(serverData.handlers(), inputOutputExchanger);
            serverData.notificationBehavior().notifyHandler(handlerSelector.select(), inputOutputExchanger);
            socketChannel(socketChannel);
        }
    }

    private ServerSocketChannel configureServerSocketChannel() {
        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel
                    .open()
                    .bind(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverSocketChannel;
    }

    private SocketChannel configureSocketChannel(ServerSocketChannel serverSocketChannel) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = serverSocketChannel
                    .accept()
                    .setOption(StandardSocketOptions.SO_REUSEADDR, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socketChannel;
    }

    private void socketChannel(SocketChannel socketChannel){
        try {
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
