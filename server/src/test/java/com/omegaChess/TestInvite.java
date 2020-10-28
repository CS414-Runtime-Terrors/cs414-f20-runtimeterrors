package com.omegaChess;

import com.omegaChess.server.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestInvite {

    @Test
    public void testSettersAndGetters() {
        Invite invite = new Invite("playa1", "playa2");

        invite.setAccepted(true);
        assertTrue(invite.isAccepted(), "Accept setter failed!");
        invite.setDeclined(true);
        assertTrue(invite.isDeclined(), "Declined setter failed!");
        assertEquals("playa1", invite.getInviter(), "Inviter is incorrect!");
        assertEquals("playa2", invite.getInvitee(), "Invitee is incorrect!");
    }

    @Test
    public void testMakeMatch() {
        Invite invite = new Invite("DnDMonger", "NustinJeep");

        Match match = invite.makeMatch();
        assertEquals(invite.getInviter(), match.getProfile1(), match.getProfile1() + " is not equal to "
                + invite.getInviter());
        assertEquals(invite.getInvitee(), match.getProfile2(), match.getProfile2() + " is not eqaul to "
                + invite.getInvitee());
    }
}
