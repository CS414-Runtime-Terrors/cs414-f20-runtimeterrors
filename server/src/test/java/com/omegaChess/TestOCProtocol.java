package com.omegaChess;

import com.omegaChess.server.OCMessage;
import com.omegaChess.server.OCProtocol;
import com.omegaChess.server.OCServerData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        // unregister profile
        OCMessage message = new OCMessage();
        message.put("process", "unregister");
        message.put("nickname", "Daniel");

        String input = message.toString();

        String output = protocol.processInput(input);

        OCMessage receivedMessage = new OCMessage();
        receivedMessage.fromString(output);

        // assert non-existence
        assertFalse(data.profileExists("Daniel"));
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

}
