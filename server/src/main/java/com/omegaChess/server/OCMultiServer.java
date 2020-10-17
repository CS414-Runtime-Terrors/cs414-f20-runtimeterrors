package com.omegaChess.server;

import java.io.IOException;
import java.net.ServerSocket;

public class OCMultiServer {

    public static void main(String[] args) throws IOException {

        int portNumber = 28362;

        boolean listening = true;

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);

            while (listening) {
                new OCMultiServerThread(serverSocket.accept()).start();
            }

        } catch(Exception e) {
            System.out.println("Error: Could not listen on port " + portNumber);
        }

    }

}
