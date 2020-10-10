package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;

import java.util.ArrayList;

public class InvalidSpace extends ChessPiece{

    public InvalidSpace(ChessBoard board, ChessPiece.Color color){super(board, null);}

    @Override
    public String getPosition() {
        try {
            throw new IllegalPositionException("This is an invalid space between the wizards");
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setPosition(String position) throws IllegalPositionException{
        throw new IllegalPositionException("This is an invalid space between the wizards");
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public ArrayList<String> legalMoves() {
        return null;
    }
}