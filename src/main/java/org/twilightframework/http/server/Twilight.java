package org.twilightframework.http.server;

import org.twilightframework.http.server.data.ServerData;
import org.twilightframework.http.server.notification.NotificationBehavior;

import java.net.InetSocketAddress;

public abstract class Twilight {
    protected final InetSocketAddress address;
    protected ServerData serverData;

    public Twilight(TwilightBuilder twilightBuilder, NotificationBehavior notificationBehavior) {
        this.address = twilightBuilder.getAddress();
        this.serverData = new ServerData(twilightBuilder.getHandlers(), notificationBehavior);
    }

    public static TwilightBuilder builder() {
        return new TwilightBuilder();
    }

    public abstract void start();
}
