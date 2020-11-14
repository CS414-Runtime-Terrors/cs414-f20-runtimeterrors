package com.omegaChess.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static com.omegaChess.server.OCServerData.createDirectoryIfNonExistent;

public class Notification {
    public enum NotificationType {
        NEW_MATCH, INVITE_REQUEST, ACCEPTED_INVITE, DECLINED_INVITE, MATCH_ENDED,
        INVITE_CANCELLED
    }

    private static int numInstances = 0;
    private int ID;

    private NotificationType event;
    private String message;
    private Date date;

    public Notification(NotificationType eventToSet, String messageToSet) {
        numInstances++;
        event = eventToSet;
        message = messageToSet;
        date = new Date();
        ID = numInstances;
    }

    // storage constructor
    public Notification() {
        numInstances++;
    }

    public NotificationType getEvent() {
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

    public int getID() {
        return ID;
    }

    public void save(String notificationsSaveLocation) {

        createDirectoryIfNonExistent(notificationsSaveLocation);

        // save primitives to notifications save folder in primitives.txt
        try {
            File saveFile = new File(notificationsSaveLocation + getID() + ".txt");

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            saveWriter.write(ID + "\n");
            saveWriter.write(event.toString() + "\n");
            saveWriter.write(message + "\n");
            saveWriter.write(getDateString() + "\n");

            saveWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void load(String saveLocation) {
        // load primitives
        try {
            File loadFile = new File(saveLocation);
            Scanner loadReader = new Scanner(loadFile);

            // actual loading
            if (loadReader.hasNextLine()) {
                ID = Integer.parseInt(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                String line = loadReader.nextLine();
                setEvent(getNotificationTypeFromString(line));
            }
            if (loadReader.hasNextLine()) {
                setMessage(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                fromDateString(loadReader.nextLine());
            }

            loadReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found in " + saveLocation);
        }
    }

    private NotificationType getNotificationTypeFromString(String line) {
        if( line.equals(NotificationType.NEW_MATCH.name()))
        {
            return NotificationType.NEW_MATCH;
        }
        else if( line.equals(NotificationType.INVITE_REQUEST.name()))
        {
            return NotificationType.INVITE_REQUEST;
        }
        else if( line.equals(NotificationType.ACCEPTED_INVITE.name()))
        {
            return NotificationType.ACCEPTED_INVITE;
        }
        else if( line.equals(NotificationType.DECLINED_INVITE.name()))
        {
            return NotificationType.DECLINED_INVITE;
        }
        else if( line.equals(NotificationType.MATCH_ENDED.name()))
        {
            return NotificationType.MATCH_ENDED;
        }
        else if( line.equals(NotificationType.INVITE_CANCELLED.name()))
        {
            return NotificationType.INVITE_CANCELLED;
        }
        return null;
    }

    private void setEvent(NotificationType s) {
        event = s;
    }

    private void setMessage(String s) {
        message = s;
    }

}
