package com.omegaChess.server;

import java.util.Date;

import static com.omegaChess.server.OCServerData.createDirectoryIfNonExistent;

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

    public void save(String saveLocation) {

        createDirectoryIfNonExistent(saveLocation);

        // save primitives
        // TODO
    }

    public void load() {
        // TODO
    }

}
