package org.twilightframework.http.server;

import org.twilightframework.http.server.notification.NotificationBehavior;

public class TwilightBlockingServer extends Twilight {
    public TwilightBlockingServer(TwilightBuilder twilightBuilder, NotificationBehavior notificationBehavior) {
        super(twilightBuilder, notificationBehavior);
    }

    @Override
    public void start() {

    }

}
