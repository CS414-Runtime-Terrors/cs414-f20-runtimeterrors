package com.omegaChess.exceptions;

@SuppressWarnings("serial")
public class IllegalMoveException extends java.lang.Exception {
    public IllegalMoveException(String errorMessage) {
        super(errorMessage);
    }
}
