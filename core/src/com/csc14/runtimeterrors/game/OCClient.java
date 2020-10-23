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

    public static void main(String[] args) throws IOException {

        String hostName = localHostName;
        int portNumber = 28362;

        try {

            Socket socket = new Socket(hostName, portNumber);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            sendSquareRequest("10", out, in);

        } catch (UnknownHostException e) {
            System.out.println("Host unknown");
        } catch (IOException e) {
            System.out.println("Something went wrong with the I/O connection.");
        }

    }

    private static void sendSquareRequest(String number, PrintWriter out, BufferedReader in) {
        try {
            OCMessage message = new OCMessage();
            message.put("process", "square");
            message.put("number", "" + number);

            // send request
            out.println(message.toString());

            // receive message
            OCMessage receivedMessage = new OCMessage();
            receivedMessage.fromString(in.readLine());

            // print answer
            String success = (String) receivedMessage.get("success");

            if (success.equals("true")) {
                System.out.println(receivedMessage.get("answer"));
            }
            else {
                System.out.println("Reason: " + receivedMessage.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
