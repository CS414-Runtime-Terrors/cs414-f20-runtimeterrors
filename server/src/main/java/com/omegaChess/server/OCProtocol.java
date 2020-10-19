package com.omegaChess.server;

// this class is responsible for actually processing any input from a client
public class OCProtocol {

    private OCServerData data = new OCServerData();

    // this method is responsible for deciding how to process the input
    public String processInput(String input) {
        String option = "squareInput"; // TODO: grab this option from a field in a passed JSON object string after performing a toJSON() call on the string.

        switch(option) {
            case "squareInput":
                return squareInput(input);
        }
        return null;
    }

    // this is an example method for processing the input, where the input is expected to be a number and the output is number*number
    private String squareInput(String inputLine) {
        int number;

        try {
            number = Integer.parseInt(inputLine);
        } catch (Exception e) {
            return "Wrong input!";
        }

        int square = number * number;

        return "Square of " + number + " is " + square;
    }
}
