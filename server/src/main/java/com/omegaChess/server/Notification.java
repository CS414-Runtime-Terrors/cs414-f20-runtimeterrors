package com.omegaChess.server;

import java.util.Date;

public class Notification {

    String event;
    String message;
    Date date;

    public Notification(String eventToSet, String messageToSet) {
        event = eventToSet;
        message = messageToSet;
        date = new Date();
    }

    public String getEvent() {
        return event;
    }

    public String getMessage() {
        return event;
    }

    public String getDateString() {
        return date.toString();
    }

}
