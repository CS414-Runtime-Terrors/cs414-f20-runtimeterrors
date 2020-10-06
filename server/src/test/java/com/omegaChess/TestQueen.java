package com.omegaChess;

import static org.junit.jupiter.api.Assertions.*;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;
import com.omegaChess.pieces.ChessPiece;
import com.omegaChess.pieces.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        // queens have no legal moves
        assertEquals(0, queen.legalMoves().size());
    }

}
