package com.omegaChess.server;

import java.util.Date;

public class Notification {

    private String event;
    private String message;
    private Date date;

    public Notification(String eventToSet, String messageToSet) {
        event = eventToSet;
        message = messageToSet;
        date = new Date();
    }

    public String getEvent() {
        return event;
    }

    public String getMessage() {
        return message;
    }

    public String getDateString() {
        return date.toString();
    }

    public void save() {
        // save primitives
        // TODO
    }

    public void load() {
        // TODO
    }

}
