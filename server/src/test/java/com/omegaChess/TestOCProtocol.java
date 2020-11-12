package com.omegaChess;

import com.omegaChess.server.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JUnit OCProtocol Class Test")
public class TestOCProtocol {

    @Test
    public void testSquareInput() {
        OCServerData data = new OCServerData();
        OCProtocol protocol = new OCProtocol(data);

        OCMessage message = new OCMessage();
        message.put("process", "square");
        message.put("number", "10");

        String input = message.toString();

        String output = protocol.processInput(input);

        OCMessage receivedMessage = new OCMessage();
        receivedMessage.fromString(output);
        String answer = (String) receivedMessage.get("answer");

        assertEquals("Square of 10 is 100", answer);
    }

    @Test
    public void testRegisterUser() {
        OCServerData data = new OCServerData();
        OCProtocol protocol = new OCProtocol(data);

        OCMessage message = new OCMessage();
        message.put("process", "register");
        message.put("email", "test@gmail.com");
        message.put("nickname", "testGuy");
        message.put("password", "pass");

        String input = message.toString();

        String output = protocol.processInput(input);

        OCMessage receivedMessage = new OCMessage();
        receivedMessage.fromString(output);

        // assert existence
        assertTrue(data.profileExists("testGuy"));
    }

    @Test
    public void testUnregisterUser() {
        OCServerData data = new OCServerData();
        OCProtocol protocol = new OCProtocol(data);

        data.createProfile("Daniel", "pass", "daniel@gmail.com");
        data.createProfile("John", "word", "john@omegachess.com");

        // Send invites between the users
        OCMessage message = new OCMessage();
        message.put("process", "invite");
        message.put("invitee", "John");
        message.put("inviter", "Daniel");

        String input = message.toString();

        protocol.processInput(input);

        message = new OCMessage();
        message.put("process", "invite");
        message.put("inviter", "John");
        message.put("invitee", "Daniel");

        input = message.toString();

        protocol.processInput(input);

        // Create a test match between the users
        data.addMatch(new Match("Daniel", "john"));

        // Create a test archive between the users
        data.addToArchive(new GameRecord("Daniel", "John", 45, false));

        // unregister profile
        message = new OCMessage();
        message.put("process", "unregister");
        message.put("nickname", "Daniel");

        input = message.toString();

        String output = protocol.processInput(input);

        OCMessage receivedMessage = new OCMessage();
        receivedMessage.fromString(output);

        // assert non-existence in all states
        assertFalse(data.profileExists("Daniel"));
        assertEquals(0, data.getProfile("John").getMailbox().getReceived().size(), "Failed to remove invite from Daniel.");
        assertEquals(0, data.getProfile("John").getMailbox().getSent().size(), "Failed to remove invite to Daniel.");
        assertEquals(0, data.getMatches().size(), "Failed to end match between users.");
        assertEquals("[deleted]", data.getArchive().get(0).getWinner(), "Failed to remove Daniel as winner from archive.");

    }

    @Test
    public void testLoginUser() {
        OCServerData data = new OCServerData();
        OCProtocol protocol = new OCProtocol(data);

        data.createProfile("Daniel", "pass", "daniel@gmail.com");

        // login
        OCMessage message = new OCMessage();
        message.put("process", "login");
        message.put("nickname", "Daniel");
        message.put("password", "pass");

        String input = message.toString();

        String output = protocol.processInput(input);

        OCMessage receivedMessage = new OCMessage();
        receivedMessage.fromString(output);

        assertEquals("true", receivedMessage.get("success"));
    }

    @Test
    void testInvite() {
        OCServerData data = new OCServerData();
        OCProtocol protocol = new OCProtocol(data);

        data.createProfile("sweetfire", "T0asty!!", "SweetFireSauce@omegacess.net");
        data.createProfile("PawPatrol", "Pupp!e5!", "PawPatrolPuppySquad@omegachess.net");

        // Invite
        OCMessage message = new OCMessage();
        message.put("process", "invite");
        message.put("invitee", "sweetfire");
        message.put("inviter", "pawpatrol");

        String input = message.toString();

        String output = protocol.processInput(input);

        OCMessage receivedMessage = new OCMessage();
        receivedMessage.fromString(output);

        assertEquals("true", receivedMessage.get("success"), "Invite was not sent!");
        assertFalse(data.getProfile("sweetfire").getMailbox().getReceived().isEmpty(),
                "Failed to add invite to mailbox received!");
        assertFalse(data.getProfile("pawpatrol").getMailbox().getSent().isEmpty(),
                "Failed to add invite to mailbox sent!");

    }

