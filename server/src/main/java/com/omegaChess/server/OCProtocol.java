package com.omegaChess.server;

// this class is responsible for actually processing any input from a client
public class OCProtocol {

    private OCServerData serverData;

    public OCProtocol(OCServerData data) {
        serverData = data;
    }

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
                case "unregister":
                    toReturn = unregisterUser(receivedMessage);
                    break;
                case "login":
                    toReturn = loginUser(receivedMessage);
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

        OCMessage message = new OCMessage();
        if (success) {
            message.put("success", "true");

            System.out.println("Registered!");

        }
        else {
            message.put("success", "false");
            message.put("reason", "nickname/email was taken");

            System.out.println("Nickname or email was taken.");

        }
        return message.toString();
    }

    private String unregisterUser(OCMessage receivedMessage) {

        String nickname = (String) receivedMessage.get("nickname");

        System.out.println("Attempting to unregister user: " + nickname);

        Boolean success = serverData.removeProfile(nickname);

        OCMessage message = new OCMessage();
        if (success) {
            message.put("success", "true");

            System.out.println("Unregistered!");

        }
        else {
            message.put("success", "false");
            message.put("reason", "nickname wasn't found");

            System.out.println("Nickname wasn't found.");

        }
        return message.toString();
    }

    private String loginUser(OCMessage receivedMessage) {

        String nickname = (String) receivedMessage.get("nickname");
        String password = (String) receivedMessage.get("password");

        System.out.println("Attempting to login user: " + nickname);

        OCMessage message = new OCMessage();

        if (!serverData.profileExists(nickname)) {
            // profile doesn't exist
            message.put("success", "false");
            message.put("reason", "nickname wasn't found");

            System.out.println("Nickname wasn't found.");
            return message.toString();
        }

        Boolean success = serverData.checkPassword(nickname, password);

        if (success) {
            message.put("success", "true");

            System.out.println("Logged in!");

        }
        else {
            message.put("success", "false");
            message.put("reason", "wrong password");

            System.out.println("Wrong password.");

        }
        return message.toString();
    }

}
