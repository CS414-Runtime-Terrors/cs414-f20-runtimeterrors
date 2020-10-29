package com.omegaChess.server;

import java.io.IOException;
import java.net.ServerSocket;

public class OCMultiServer {

    public static void main(String[] args) throws IOException {

        OCServerData serverData = new OCServerData();

        if (args.length == 0) {
            System.out.println("Missing port number as first argument.");
            return;
        }

        int portNumber = -1;

        try {
            portNumber = Integer.parseInt(args[0]);
        } catch(Exception e) {
            System.out.println("Incorrect argument! Expecting an integer to use as port number.");
        }

        boolean listening = true;

        try {
            System.out.println("Creating Socket");
            ServerSocket serverSocket = new ServerSocket(portNumber);


            while (listening) {
                System.out.println("Listening...");
                new OCMultiServerThread(serverSocket.accept(), serverData).start();
                System.out.println("New connection!");
            }

        } catch(Exception e) {
            System.out.println("Error: Could not listen on port " + portNumber);
        }

    }

}