    @Test
    void testGetInvites() {
        OCServerData data = new OCServerData();
        OCProtocol protocol = new OCProtocol(data);

        data.createProfile("eatmyshorts","B@rts1mps0n","bartsimpsonininc@omegachess.net");
        data.createProfile("italian","!talianHandshak3","needsmorecheese@omegachess.net");

        // Send invite
        OCMessage message = new OCMessage();
        message.put("process", "invite");
        message.put("invitee", "eatmyshorts");
        message.put("inviter", "italian");

        String input = message.toString();

        protocol.processInput(input);

        // get sent invites
        message = new OCMessage();
        message.put("process", "invites sent");
        message.put("user", "italian");

        input = message.toString();

        String output = protocol.processInput(input);

        OCMessage receivedMessage = new OCMessage();
        receivedMessage.fromString(output);

        OCMessage invite = new OCMessage();
        if (receivedMessage.get("object0").equalsIgnoreCase("invite")){
            invite.put("inviter", receivedMessage.get("inviter0"));
            invite.put("invitee", receivedMessage.get("invitee0"));
            invite.put("accepted", receivedMessage.get("accepted0"));
            invite.put("declined", receivedMessage.get("declined0"));
        }

        assertEquals("italian", invite.get("inviter"), "Failed to get sent invite");

        // get received invites
        message = new OCMessage();
        message.put("process","invites received");
        message.put("user","eatmyshorts");

        input = message.toString();

        output = protocol.processInput(input);

        receivedMessage = new OCMessage();
        receivedMessage.fromString(output);
        invite = new OCMessage();

        if (receivedMessage.get("object0").toString().equalsIgnoreCase("invite")) {
            invite.put("inviter", receivedMessage.get("inviter0").toString());
            invite.put("invitee", receivedMessage.get("invitee0").toString());
            invite.put("accepted", receivedMessage.get("accepted0").toString());
            invite.put("declined", receivedMessage.get("declined0").toString());
        }


        assertEquals("eatmyshorts", invite.get("invitee").toString(), "Failed to get sent invite");

    }

    @Test
    public void testGetProfileData() {
        OCServerData data = new OCServerData();
        OCProtocol protocol = new OCProtocol(data);

        data.createProfile("Daniel", "pass", "daniel@gmail.com");
        data.getProfile("Daniel").setGamesWon(1);
        data.getProfile("Daniel").setGamesLost(2);
        data.getProfile("Daniel").setGamesTied(3);

        // login
        OCMessage message = new OCMessage();
        message.put("process", "get profile data");
        message.put("nickname", "Daniel");

        String input = message.toString();

        String output = protocol.processInput(input);

        OCMessage receivedMessage = new OCMessage();
        receivedMessage.fromString(output);

        assertEquals("1", receivedMessage.get("gamesWon"));
        assertEquals("2", receivedMessage.get("gamesLost"));
        assertEquals("3", receivedMessage.get("gamesTied"));
    }

    @Test
    public void testGetNotifications() {
        OCServerData data = new OCServerData();
        OCProtocol protocol = new OCProtocol(data);

        data.createProfile("Daniel", "pass", "daniel@gmail.com");

        data.getProfile("Daniel").getMailbox().addNotification("Event 1", "Message 1");
        data.getProfile("Daniel").getMailbox().addNotification("Event 2", "Message 2");

        // get notifications
        OCMessage message = new OCMessage();
        message.put("process", "get notifications");
        message.put("nickname", "Daniel");

        String input = message.toString();

        System.out.println(input);

        String output = protocol.processInput(input);

        OCMessage receivedMessage = new OCMessage();
        receivedMessage.fromString(output);

        System.out.println(output);

        int count = Integer.parseInt(receivedMessage.get("count"));

        assertEquals(2, count);
        assertEquals("Event 1", receivedMessage.get("event1"));
        assertEquals("Message 1", receivedMessage.get("message1"));
        assertNotNull(receivedMessage.get("datestring1"));
        assertEquals("Event 2", receivedMessage.get("event2"));
        assertEquals("Message 2", receivedMessage.get("message2"));
        assertNotNull(receivedMessage.get("datestring2"));
    }

