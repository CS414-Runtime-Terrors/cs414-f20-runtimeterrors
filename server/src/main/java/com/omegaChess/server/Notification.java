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

    public void save(String notificationsSaveLocation) {

        createDirectoryIfNonExistent(notificationsSaveLocation);

        // save primitives to notifications save folder in primitives.txt
        try {
            File saveFile = new File(notificationsSaveLocation + getDateString() + ".txt");

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            saveWriter.write(event + "\n");
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
                setEvent(loadReader.nextLine());
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

    private void setEvent(String s) {
        event = s;
    }

    private void setMessage(String s) {
        message = s;
    }

}
