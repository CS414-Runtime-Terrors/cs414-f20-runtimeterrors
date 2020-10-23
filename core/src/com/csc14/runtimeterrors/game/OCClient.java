package com.csc14.runtimeterrors.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class OCClient {

    private static final String serverHostName = "albany.cs.colostate.edu"; // in order to test on production, OCMultiServer.java must be running on the server host
    private static final String localHostName = "Jeff-From-Surplus"; // set this when testing locally

    String hostName = localHostName;
    int portNumber = 28362;

    Socket socket;
    PrintWriter out;
    BufferedReader in;

    public OCClient() {
        try {
            socket = new Socket(hostName, portNumber);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Host unknown");
        } catch (IOException e) {
            System.out.println("Something went wrong with the I/O connection.");
        }

    }

    public OCMessage sendRequestAndReceiveMessage(OCMessage message) {
        // send request
        out.println(message.toString());

        // receive message
        OCMessage receivedMessage = new OCMessage();

        try {
            receivedMessage.fromString(in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receivedMessage;
    }

    // example request
    public String sendSquareRequest(String number) {

        OCMessage message = new OCMessage();
        message.put("process", "square");
        message.put("number", "" + number);

        // receive message
        OCMessage receivedMessage = sendRequestAndReceiveMessage(message);

        // print answer
        String success = (String) receivedMessage.get("success");

        if (success.equals("true")) {
            return (String) receivedMessage.get("answer");
        }
        else {
            return (String) receivedMessage.get("reason");
        }

    }

    // register request
    public String sendRegisterRequest(String email, String nickname, String password) {

        OCMessage message = new OCMessage();
        message.put("process", "register");
        message.put("email", email);
        message.put("nickname", nickname);
        message.put("password", password);

        // receive message
        OCMessage receivedMessage = sendRequestAndReceiveMessage(message);

        String success = (String) receivedMessage.get("success");

        if (success.equals("true")) {
            return success;
        }
        else {
            return "error";
        }

    }

}
