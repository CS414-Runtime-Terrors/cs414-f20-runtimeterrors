package com.omegaChess.exceptions;

@SuppressWarnings("serial")
public class IllegalPositionException extends Exception {

    public IllegalPositionException(String errorMessage) {
        super(errorMessage);
    }
}