    @Test
    public void testInviteResponse(){
        OCServerData data = new OCServerData();
        OCProtocol protocol = new OCProtocol(data);

        data.createProfile("jae", "wing", "adskfjlhasd@omegachess.com");
        data.createProfile("Shing", "shaw", "asdfasdwes@omegachess.com");

        // Send an invite between the users
        OCMessage invite = new OCMessage();
        invite.put("process", "invite");
        invite.put("invitee", "jae");
        invite.put("inviter", "shing");

        String input = invite.toString();

        protocol.processInput(input);

        System.out.println("Testing accepting an invite between two users");

        // Testing accept
        OCMessage message = new OCMessage();
        message.put("process", "invite response");
        message.put("response", "accept");
        message.put("inviter", "shing");
        message.put("invitee", "jae");

        input = message.toString();

        String out = protocol.processInput(input);

        OCMessage receivedMessage = new OCMessage();
        receivedMessage.fromString(out);

        assertEquals("true", receivedMessage.get("success"), "Something went wrong");
        assertEquals(0, data.getProfile("jae").getMailbox().getReceived().size(), "Failed to remove invite from mailbox.");
        assertEquals(0, data.getProfile("shing").getMailbox().getSent().size(), "Failed to remove invite from mailbox.");
        assertEquals(1, data.getMatches().size(), "Failed to add a new match to the server.");

        // Send an invite between the users
        invite = new OCMessage();
        invite.put("process", "invite");
        invite.put("invitee", "jae");
        invite.put("inviter", "shing");

        input = invite.toString();

        protocol.processInput(input);

        System.out.println("Testing declining an invite between two users");

        // Testing decline
        message = new OCMessage();
        message.put("process", "invite response");
        message.put("response", "decline");
        message.put("inviter", "shing");
        message.put("invitee", "jae");

        input = message.toString();

        out = protocol.processInput(input);

        receivedMessage.fromString(out);

        assertEquals("true", receivedMessage.get("success"), "Something went wrong");
        assertEquals(0, data.getProfile("jae").getMailbox().getReceived().size(), "Failed to remove invite from mailbox.");
        assertEquals(0, data.getProfile("shing").getMailbox().getSent().size(), "Failed to remove invite from mailbox.");
    }

    @Test
    public void testGetBoard(){
        OCServerData data = new OCServerData();
        OCProtocol protocol = new OCProtocol(data);

        data.createProfile("this", "one", "thisOne@omegachess.com");
        data.createProfile("that", "one", "thatOne@omegachess.com");

        Match match = new Match("this", "that");
        data.addMatch(match);

        OCMessage message = new OCMessage();

        message.put("process", "get board data");
        message.put("ID", String.valueOf(match.getMatchID()));
        String input = message.toString();

        String out = protocol.processInput(input);

        OCMessage receivedMessage = new OCMessage();
        receivedMessage.fromString(out);

        assertEquals("true", receivedMessage.get("success"), "Failed to get board data because " + receivedMessage.get("reason"));
    }

    @Test
    public void testGetLegalMoves() {
        OCServerData data = new OCServerData();
        OCProtocol protocol = new OCProtocol(data);

        // create profiles
        data.createProfile("pete", "zoop", "asdf@mail.com");
        data.createProfile("kyle", "zoop", "fdsa@mail.com");

        // send invite
        OCMessage invite = new OCMessage();
        invite.put("process", "invite");
        invite.put("invitee", "kyle");
        invite.put("inviter", "pete");
        protocol.processInput(invite.toString());

        // accept invite and get matchID
        OCMessage accept = new OCMessage();
        accept.put("process", "invite response");
        accept.put("response", "accept");
        accept.put("inviter", "pete");
        accept.put("invitee", "kyle");
        String acceptString = protocol.processInput(accept.toString());
        OCMessage acceptResponse = new OCMessage();
        acceptResponse.fromString(acceptString);
        String matchID = acceptResponse.get("matchID");

        // test white pawn in starting position
        OCMessage message = new OCMessage();
        message.put("process", "get legal moves");
        message.put("matchID", matchID);
        message.put("row", "2");
        message.put("column", "1");
        String input = message.toString();
        String out = protocol.processInput(input);
        OCMessage receivedMessage = new OCMessage();
        receivedMessage.fromString(out);
        assertEquals("true", receivedMessage.get("success"));
        assertEquals("/a3/a4/a5/", receivedMessage.get("legal moves"));

        // test black pawn in starting position
        message = new OCMessage();
        message.put("process", "get legal moves");
        message.put("matchID", matchID);
        message.put("row", "9");
        message.put("column", "10");
        input = message.toString();
        out = protocol.processInput(input);
        receivedMessage = new OCMessage();
        receivedMessage.fromString(out);
        assertEquals("true", receivedMessage.get("success"));
        assertEquals("/j8/j7/j6/", receivedMessage.get("legal moves"));

        // test knight in starting position
        message = new OCMessage();
        message.put("process", "get legal moves");
        message.put("matchID", matchID);
        message.put("row", "1");
        message.put("column", "8");
        input = message.toString();
        out = protocol.processInput(input);
        receivedMessage = new OCMessage();
        receivedMessage.fromString(out);
        assertEquals("true", receivedMessage.get("success"));
        assertEquals("/g3/i3/", receivedMessage.get("legal moves"));

        // test blank square
        message = new OCMessage();
        message.put("process", "get legal moves");
        message.put("matchID", matchID);
        message.put("row", "5");
        message.put("column", "5");
        input = message.toString();
        out = protocol.processInput(input);
        receivedMessage = new OCMessage();
        receivedMessage.fromString(out);
        assertEquals("false", receivedMessage.get("success"));
    }
}
