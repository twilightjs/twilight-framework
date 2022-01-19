package com.twilight.server.http;

import com.twilight.server.handler.Manager;

public interface HttpServer {
    public void startServer();

    public void setManager(Manager manager);
}
