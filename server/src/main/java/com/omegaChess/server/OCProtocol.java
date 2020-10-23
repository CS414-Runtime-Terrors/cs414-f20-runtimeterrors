package com.omegaChess.server;

// this class is responsible for actually processing any input from a client
public class OCProtocol {

    OCServerData serverData = new OCServerData();

    public String processInput(String input) {
        String toReturn = "";
        try {
            OCMessage receivedMessage = new OCMessage();
            receivedMessage.fromString(input);

            String process = (String) receivedMessage.get("process");

            switch(process) {
                case "square":
                    toReturn = squareInput(receivedMessage);
                    break;
                case "register":
                    toReturn = registerUser(receivedMessage);
                    break;
                default:
                    OCMessage message = new OCMessage();
                    message.put("success", "false");
                    message.put("reason", "process not recognized");

                    toReturn = message.toString();
                    break;
            }
        } catch (Exception e) {
            OCMessage message = new OCMessage();
            message.put("success", "false");
            message.put("reason", "Something went wrong when processing input.");

            toReturn = message.toString();

            System.out.println("Something went wrong when processing input.");
        }

        return toReturn;
    }

    private String squareInput(OCMessage receivedMessage) {
        String inputLine = (String) receivedMessage.get("number");

        System.out.println("Attempting to square " + inputLine + "...");
        int number;

        try {
            number = Integer.parseInt(inputLine);
        } catch (Exception e) {
            OCMessage message = new OCMessage();
            message.put("success", "false");
            message.put("reason", "Wrong input!");

            return message.toString();
        }

        int square = number * number;
        System.out.println("Square: " + square);


        OCMessage message = new OCMessage();
        message.put("success", "true");
        message.put("answer", "Square of " + number + " is " + square);

        return message.toString();
    }

    private String registerUser(OCMessage receivedMessage) {

        String email = (String) receivedMessage.get("email");
        String nickname = (String) receivedMessage.get("nickname");
        String password = (String) receivedMessage.get("password");

        System.out.println("Attempting to register new user: " + nickname);

        Boolean success = serverData.createProfile(nickname, password, email);

        if (success) {
            OCMessage message = new OCMessage();
            message.put("success", "true");

            System.out.println("Registered!");

            return message.toString();
        }
        else {
            OCMessage message = new OCMessage();
            message.put("success", "false");
            message.put("reason", "nickname was taken");

            System.out.println("Something went wrong. Nickname was likely taken.");

            return message.toString();
        }
    }

}
