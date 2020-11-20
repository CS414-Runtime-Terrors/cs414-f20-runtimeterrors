package com.omegaChess;

import static org.junit.jupiter.api.Assertions.*;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;
import com.omegaChess.pieces.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

@DisplayName("JUnit Knight Class Test")
class TestKnight {

    @Test
    void test_createInstance() {
        ChessBoard board = new ChessBoard();

        Knight knight = new Knight(board, ChessPiece.Color.BLACK);

        assertNotNull(knight);
    }

    @Test
    void test_getColor() {
        ChessBoard board = new ChessBoard();

        Knight knight = new Knight(board, ChessPiece.Color.BLACK);

        assertEquals(knight.getColor(), ChessPiece.Color.BLACK);
    }

    @Test
    void test_position() {
        // test_position test both getPosition and setPosition
        ChessBoard board = new ChessBoard();

        Knight knight = new Knight(board, ChessPiece.Color.BLACK);

        try {
            knight.setPosition("e3");
        } catch (IllegalPositionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertEquals("e3", knight.getPosition());
    }

    @Test
    void test_toString() {
        ChessBoard board = new ChessBoard();

        Knight knight = new Knight(board, ChessPiece.Color.BLACK);

        assertEquals("\u265E", knight.toString());

        Knight knight_w = new Knight(board, ChessPiece.Color.WHITE);

        assertEquals("\u2658", knight_w.toString());
    }

    @Test
    void test_legalMoves() {
        ChessBoard board = new ChessBoard();

        Knight knight = new Knight(board, ChessPiece.Color.BLACK);
        King king = new King(board, ChessPiece.Color.BLACK);

        board.placePiece(knight,  "f5");
        board.placePiece(king, "j2");

        // test 1 - no blocking pieces
        ArrayList<String> validMoves = new ArrayList<>();
        validMoves.add("d6");
        validMoves.add("e7");
        validMoves.add("g7");
        validMoves.add("h6");
        validMoves.add("h4");
        validMoves.add("g3");
        validMoves.add("d4");
        validMoves.add("e3");

        // get kings valid moves
        LegalMoves moves = knight.legalMoves(true, false);
        ArrayList<String> knightValid = moves.getListOfMoves();

        // Sort in case they come in a different order
        Collections.sort(validMoves);
        Collections.sort(knightValid);

        assertEquals(validMoves, knightValid);

        // test 2 - friend piece in the way
        Pawn pawn = new Pawn(board, ChessPiece.Color.BLACK);
        board.placePiece(pawn, "g3");
        
        validMoves.clear();
        validMoves.add("d6");
        validMoves.add("e7");
        validMoves.add("g7");
        validMoves.add("h6");
        validMoves.add("h4");
        validMoves.add("d4");
        validMoves.add("e3");

        // get kings valid moves
        moves = knight.legalMoves(true, false);
        knightValid = moves.getListOfMoves();

        // Sort in case they come in a different order
        Collections.sort(validMoves);
        Collections.sort(knightValid);

        assertEquals(validMoves, knightValid);
        
    }

}
