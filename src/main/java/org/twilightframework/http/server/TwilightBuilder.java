package org.twilightframework.http.server;

import org.twilightframework.http.handler.Handler;
import org.twilightframework.http.server.notification.NotifierTwilightBlockingServer;
import org.twilightframework.http.server.notification.NotifierTwilightMultiThreadingServer;
import org.twilightframework.http.server.notification.NotifierTwilightNonBlockingServer;
import org.twilightframework.http.server.notification.NotifierTwilightXnioServer;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class TwilightBuilder {
    private InetSocketAddress address;
    private List<Handler> handlers;
    private String twilightServer;

    public TwilightBuilder() {
        this.handlers = new ArrayList<>();
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public List<Handler> getHandlers() {
        return handlers;
    }

    public TwilightBuilder configure(TwilightServers twilightServer) {
        this.twilightServer = twilightServer.name();
        return this;
    }

    public TwilightBuilder configure(String twilightServer) {
        this.twilightServer = twilightServer;
        return this;
    }

    public TwilightBuilder setListener(InetSocketAddress address) {
        this.address = address;
        return this;
    }


    public TwilightBuilder setListener(String hostname, int port) {
        this.address = new InetSocketAddress(hostname, port);
        return this;
    }


    public TwilightBuilder setListener(int port) {
        this.address = new InetSocketAddress(port);
        return this;
    }

    public TwilightBuilder addHandler(Handler handler) {
        this.handlers.add(handler);
        return this;
    }

    public Twilight build() {
        return switch (twilightServer) {
            case "TWILIGHT_MULTITHREADING_SERVER" -> new TwilightMultiThreadingServer(this, new NotifierTwilightMultiThreadingServer());
            case "TWILIGHT_BLOCKING_SERVER" -> new TwilightBlockingServer(this, new NotifierTwilightBlockingServer());
            case "TWILIGHT_NON_BLOCKING_SERVER" -> new TwilightNonBlockingServer(this, new NotifierTwilightNonBlockingServer());
            case "TWILIGHT_XNIO_SERVER" -> new TwilightXnioServer(this, new NotifierTwilightXnioServer());
            default -> throw new IllegalStateException("Unexpected value: " + twilightServer);
        };
    }
}
