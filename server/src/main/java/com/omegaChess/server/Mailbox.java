package com.omegaChess.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static com.omegaChess.server.OCServerData.createDirectoryIfNonExistent;

public class Mailbox {

    private ArrayList<Invite> sent, received;
    private ArrayList<Notification> notifications;

    public Mailbox() {
        sent = new ArrayList<>();
        received = new ArrayList<>();
        notifications = new ArrayList<>();
    }

    public void addToSent(Invite invite) { sent.add(invite); }

    public void removeFromSent(Invite invite) { sent.remove(invite); }

    public void addToReceived(Invite invite) { received.add(invite); }

    public void removeFromReceived(Invite invite) { received.remove(invite); }

    public ArrayList<Invite> getReceived() { return received; }

    public ArrayList<Invite> getSent() { return sent; }

    public void addNotification(String event, String message) {
        Notification notification = new Notification(event, message);
        notifications.add(notification);
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void save(String saveLocation) {

        createDirectoryIfNonExistent(saveLocation);

        final String sentInvitationsSaveLocation = saveLocation + "sent-invitations/";
        final String receivedInvitationsSaveLocation = saveLocation + "received-invitations/";
        final String notificationsSaveLocation = saveLocation + "notifications/";

        // save sent invitations
        for (Invite i : sent) {
            i.save(sentInvitationsSaveLocation);
        }

        // save received invitations
        for (Invite i : received) {
            i.save(receivedInvitationsSaveLocation);
        }

        // save notification timestamps
        try {
            File saveFile = new File(notificationsSaveLocation + "timestamps.txt");

            createDirectoryIfNonExistent(notificationsSaveLocation);

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            for (Notification n : notifications) {
                saveWriter.write(n.getDateString() + "\n");
            }

            saveWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        // save notifications
        for (Notification n : notifications) {
            n.save(notificationsSaveLocation);
        }
    }

    public void load(String saveLocation) {

        final String sentInvitationsSaveLocation = saveLocation + "sent-invitations/";
        final String receivedInvitationsSaveLocation = saveLocation + "received-invitations/";
        final String notificationsSaveLocation = saveLocation + "notifications/";

        // load sent invites
        // TODO

        // load received invites
        // TODO

        // load notifications
        try {
            File loadFile = new File(notificationsSaveLocation + "timestamps.txt");
            Scanner loadReader = new Scanner(loadFile);
            // actual loading
            while (loadReader.hasNextLine()) {
                String nextTimeStamp = loadReader.nextLine();
                Notification temp = new Notification();
                temp.load(notificationsSaveLocation + nextTimeStamp + ".txt");
                notifications.add(temp);
            }
            loadReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found in " + notificationsSaveLocation);
        }
    }
}
