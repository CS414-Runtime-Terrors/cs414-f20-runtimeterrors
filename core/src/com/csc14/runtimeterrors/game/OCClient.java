package com.csc14.runtimeterrors.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class OCClient {

    private static final String serverHostName = "34.71.50.54"; // in order to test on production, OCMultiServer.java must be running on the server host
    private static final String localHostName = "localhost"; // set this to your hostname when testing locally


    private final PrintWriter out;
    private final BufferedReader in;

    public OCClient(boolean useLocalArg) throws IOException {
        String hostName;
        if(useLocalArg)
        {
            hostName = localHostName;
        }
        else
        {
            hostName = serverHostName;
        }

        int portNumber = 8484;
        Socket socket = new Socket(hostName, portNumber);
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


    // get legal moves request
    public OCMessage getLegalMoves(int matchID, int[] position) {
        System.out.println("Sending request to get legal moves for matchID: " + matchID + " and piece at position: " + position[0] + "," + position[1]);

        OCMessage message = new OCMessage();
        message.put("process", "get legal moves");
        message.put("matchID", Integer.toString(matchID));
        message.put("row", Integer.toString(position[0]));
        message.put("column", Integer.toString(position[1]));

        // Send and receive results
        OCMessage receivedMessage = sendRequestAndReceiveMessage(message);

        printResult(receivedMessage);

        return receivedMessage;
    }

    // Accept an invite from another user
    public OCMessage acceptInvite(String user, String inviter){
        System.out.println("Accepting an invitation from " + inviter);
        OCMessage message = new OCMessage();
        message.put("process", "invite response");
        message.put("response", "accept");
        message.put("inviter", inviter);
        message.put("invitee", user);

        OCMessage receivedMessage = sendRequestAndReceiveMessage(message);

        printResult(receivedMessage);

        return receivedMessage;
    }

    // Decline an invite from another user
    public OCMessage declineInvite(String user, String inviter){
        System.out.println("Accepting an invitation from " + inviter);
        OCMessage message = new OCMessage();
        message.put("process", "invite response");
        message.put("response", "decline");
        message.put("inviter", inviter);
        message.put("invitee", user);

        OCMessage receivedMessage = sendRequestAndReceiveMessage(message);

        printResult(receivedMessage);

        return receivedMessage;
    }

    // Get board data from server for a match
    public OCMessage getBoardData(int ID){
        System.out.println("Getting board data for match with ID="+ID);
        OCMessage message = new OCMessage();
        message.put("process", "get board data");
        message.put("ID", String.valueOf(ID));

        OCMessage receivedMessage = sendRequestAndReceiveMessage(message);

        printResult(receivedMessage);

        return receivedMessage;
    }

    // send move to be made on the server
    public OCMessage matchMove(int matchID, int[] fromPosition, int[] toPosition){
        System.out.println("Sending move from "+ Arrays.toString(fromPosition) +" to "+ Arrays.toString(toPosition) +" to the server");
        OCMessage message = new OCMessage();
        message.put("process", "match move");
        message.put("matchID", Integer.toString(matchID));
        message.put("fromRow", Integer.toString(fromPosition[0]));
        message.put("fromColumn", Integer.toString(fromPosition[1]));
        message.put("toRow", Integer.toString(toPosition[0]));
        message.put("toColumn", Integer.toString(toPosition[1]));

        // receive message
        OCMessage receivedMessage = sendRequestAndReceiveMessage(message);

        printResult(receivedMessage);

        return receivedMessage;
    }

    // request the matches a user can resume
    public OCMessage getResumeMatches(String nickname) {
        System.out.println("Sending get in-progress matches request for " + nickname);

        OCMessage message = new OCMessage();
        message.put("process", "get in-progress matches");
        message.put("nickname", nickname);

        // receive message
        OCMessage receivedMessage = sendRequestAndReceiveMessage(message);

        printResult(receivedMessage);

        return receivedMessage;
    }

    // Get current turn
    public OCMessage getTurn(int ID){

        OCMessage message = new OCMessage();
        message.put("process", "get turn");
        message.put("ID", String.valueOf(ID));

        // Received message
        OCMessage receivedMessage = sendRequestAndReceiveMessage(message);

        printResult(receivedMessage);

        return receivedMessage;
    }

    // End match
    public OCMessage endMatch(int ID, String winner, String loser){
        OCMessage message = new OCMessage(), receivedMessage;
        message.put("process", "end match");
        message.put("ID", String.valueOf(ID));
        message.put("winner", winner);
        message.put("loser", loser);

        receivedMessage = sendRequestAndReceiveMessage(message);

        printResult(receivedMessage);

        return receivedMessage;
    }

    // get game records
    public OCMessage getGameRecords(String nickname){
        OCMessage message = new OCMessage(), receivedMessage;
        message.put("process", "get game records");
        message.put("user", nickname);

        receivedMessage = sendRequestAndReceiveMessage(message);

        printResult(receivedMessage);

        return receivedMessage;
    }

    // request checkmate check
    public OCMessage getCheckmate(int matchID) {
        OCMessage message = new OCMessage(), receivedMessage;
        message.put("process", "checkmate check");
        message.put("ID", String.valueOf(matchID));

        receivedMessage = sendRequestAndReceiveMessage(message);

        printResult(receivedMessage);

        return receivedMessage;
    }
}
