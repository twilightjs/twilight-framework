package org.twilightframework.http.server;

import org.twilightframework.http.server.notification.NotificationBehavior;

public class TwilightXnioServer extends Twilight{
    public TwilightXnioServer(TwilightBuilder twilightBuilder, NotificationBehavior notificationBehavior) {
        super(twilightBuilder, notificationBehavior);
    }

    @Override
    public void start() {

    }

}
