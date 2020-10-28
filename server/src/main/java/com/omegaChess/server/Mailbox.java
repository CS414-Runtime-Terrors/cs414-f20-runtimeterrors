package com.omegaChess.server;

import java.util.ArrayList;

public class Mailbox {

    private ArrayList<Invite> sent, received;
    private ArrayList<Notification> notifications;

    public Mailbox() {
        sent = new ArrayList<>();
        received = new ArrayList<>();
        notifications = new ArrayList<>();
    }


    public void addToSent(Invite invite) { sent.add(invite); }

    public void addToReceived(Invite invite) { received.add(invite); }

    public ArrayList<Invite> getReceived() { return received; }

    public ArrayList<Invite> getSent() { return sent; }

    public void addNotification(String event, String message) {
        Notification notification = new Notification(event, message);
        notifications.add(notification);
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }
}
