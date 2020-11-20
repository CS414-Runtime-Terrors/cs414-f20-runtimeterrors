package com.omegaChess;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;
import com.omegaChess.pieces.Bishop;
import com.omegaChess.pieces.ChessPiece;
import com.omegaChess.pieces.King;
import com.omegaChess.pieces.LegalMoves;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("JUnit Bishop Class Test")
class TestBishop {
    @Test
    void test_createInstance() {
        ChessBoard board = new ChessBoard();

        Bishop bishop = new Bishop(board, ChessPiece.Color.BLACK);

        assertNotNull(bishop);
    }

    @Test
    void test_getColor() {
        ChessBoard board = new ChessBoard();

        Bishop bishop = new Bishop(board, ChessPiece.Color.BLACK);

        assertEquals(bishop.getColor(), ChessPiece.Color.BLACK);
    }

    @Test
    void test_position() {
        // test_position test both getPosition and setPosition
        ChessBoard board = new ChessBoard();

        Bishop bishop = new Bishop(board, ChessPiece.Color.BLACK);

        try {
            bishop.setPosition("h5");
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }

        assertEquals("h5", bishop.getPosition());
    }

    @Test
    void test_toString() {
        ChessBoard board = new ChessBoard();

        Bishop bishop = new Bishop(board, ChessPiece.Color.BLACK);

        assertEquals("\u265D", bishop.toString());

        Bishop bishop_w = new Bishop(board, ChessPiece.Color.WHITE);

        assertEquals("\u2657", bishop_w.toString());
    }

    @Test
    void test_legalMoves() {
        ChessBoard board = new ChessBoard();

        Bishop bishop = new Bishop(board, ChessPiece.Color.BLACK);
        King king = new King(board, ChessPiece.Color.BLACK);

        board.placePiece(bishop, "d4");
        board.placePiece(king, "j2");

        // test 1 - no blocking pieces
        ArrayList<String> validMoves = new ArrayList<>();
        validMoves.add("e3");
        validMoves.add("f2");
        validMoves.add("g1");
        validMoves.add("e5");
        validMoves.add("f6");
        validMoves.add("g7");
        validMoves.add("h8");
        validMoves.add("i9");
        validMoves.add("j10");
        validMoves.add("c3");
        validMoves.add("b2");
        validMoves.add("a1");
        validMoves.add("c5");
        validMoves.add("b6");
        validMoves.add("a7");

        LegalMoves moves = bishop.legalMoves(true, false);
        ArrayList<String> bishopValid = moves.getListOfMoves();
        Collections.sort(validMoves);
        Collections.sort(bishopValid);

        assertEquals(validMoves, bishopValid);
    }

    @Test
    public void testMovesToBlockCheckingPiece() {
        ChessBoard board = new ChessBoard();
        Bishop bishop = new Bishop(board, ChessPiece.Color.BLACK);
        board.placePiece(bishop, "f5");
        ArrayList<String> validMoves = new ArrayList<>();

        validMoves.add("f5");
        validMoves.add("g4");
        validMoves.add("h3");
        assertEquals(validMoves, bishop.movesToBlockCheckingPiece("i2").getListOfMoves());

        validMoves.clear();
        validMoves.add("f5");
        validMoves.add("g6");
        assertEquals(validMoves, bishop.movesToBlockCheckingPiece("h7").getListOfMoves());

        validMoves.clear();
        validMoves.add("f5");
        assertEquals(validMoves, bishop.movesToBlockCheckingPiece("e6").getListOfMoves());
    }
}
