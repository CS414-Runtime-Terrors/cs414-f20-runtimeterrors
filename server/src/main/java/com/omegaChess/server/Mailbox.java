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

        // save sent invite filenames
        try {
            File saveFile = new File(sentInvitationsSaveLocation + "filenames.txt");

            createDirectoryIfNonExistent(sentInvitationsSaveLocation);

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            for (Invite i : sent) {
                saveWriter.write(i.getInviter() + "-" + i.getInvitee() + ".txt" + "\n");
            }

            saveWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        // save sent invitations
        for (Invite i : sent) {
            i.save(sentInvitationsSaveLocation);
        }

        // save received invite filenames
        try {
            File saveFile = new File(receivedInvitationsSaveLocation + "filenames.txt");

            createDirectoryIfNonExistent(receivedInvitationsSaveLocation);

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            for (Invite i : received) {
                saveWriter.write(i.getInviter() + "-" + i.getInvitee() + ".txt" + "\n");
            }

            saveWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        // save received invitations
        for (Invite i : received) {
            i.save(receivedInvitationsSaveLocation);
        }

        // save notification filenames
        try {
            File saveFile = new File(notificationsSaveLocation + "filenames.txt");

            createDirectoryIfNonExistent(notificationsSaveLocation);

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            for (Notification n : notifications) {
                saveWriter.write(n.getID() + "\n");
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
        try {
            File loadFile = new File(sentInvitationsSaveLocation + "filenames.txt");
            Scanner loadReader = new Scanner(loadFile);
            // actual loading
            while (loadReader.hasNextLine()) {
                String nextFileName = loadReader.nextLine();
                Invite temp = new Invite();
                temp.load(sentInvitationsSaveLocation + nextFileName);
                sent.add(temp);
            }
            loadReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found in " + sentInvitationsSaveLocation);
        }

        // load received invites
        try {
            File loadFile = new File(receivedInvitationsSaveLocation + "filenames.txt");
            Scanner loadReader = new Scanner(loadFile);
            // actual loading
            while (loadReader.hasNextLine()) {
                String nextFileName = loadReader.nextLine();
                Invite temp = new Invite();
                temp.load(receivedInvitationsSaveLocation + nextFileName);
                received.add(temp);
            }
            loadReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found in " + receivedInvitationsSaveLocation);
        }

        // load notifications
        try {
            File loadFile = new File(notificationsSaveLocation + "filenames.txt");
            Scanner loadReader = new Scanner(loadFile);
            // actual loading
            while (loadReader.hasNextLine()) {
                String nextFilename = loadReader.nextLine();
                Notification temp = new Notification();
                temp.load(notificationsSaveLocation + nextFilename + ".txt");
                notifications.add(temp);
            }
            loadReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found in " + notificationsSaveLocation);
        }
    }
}
