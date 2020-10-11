package com.omegaChess;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;
import com.omegaChess.pieces.ChessPiece;
import com.omegaChess.pieces.King;
import com.omegaChess.pieces.LegalMoves;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("JUnit King Class Test")
class TestKing {

    @Test
    void test_createInstance() {
        ChessBoard board = new ChessBoard();

        King king = new King(board, ChessPiece.Color.BLACK);

        assertNotNull(king);
    }

    @Test
    void test_getColor() {
        ChessBoard board = new ChessBoard();

        King king = new King(board, ChessPiece.Color.BLACK);

        assertEquals(king.getColor(), ChessPiece.Color.BLACK);
    }

    @Test
    void test_position() {
        // test_position test both getPosition and setPosition
        ChessBoard board = new ChessBoard();

        King king = new King(board, ChessPiece.Color.BLACK);

        try {
            king.setPosition("d8");
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }

        assertEquals("d8", king.getPosition());
    }

    @Test
    void test_toString() {
        ChessBoard board = new ChessBoard();

        King king = new King(board, ChessPiece.Color.BLACK);

        assertEquals("\u265A", king.toString());

        King king_w = new King(board, ChessPiece.Color.WHITE);

        assertEquals("\u2654", king_w.toString());
    }


    @Test
    void test_legalMoves() {
        ChessBoard board = new ChessBoard();

        King king = new King(board, ChessPiece.Color.BLACK);

        board.placePiece(king,  "e1");


        // valid moves list for king in e1 position
        ArrayList<String> validMoves = new ArrayList<>();
        validMoves.add("d1");
        validMoves.add("d2");
        validMoves.add("e2");
        validMoves.add("f2");
        validMoves.add("f1");

        // get kings valid moves
        LegalMoves moves = king.legalMoves();
        ArrayList<String> kingValid = moves.getListOfMoves();

        // Sort in case they come in a different order
        Collections.sort(validMoves);
        Collections.sort(kingValid);

        assertEquals(validMoves, kingValid);

        validMoves.clear();

        board.placePiece(king, "d5");

        // new list for if king is in d5 position
        validMoves.add("c5");
        validMoves.add("c6");
        validMoves.add("d6");
        validMoves.add("e6");
        validMoves.add("e5");
        validMoves.add("e4");
        validMoves.add("d4");
        validMoves.add("c4");

        // get kings valid moves
        moves = king.legalMoves();
        kingValid = moves.getListOfMoves();

        // Sort in case they come in a different order
        Collections.sort(validMoves);
        Collections.sort(kingValid);


        assertEquals(validMoves, kingValid);
    }

}
