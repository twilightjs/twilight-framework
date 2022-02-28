package org.twilightframework.http.server;

import org.twilightframework.http.server.notification.NotificationBehavior;

public class TwilightNonBlockingServer extends Twilight {
    public TwilightNonBlockingServer(TwilightBuilder twilightBuilder, NotificationBehavior notificationBehavior) {
        super(twilightBuilder, notificationBehavior);
    }

    @Override
    public void start() {

    }

}
