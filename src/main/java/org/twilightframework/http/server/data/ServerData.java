package org.twilightframework.http.server.data;

import org.twilightframework.http.handler.Handler;
import org.twilightframework.http.server.notification.NotificationBehavior;

import java.util.List;

public record ServerData(List<Handler> handlers,
                         NotificationBehavior notificationBehavior) {

    public List<Handler> getHandlers() {
        return handlers;
    }


    public NotificationBehavior getNotificationBehavior() {
        return notificationBehavior;
    }
}
