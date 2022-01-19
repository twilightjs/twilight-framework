package com.twilight.server.http;

import com.twilight.server.handler.Handler;

import java.net.InetSocketAddress;
import java.util.ArrayList;

public class TwilightBuilder {
    private InetSocketAddress address;
    private ArrayList<Handler> handlers;
    private String twilightServer;

    public TwilightBuilder(){
        this.handlers = new ArrayList<>();
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public ArrayList<Handler> getHandlers() {
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
            case "TWILIGHT_BLOCKING_SERVER" -> new TwilightBlockingServer(this);
            case "TWILIGHT_NON_BLOCKING_SERVER" -> new TwilightNonBlockingServer(this);
            case "TWILIGHT_XNIO_SERVER" -> new TwilightXnioServer(this);
            default -> throw new IllegalStateException("Unexpected value: " + twilightServer);
        };
    }
}
