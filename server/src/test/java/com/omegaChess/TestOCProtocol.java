package com.omegaChess;

import com.omegaChess.server.OCMessage;
import com.omegaChess.server.OCProtocol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("JUnit OCProtocol Class Test")
public class TestOCProtocol {

    @Test
    public void testSquareInput() {
        OCProtocol protocol = new OCProtocol();

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
        OCProtocol protocol = new OCProtocol();

        OCMessage message = new OCMessage();
        message.put("process", "register");
        message.put("email", "test@gmail.com");
        message.put("nickname", "testGuy");
        message.put("password", "pass");

        String input = message.toString();

        String output = protocol.processInput(input);

        OCMessage receivedMessage = new OCMessage();
        receivedMessage.fromString(output);

        String success = (String) receivedMessage.get("success");

        assertEquals("true", output);
    }

}
