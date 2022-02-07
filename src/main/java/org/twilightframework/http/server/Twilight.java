package org.twilightframework.http.server;

import org.twilightframework.http.handler.Handler;

import java.net.InetSocketAddress;
import java.util.ArrayList;

public abstract class Twilight {
    protected final InetSocketAddress address;
    protected final ArrayList<Handler> handlers;
    protected NotificationBehavior notificationBehavior;

    public Twilight(TwilightBuilder twilightBuilder, NotificationBehavior notificationBehavior) {
        this.address = twilightBuilder.getAddress();
        this.handlers = twilightBuilder.getHandlers();
        this.notificationBehavior = notificationBehavior;
    }

    public Twilight(TwilightBuilder twilightBuilder) {
        this.address = twilightBuilder.getAddress();
        this.handlers = twilightBuilder.getHandlers();
    }

    public static TwilightBuilder builder() {
        return new TwilightBuilder();
    }

    public abstract void start();
}
