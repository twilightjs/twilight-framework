package com.twilight.server.http;

import com.twilight.server.handler.Manager;

import java.net.InetSocketAddress;

public class AsyncHttpServer extends AsyncHttpServerImplementation implements HttpServer {

    protected AsyncHttpServer(InetSocketAddress address) {
        super(address);
    }

    protected AsyncHttpServer(String hostname, int port) {
        super(hostname, port);
    }

    protected AsyncHttpServer(int port) {
        super(port);
    }

    public static HttpServer addListener(InetSocketAddress address) {
        return AsyncHttpServerImplementation.addListener(address);
    }

    public static HttpServer addListener(int port) {
        return AsyncHttpServerImplementation.addListener(port);
    }

    public static HttpServer addListener(String hostname, int port) {
        return AsyncHttpServerImplementation.addListener(hostname, port);
    }

    public void setManager(Manager manager) {
        super.setManager(manager);
    }

    @Override
    public void startServer() {
        super.startServer();
    }
}
