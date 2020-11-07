package com.omegaChess.server;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    // storage constructor
    public Notification() {

    }

    public String getEvent() {
        return event;
    }

    public String getMessage() {
        return message;
    }

    public String getDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return dateFormat.format(date);
    }

    public void fromDateString(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            date = formatter.parse(dateString);
        } catch(Exception e) {
            System.out.println("Something went wrong parsing date string: " + dateString);
            e.printStackTrace();
        }

    }

    public void save(String saveLocation) {

        createDirectoryIfNonExistent(saveLocation);

        // save primitives
        // TODO
    }

    public void load(String saveLocation) {
        // TODO
    }

}
