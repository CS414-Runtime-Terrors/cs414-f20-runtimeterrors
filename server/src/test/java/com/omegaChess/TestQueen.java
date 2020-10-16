package com.omegaChess;

import static org.junit.jupiter.api.Assertions.*;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;
import com.omegaChess.pieces.Bishop;
import com.omegaChess.pieces.ChessPiece;
import com.omegaChess.pieces.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

@DisplayName("JUnit Queen Class Test")
class TestQueen {

    @Test
    void test_createInstance() {
        ChessBoard board = new ChessBoard();

        Queen queen = new Queen(board, ChessPiece.Color.BLACK);

        assertNotNull(queen);
    }

    @Test
    void test_getColor() {
        ChessBoard board = new ChessBoard();

        Queen queen = new Queen(board, ChessPiece.Color.BLACK);

        assertEquals(queen.getColor(), ChessPiece.Color.BLACK);
    }

    @Test
    void test_position() {
        // test_position test both getPosition and setPosition
        ChessBoard board = new ChessBoard();

        Queen queen = new Queen(board, ChessPiece.Color.BLACK);

        try {
            queen.setPosition("e3");
        } catch (IllegalPositionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertEquals("e3", queen.getPosition());
    }

    @Test
    void test_toString() {
        ChessBoard board = new ChessBoard();

        Queen queen = new Queen(board, ChessPiece.Color.BLACK);

        assertEquals("\u265B", queen.toString());

        Queen queen_w = new Queen(board, ChessPiece.Color.WHITE);

        assertEquals("\u2655", queen_w.toString());
    }

    @Test
    void test_legalMoves() {
        ChessBoard board = new ChessBoard();

        Queen queen = new Queen(board, ChessPiece.Color.BLACK);

        board.placePiece(queen, "e5");

        // test 1 - no blocking pieces
        ArrayList<String> validMoves = new ArrayList<>();
        validMoves.add("d6");
        validMoves.add("c7");
        validMoves.add("b8");
        validMoves.add("a9");
        validMoves.add("e6");
        validMoves.add("e7");
        validMoves.add("e8");
        validMoves.add("e9");
        validMoves.add("e10");
        validMoves.add("f6");
        validMoves.add("g7");
        validMoves.add("h8");
        validMoves.add("i9");
        validMoves.add("j10");
        validMoves.add("f5");
        validMoves.add("g5");
        validMoves.add("h5");
        validMoves.add("i5");
        validMoves.add("j5");
        validMoves.add("f4");
        validMoves.add("g3");
        validMoves.add("h2");
        validMoves.add("i1");
        validMoves.add("e4");
        validMoves.add("e3");
        validMoves.add("e2");
        validMoves.add("e1");
        validMoves.add("d4");
        validMoves.add("c3");
        validMoves.add("b2");
        validMoves.add("a1");
        validMoves.add("d5");
        validMoves.add("c5");
        validMoves.add("b5");
        validMoves.add("a5");

        ArrayList<String> bishopValid = queen.legalMoves();
        Collections.sort(validMoves);
        Collections.sort(bishopValid);

        assertEquals(validMoves, bishopValid);
    }

}
