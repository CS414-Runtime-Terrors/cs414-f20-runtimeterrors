package com.omegaChess;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalMoveException;
import com.omegaChess.exceptions.IllegalPositionException;
import com.omegaChess.pieces.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

@DisplayName("JUnit ChessBoard Class Test")
class TestChessBoard {

    @Test
    void test_createInstance() {
        ChessBoard board = new ChessBoard();
        String[] boardSlots = {"a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "a10",
                "b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8", "b9", "b10",
                "c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "c10",
                "d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9", "d10",
                "e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10",
                "f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9", "f10",
                "g1", "g2", "g3", "g4", "g5", "g6", "g7", "g8", "g9", "g10",
                "h1", "h2", "h3", "h4", "h5", "h6", "h7", "h8", "h9", "h10",
                "i1", "i2", "i3", "i4", "i5", "i6", "i7", "i8", "i9", "i10",
                "j1", "j2", "j3", "j4", "j5", "j6", "j7", "j8", "j9", "j10",
                "w1", "w2", "w3", "w4"};

        // have to test by that each piece is null
        // which will thereby test the getPiece and constructor methods
        for (String boardSlot : boardSlots) {
            try {
                assertNull(board.getPiece(boardSlot));
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void test_initialize() {
        ChessBoard board = new ChessBoard();

        // call the initialize function
        // this tests initialize as well as place piece and setPosition
        board.initialize();

        // test all of the white pieces have correct color and piece type
        try {
            assertEquals(board.getPiece("a1").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("a1") instanceof Champion);

            assertEquals(board.getPiece("b1").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("b1") instanceof Rook);

            assertEquals(board.getPiece("c1").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("c1") instanceof Knight);

            assertEquals(board.getPiece("d1").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("d1") instanceof Bishop);

            assertEquals(board.getPiece("e1").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("e1") instanceof Queen);

            assertEquals(board.getPiece("f1").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("f1") instanceof King);

            assertEquals(board.getPiece("g1").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("g1") instanceof Bishop);

            assertEquals(board.getPiece("h1").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("h1") instanceof Knight);

            assertEquals(board.getPiece("i1").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("i1") instanceof Rook);

            assertEquals(board.getPiece("j1").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("j1") instanceof Champion);

            assertEquals(board.getPiece("a2").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("a2") instanceof Pawn);

            assertEquals(board.getPiece("b2").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("b2") instanceof Pawn);

            assertEquals(board.getPiece("c2").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("c2") instanceof Pawn);

            assertEquals(board.getPiece("d2").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("d2") instanceof Pawn);

            assertEquals(board.getPiece("e2").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("e2") instanceof Pawn);

            assertEquals(board.getPiece("f2").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("f2") instanceof Pawn);

            assertEquals(board.getPiece("g2").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("g2") instanceof Pawn);

            assertEquals(board.getPiece("h2").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("h2") instanceof Pawn);

            assertEquals(board.getPiece("i2").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("i2") instanceof Pawn);

            assertEquals(board.getPiece("j2").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("j2") instanceof Pawn);

            assertEquals(board.getPiece("w1").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("w1") instanceof Wizard);

            assertEquals(board.getPiece("w2").getColor(), ChessPiece.Color.WHITE);
            assertTrue(board.getPiece("w2") instanceof Wizard);


            // test all of the black pieces have right color and piece type
            assertEquals(board.getPiece("a10").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("a10") instanceof Champion);

            assertEquals(board.getPiece("b10").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("b10") instanceof Rook);

            assertEquals(board.getPiece("c10").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("c10") instanceof Knight);

            assertEquals(board.getPiece("d10").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("d10") instanceof Bishop);

            assertEquals(board.getPiece("e10").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("e10") instanceof Queen);

            assertEquals(board.getPiece("f10").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("f10") instanceof King);

            assertEquals(board.getPiece("g10").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("g10") instanceof Bishop);

            assertEquals(board.getPiece("h10").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("h10") instanceof Knight);

            assertEquals(board.getPiece("i10").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("i10") instanceof Rook);

            assertEquals(board.getPiece("j10").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("j10") instanceof Champion);

            assertEquals(board.getPiece("a9").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("a9") instanceof Pawn);

            assertEquals(board.getPiece("b9").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("b9") instanceof Pawn);

            assertEquals(board.getPiece("c9").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("c9") instanceof Pawn);

            assertEquals(board.getPiece("d9").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("d9") instanceof Pawn);

            assertEquals(board.getPiece("e9").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("e9") instanceof Pawn);

            assertEquals(board.getPiece("f9").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("f9") instanceof Pawn);

            assertEquals(board.getPiece("g9").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("g9") instanceof Pawn);

            assertEquals(board.getPiece("h9").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("h9") instanceof Pawn);

            assertEquals(board.getPiece("i9").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("i9") instanceof Pawn);

            assertEquals(board.getPiece("j9").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("j9") instanceof Pawn);

            assertEquals(board.getPiece("w3").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("w3") instanceof Wizard);

            assertEquals(board.getPiece("w4").getColor(), ChessPiece.Color.BLACK);
            assertTrue(board.getPiece("w4") instanceof Wizard);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test_getPieceValid() {
        ChessBoard board = new ChessBoard();

        // call the initialize function
        board.initialize();

        try {
            assertTrue(board.getPiece("g9") instanceof Pawn);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test_getPieceInvalid() {
        ChessBoard board = new ChessBoard();

        // call the initialize function
        board.initialize();

        // test invalid letter character
        assertThrows(IllegalPositionException.class, () -> board.getPiece("k1"));

        // test invalid number
        assertThrows(IllegalPositionException.class, () -> board.getPiece("c12"));
    }

    @Test
    void test_placePiece() {
        ChessBoard board = new ChessBoard();

        King blackKing = new King(board, ChessPiece.Color.BLACK);

        // place black king at random spot on board
        board.placePiece(blackKing, "d5");

        try {
            assertEquals(blackKing, board.getPiece("d5"));
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test_move() {
        ChessBoard board = new ChessBoard();

        Pawn whitePawn = new Pawn(board, ChessPiece.Color.WHITE);

        // place black king at random spot on board
        board.placePiece(whitePawn, "d3");

        try {
            board.move("d3", "d4");
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }

        assertEquals("d4", whitePawn.getPosition());

        try {
            assertNull(board.getPiece("d3"));
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }

        // make sure illegal move exception is thrown
        assertThrows(IllegalMoveException.class, () -> board.move("d4", "d6"));
    }

}
