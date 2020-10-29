package com.omegaChess.server;

import java.io.IOException;
import java.net.ServerSocket;

public class OCMultiServer {

    public static void main(String[] args) throws IOException {

        OCServerData serverData = new OCServerData();

        int portNumber = 8484;

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
