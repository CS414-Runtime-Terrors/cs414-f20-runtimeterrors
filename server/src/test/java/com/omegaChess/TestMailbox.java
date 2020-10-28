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
        String expectedEventString = "testEvent";
        String expectedMessageString = "testMessage";

        Mailbox mailbox = new Mailbox();
        mailbox.addNotification("testEvent", "testMessage");
        assertEquals(expectedEventString, mailbox.getNotifications().get(0).getEvent());
        assertEquals(expectedMessageString, mailbox.getNotifications().get(0).getMessage());
    }

    @Test
    public void testGetNotifications() {
        Mailbox mailbox = new Mailbox();
        mailbox.addNotification("testEvent", "testMessage");
        assertEquals(1, mailbox.getNotifications().size());
    }
}
