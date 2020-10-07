package com.omegaChess;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;
import com.omegaChess.pieces.ChessPiece;
import com.omegaChess.pieces.Knight;
import com.omegaChess.pieces.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("JUnit Rook Class Test")
class TestRook {

    @Test
    void test_createInstance() {
        ChessBoard board = new ChessBoard();

        Rook rook = new Rook(board, ChessPiece.Color.BLACK);

        assertNotNull(rook);
    }

    @Test
    void test_getColor() {
        ChessBoard board = new ChessBoard();

        Rook rook = new Rook(board, ChessPiece.Color.BLACK);

        assertEquals(rook.getColor(), ChessPiece.Color.BLACK);
    }

    @Test
    void test_position() {
        // test_position test both getPosition and setPosition
        ChessBoard board = new ChessBoard();

        Rook rook = new Rook(board, ChessPiece.Color.BLACK);

        try {
            rook.setPosition("e3");
        } catch (IllegalPositionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertEquals("e3", rook.getPosition());
    }

    @Test
    void test_toString() {
        ChessBoard board = new ChessBoard();

        Rook rook = new Rook(board, ChessPiece.Color.BLACK);

        assertEquals("\u265C", rook.toString());

        Rook rook_w = new Rook(board, ChessPiece.Color.WHITE);

        assertEquals("\u2656", rook_w.toString());
    }
    @Test
    void test_legalMoves() {
        ChessBoard board = new ChessBoard();

        Rook rook = new Rook(board, ChessPiece.Color.BLACK);
        Knight knight = new Knight(board, ChessPiece.Color.WHITE);

        board.placePiece(rook, "f2");

        // test 1 - no blocking pieces
        ArrayList<String> validMoves = new ArrayList<String>();
        validMoves.add("g2");
        validMoves.add("h2");
        validMoves.add("e2");
        validMoves.add("d2");
        validMoves.add("c2");
        validMoves.add("b2");
        validMoves.add("a2");
        validMoves.add("f1");
        validMoves.add("f3");
        validMoves.add("f4");
        validMoves.add("f5");
        validMoves.add("f6");
        validMoves.add("f7");
        validMoves.add("f8");

        ArrayList<String> rookValid = rook.legalMoves();
        Collections.sort(validMoves);
        Collections.sort(rookValid);

        assertEquals(validMoves, rookValid);

        // test 2 - blocking piece vertical (piece can be captured)
        board = new ChessBoard();

        rook = new Rook(board, ChessPiece.Color.BLACK);
        knight = new Knight(board, ChessPiece.Color.WHITE);

        board.placePiece(rook, "f2");
        board.placePiece(knight, "f3");

        validMoves.clear();
        validMoves.add("g2");
        validMoves.add("h2");
        validMoves.add("e2");
        validMoves.add("d2");
        validMoves.add("c2");
        validMoves.add("b2");
        validMoves.add("a2");
        validMoves.add("f1");
        validMoves.add("f3");

        rookValid = rook.legalMoves();
        Collections.sort(validMoves);
        Collections.sort(rookValid);

        assertEquals(validMoves, rookValid);

        // test 3 - blocking piece 3 vertical (can't be captured)
        // and blocking piece
        board = new ChessBoard();

        rook = new Rook(board, ChessPiece.Color.BLACK);
        knight = new Knight(board, ChessPiece.Color.BLACK);
        Knight knight2 = new Knight(board, ChessPiece.Color.WHITE);

        board.placePiece(rook, "f2");
        board.placePiece(knight, "f5");
        board.placePiece(knight, "h2");
        board.placePiece(knight2, "c2");

        validMoves.clear();
        validMoves.add("g2");
        validMoves.add("e2");
        validMoves.add("d2");
        validMoves.add("c2");
        validMoves.add("f1");
        validMoves.add("f3");
        validMoves.add("f4");

        rookValid = rook.legalMoves();
        Collections.sort(validMoves);
        Collections.sort(rookValid);

        assertEquals(validMoves, rookValid);
    }

}
