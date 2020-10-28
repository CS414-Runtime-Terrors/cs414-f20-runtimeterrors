package com.omegaChess;

import com.omegaChess.server.Notification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestNotification {

    @Test
    public void testConstructor() {
        Notification notification = new Notification("new match", "You've been invited to a new match!");

        assertEquals("new match", notification.getEvent());
        assertEquals("You've been invited to a new match!", notification.getMessage());
        assertNotNull(notification.getDateString());
    }

    @Test
    public void testGetEvent() {
        Notification notification = new Notification("won game", "You've won a match!");

        assertEquals("won game", notification.getEvent());
    }

    @Test
    public void testGetMessage() {
        Notification notification = new Notification("invite accepted", "An invite of yours was accepted!");

        assertEquals("An invite of yours was accepted!", notification.getMessage());
    }

    @Test
    public void testGetDateString() {
        Notification notification = new Notification("new match", "You've been invited to a new match!");

        assertNotNull(notification.getDateString());
    }


}
