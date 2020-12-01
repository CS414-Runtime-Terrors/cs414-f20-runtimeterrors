package com.omegaChess;

import com.omegaChess.server.Notification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestNotification {

    @Test
    public void testConstructor() {
        Notification notification = new Notification(Notification.NotificationType.NEW_MATCH, "You've been invited to a new match!");

        assertEquals(Notification.NotificationType.NEW_MATCH, notification.getEvent());
        assertEquals("You've been invited to a new match!", notification.getMessage());
        assertNotNull(notification.getDateString());
    }

    @Test
    public void testGetEvent() {
        Notification notification = new Notification(Notification.NotificationType.MATCH_ENDED, "You've won a match!");

        assertEquals(Notification.NotificationType.MATCH_ENDED, notification.getEvent());
    }

    @Test
    public void testGetMessage() {
        Notification notification = new Notification(Notification.NotificationType.ACCEPTED_INVITE, "An invite of yours was accepted!");

        assertEquals("An invite of yours was accepted!", notification.getMessage());
    }

    @Test
    public void testGetDateString() {
        Notification notification = new Notification(Notification.NotificationType.NEW_MATCH, "You've been invited to a new match!");

        assertNotNull(notification.getDateString());
    }


}
