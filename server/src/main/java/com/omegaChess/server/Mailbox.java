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

    public void save() {
        // save sent invitations
        for (Invite i : sent) {
            i.save();
        }

        // save received invitations
        for (Invite i : received) {
            i.save();
        }

        // save notifications
        for (Notification n : notifications) {
            n.save();
        }
    }

    public void load() {

    }
}
