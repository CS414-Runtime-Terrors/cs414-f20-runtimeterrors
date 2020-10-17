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
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String userInput;

            // this is the client loop, writing to the socket's output stream every time something is inputted into standard input.
            // we will want to change this and integrate it with the graphical code so that we can write the socket's output stream
            // every time something is clicked on.
            while ((userInput = stdIn.readLine()) != null) {

                // plan: have a JSON object with field "process":"squareInput" (as an example) and "number":in.readLine() and pass this

                out.println(userInput); // TODO: rather than pass a simple string, write a JSON object converted to a string to the socket's output stream
                System.out.println(in.readLine());

            }

        } catch (UnknownHostException e) {
            System.out.println("Host unknown");
        } catch (IOException e) {
            System.out.println("Something went wrong with the I/O connection.");
        }

    }

}
