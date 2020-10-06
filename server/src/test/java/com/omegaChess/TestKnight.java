package com.omegaChess;

import static org.junit.jupiter.api.Assertions.*;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;
import com.omegaChess.pieces.ChessPiece;
import com.omegaChess.pieces.Knight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        // knights have no legal moves
        assertEquals(0, knight.legalMoves().size());
    }

}
