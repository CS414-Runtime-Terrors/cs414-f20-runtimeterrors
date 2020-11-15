package com.omegaChess;

import com.omegaChess.server.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

public class TestMailbox {

    @Test
    public void testAddToSent(){
        Invite invite = new Invite("Jimmy", "John");
        Mailbox mailbox = new Mailbox();

        mailbox.addToSent(invite);
        assertEquals(mailbox.getSent().get(0), invite, "Failed to add to sent mailbox!");
    }

    @Test
    public void testAddToReceived(){
        Invite invite = new Invite("Terry", "Tristan");
        Mailbox mailbox = new Mailbox();

        mailbox.addToReceived(invite);
        assertEquals(mailbox.getReceived().get(0), invite, "Failed to add to received mailbox!");
    }

    @Test
    public void testAddNotification() {
        Notification.NotificationType expectedEvent = Notification.NotificationType.NEW_MATCH;
        String expectedMessageString = "testMessage";

        Mailbox mailbox = new Mailbox();
        mailbox.addNotification(Notification.NotificationType.NEW_MATCH, "testMessage");
        assertEquals(expectedEvent, mailbox.getNotifications().get(0).getEvent());
        assertEquals(expectedMessageString, mailbox.getNotifications().get(0).getMessage());
    }

    @Test
    public void testGetNotifications() {
        Mailbox mailbox = new Mailbox();
        mailbox.addNotification(Notification.NotificationType.NEW_MATCH, "testMessage");
        assertEquals(1, mailbox.getNotifications().size());
    }

    @Test
    public void testRemoveInvites() {
        Invite invite = new Invite("Death2Fall", "JuneDay");
        Mailbox mailbox = new Mailbox();

        mailbox.addToSent(invite);
        mailbox.addToReceived(invite);

        mailbox.removeFromSent(invite);
        assertEquals(0, mailbox.getSent().size(), "Failed to remove from sent!");
        mailbox.removeFromReceived(invite);
        assertEquals(0, mailbox.getReceived().size(), "Failed to remove from received!");
    }
}
