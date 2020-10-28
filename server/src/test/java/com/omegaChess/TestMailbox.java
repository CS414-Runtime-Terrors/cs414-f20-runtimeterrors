package com.omegaChess;

import com.omegaChess.server.*;
import org.junit.jupiter.api.Test;

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
}
