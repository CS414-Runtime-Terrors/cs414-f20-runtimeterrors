package com.csc14.runtimeterrors.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class OCClient {

    Socket socket;
    PrintWriter out;
    BufferedReader in;

    public OCClient(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Missing arguments for server host name and port number!");
            return;
        }

        socket = new Socket(args[0], Integer.parseInt(args[1])); // server host name then port
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private OCMessage sendRequestAndReceiveMessage(OCMessage message) {
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

    private boolean printResult(OCMessage receivedMessage) {
        String success = receivedMessage.get("success");

        if (success.equals("true")) {
            System.out.println("Success!");
            return true;
        }
        else {
            System.out.println(receivedMessage.get("reason"));
            return false;
        }
    }

    // example request
    public String sendSquareRequest(String number) {

        System.out.println("Sending square request!");

        OCMessage message = new OCMessage();
        message.put("process", "square");
        message.put("number", "" + number);

        // receive message
        OCMessage receivedMessage = sendRequestAndReceiveMessage(message);

        // print answer
        String success = receivedMessage.get("success");

        if (success.equals("true")) {
            return receivedMessage.get("answer");
        }
        else {
            return receivedMessage.get("reason");
        }

    }

    // register request
    public boolean sendRegisterRequest(String email, String nickname, String password) {

        System.out.println("Sending register request for " + nickname + "!");

        OCMessage message = new OCMessage();
        message.put("process", "register");
        message.put("email", email);
        message.put("nickname", nickname);
        message.put("password", password);

        // receive message
        OCMessage receivedMessage = sendRequestAndReceiveMessage(message);

        return printResult(receivedMessage);
    }

    // unregister request
    public boolean sendUnregisterRequest(String nickname) {
        System.out.println("Sending unregister request for " + nickname + "!");

        OCMessage message = new OCMessage();
        message.put("process", "unregister");
        message.put("nickname", nickname);

        // receive message
        OCMessage receivedMessage = sendRequestAndReceiveMessage(message);

        return printResult(receivedMessage);
    }

    // login request
    public OCMessage sendLoginRequest(String nickname, String password) {
        System.out.println("Sending login request for " + nickname + "!");

        OCMessage message = new OCMessage();
        message.put("process", "login");
        message.put("nickname", nickname);
        message.put("password", password);

        // receive message
        OCMessage receivedMessage = sendRequestAndReceiveMessage(message);

        printResult(receivedMessage);

        return receivedMessage;
    }

    // get profile data request
    public OCMessage sendGetProfileDataRequest(String nickname) {
        System.out.println("Sending get profile data request for " + nickname + "!");

        OCMessage message = new OCMessage();
        message.put("process", "get profile data");
        message.put("nickname", nickname);

        // receive message
        OCMessage receivedMessage = sendRequestAndReceiveMessage(message);

        printResult(receivedMessage);

        return receivedMessage;
    }

    // send invite request
    public OCMessage sendInviteRequest(String inviter, String invitee){
        System.out.println("Sending invite request from " + inviter + " to " + invitee + "!");

        OCMessage message = new OCMessage();
        message.put("process", "invite");
        message.put("invitee", invitee.toLowerCase());
        message.put("inviter", inviter.toLowerCase());

        // send and receive results
        OCMessage receivedMessage = sendRequestAndReceiveMessage(message);

        printResult(receivedMessage);

        return receivedMessage;

    }

    // get sent invites request
    public OCMessage getSentInvites(String user){
        System.out.println("Sending request to get sent invites from mailbox!");

        OCMessage message = new OCMessage();
        message.put("process", "invites sent");
        message.put("user", user);

        // Send and receive results
        OCMessage receivedMessage = sendRequestAndReceiveMessage(message);

        printResult(receivedMessage);

        return receivedMessage;
    }

    // get received invites request
    public OCMessage getReceivedInvites(String user){
        System.out.println("Sending request to get received invites from mailbox!");

        OCMessage message = new OCMessage();
        message.put("process", "invites received");
        message.put("user", user);

        // Send and receive results
        OCMessage receivedMessage = sendRequestAndReceiveMessage(message);

        printResult(receivedMessage);

        return receivedMessage;
    }

    // get notifications request
    public OCMessage getNotifications(String nickname) {
        System.out.println("Sending request to get notifications from mailbox for user: " + nickname);

        OCMessage message = new OCMessage();
        message.put("process", "get notifications");
        message.put("nickname", nickname);

        // Send and receive results
        OCMessage receivedMessage = sendRequestAndReceiveMessage(message);

        printResult(receivedMessage);

        return receivedMessage;
    }

}
