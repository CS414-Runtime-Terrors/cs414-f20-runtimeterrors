package com.omegaChess;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.pieces.*;

/* Test class for the getNormalOrCheckMoves chess piece method and any other methods that
* can't be tested in an individual subclass. */
public class TestChessPiece {
    @Test
    public void testGetMoves() {
        ChessBoard board = new ChessBoard();
        ArrayList<String> validMoves = new ArrayList<>();
        King wKing = new King(board, ChessPiece.Color.WHITE);
        Pawn wPawn = new Pawn(board, ChessPiece.Color.WHITE);
        Pawn wPawn2 = new Pawn(board, ChessPiece.Color.WHITE);
        Pawn wPawn3 = new Pawn(board, ChessPiece.Color.WHITE);
        Pawn wPawn4 = new Pawn(board, ChessPiece.Color.WHITE);
        Pawn wPawn5 = new Pawn(board, ChessPiece.Color.WHITE);
        Bishop bBishop = new Bishop(board, ChessPiece.Color.BLACK);

        //Not in check
        board.placePiece(wKing, "g3");
        board.placePiece(wPawn, "c2");

        validMoves.add("c3");
        validMoves.add("c4");
        validMoves.add("c5");
        assertEquals(validMoves, wPawn.getNormalOrCheckMoves().getListOfMoves());

        //In Check, cannot capture/block
        board.placePiece(bBishop, "c7");
        board.placePiece(wPawn2, "d4");
        wPawn2.setMoved(true);

        validMoves.clear();
        assertEquals(validMoves, wPawn.getNormalOrCheckMoves().getListOfMoves());
        assertEquals(validMoves, wPawn2.getNormalOrCheckMoves().getListOfMoves());

        //In Check, can block
        board.placePiece(wPawn3, "f2");

        validMoves.clear();
        validMoves.add("f4");
        assertEquals(validMoves, wPawn3.getNormalOrCheckMoves().getListOfMoves());

        board.placePiece(wPawn4, "e4");
        wPawn4.setMoved(true);

        validMoves.clear();
        validMoves.add("e5");
        assertEquals(validMoves, wPawn4.getNormalOrCheckMoves().getListOfMoves());

        //In Check, can capture
        board.placePiece(wPawn5, "b6");

        validMoves.clear();
        validMoves.add("c7");
        assertEquals(validMoves, wPawn5.getNormalOrCheckMoves().getListOfMoves());
    }
}
