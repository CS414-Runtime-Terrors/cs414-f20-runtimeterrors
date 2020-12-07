package com.omegaChess;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalMoveException;
import com.omegaChess.exceptions.IllegalPositionException;
import com.omegaChess.pieces.*;
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

        assertEquals("blackKing.png", king.toString());

        King king_w = new King(board, ChessPiece.Color.WHITE);

        assertEquals("whiteKing.png", king_w.toString());
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
        LegalMoves moves = king.legalMoves(true, false);
        ArrayList<String> kingValid = moves.getListOfMoves();

        // Sort in case they come in a different order
        Collections.sort(validMoves);
        Collections.sort(kingValid);

        assertEquals(validMoves, kingValid);

        board = new ChessBoard();
        validMoves.clear();

        board.placePiece(king, "d5");
        king.setMoved(true); //king is not in its staring position

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
        moves = king.legalMoves(true, false);
        kingValid = moves.getListOfMoves();

        // Sort in case they come in a different order
        Collections.sort(validMoves);
        Collections.sort(kingValid);

        assertEquals(validMoves, kingValid);


        validMoves.clear();
        board = new ChessBoard();
        board.initialize();

        // make e positions empty so queen at e10 could capture if
        // king moved to e2
        board.placePiece(null, "e9");
        board.placePiece(null, "e2");

        // make some positions null to give king opportunity to move
        board.placePiece(null, "f2");
        board.placePiece(null, "g2");
        board.placePiece(null, "g1");

        // e2 and e9 should be null now
        try {
            assertNull(board.getPiece("e2"));
            assertNull(board.getPiece("e9"));
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }

        // get the king at f1
        ChessPiece king1 = null;
        try {
            king1 = board.getPiece("f1");
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }

        // new list for if king is in f1 with opponent queen in e10
        validMoves.add("f2");
        validMoves.add("g2");
        validMoves.add("g1");

        // get kings valid moves
        assert king1 != null;
        kingValid = king1.legalMoves(true, false).getListOfMoves();

        // Sort in case they come in a different order
        Collections.sort(validMoves);
        Collections.sort(kingValid);

        assertEquals(validMoves, kingValid);

        // test in check, can only capture to leave check, and checking piece is protected
        board = new ChessBoard();
        validMoves.clear();
        king = new King(board, ChessPiece.Color.WHITE);
        King bKing = new King(board, ChessPiece.Color.BLACK);
        Queen bQueen = new Queen(board, ChessPiece.Color.BLACK);
        Rook bRook = new Rook(board, ChessPiece.Color.BLACK);

        board.placePiece(king, "f1");
        board.placePiece(bKing, "f10");
        board.placePiece(bQueen, "f2");
        board.placePiece(bRook, "f6");

        kingValid = king.legalMoves(true, false).getListOfMoves();
        assertEquals(validMoves, kingValid);

        board = new ChessBoard();
        king = new King(board, ChessPiece.Color.WHITE);
        bKing = new King(board, ChessPiece.Color.BLACK);
        bQueen = new Queen(board, ChessPiece.Color.BLACK);
        Bishop bBishop = new Bishop(board, ChessPiece.Color.BLACK);

        board.placePiece(king, "f1");
        board.placePiece(bKing, "f10");
        board.placePiece(bQueen, "f2");
        board.placePiece(bBishop, "h4");

        kingValid = king.legalMoves(true, false).getListOfMoves();
        assertEquals(validMoves, kingValid);

        board = new ChessBoard();
        king = new King(board, ChessPiece.Color.WHITE);
        bKing = new King(board, ChessPiece.Color.BLACK);
        bQueen = new Queen(board, ChessPiece.Color.BLACK);
        Pawn bPawn = new Pawn(board, ChessPiece.Color.BLACK);

        board.placePiece(king, "f1");
        board.placePiece(bKing, "f10");
        board.placePiece(bQueen, "f2");
        board.placePiece(bPawn, "g3");

        kingValid = king.legalMoves(true, false).getListOfMoves();
        assertEquals(validMoves, kingValid);

        board = new ChessBoard();
        king = new King(board, ChessPiece.Color.WHITE);
        bKing = new King(board, ChessPiece.Color.BLACK);
        bQueen = new Queen(board, ChessPiece.Color.BLACK);
        Knight bKnight = new Knight(board, ChessPiece.Color.BLACK);

        board.placePiece(king, "f1");
        board.placePiece(bKing, "f10");
        board.placePiece(bQueen, "f2");
        board.placePiece(bKnight, "g4");

        kingValid = king.legalMoves(true, false).getListOfMoves();
        assertEquals(validMoves, kingValid);

        board = new ChessBoard();
        king = new King(board, ChessPiece.Color.WHITE);
        bKing = new King(board, ChessPiece.Color.BLACK);
        bQueen = new Queen(board, ChessPiece.Color.BLACK);
        Wizard bWizard = new Wizard(board, ChessPiece.Color.BLACK);

        board.placePiece(king, "f1");
        board.placePiece(bKing, "f10");
        board.placePiece(bQueen, "f2");
        board.placePiece(bWizard, "g5");

        kingValid = king.legalMoves(true, false).getListOfMoves();
        assertEquals(validMoves, kingValid);

        board = new ChessBoard();
        king = new King(board, ChessPiece.Color.WHITE);
        bKing = new King(board, ChessPiece.Color.BLACK);
        bQueen = new Queen(board, ChessPiece.Color.BLACK);
        Champion bChamp = new Champion(board, ChessPiece.Color.BLACK);

        board.placePiece(king, "f1");
        board.placePiece(bKing, "f10");
        board.placePiece(bQueen, "f2");
        board.placePiece(bChamp, "h4");

        kingValid = king.legalMoves(true, false).getListOfMoves();
        assertEquals(validMoves, kingValid);

        board = new ChessBoard();
        king = new King(board, ChessPiece.Color.WHITE);
        bKing = new King(board, ChessPiece.Color.BLACK);
        bQueen = new Queen(board, ChessPiece.Color.BLACK);
        Queen bQueen2 = new Queen(board, ChessPiece.Color.BLACK);

        board.placePiece(king, "f1");
        board.placePiece(bKing, "f10");
        board.placePiece(bQueen, "f2");
        board.placePiece(bQueen2, "h4");

        kingValid = king.legalMoves(true, false).getListOfMoves();
        assertEquals(validMoves, kingValid);

        // test capture into check
        board = new ChessBoard();
        validMoves.clear();
        king = new King(board, ChessPiece.Color.WHITE);
        Pawn wPawn = new Pawn(board, ChessPiece.Color.WHITE);
        Pawn wPawn2 = new Pawn(board, ChessPiece.Color.WHITE);
        bKing = new King(board, ChessPiece.Color.BLACK);
        bPawn = new Pawn(board, ChessPiece.Color.BLACK);
        bRook = new Rook(board, ChessPiece.Color.BLACK);

        board.placePiece(king, "f1");
        board.placePiece(wPawn, "e2");
        board.placePiece(wPawn2, "g2");
        board.placePiece(bKing, "f10");
        board.placePiece(bPawn, "f2");
        board.placePiece(bRook, "f6");

        kingValid = king.legalMoves(true, false).getListOfMoves();
        assertEquals(validMoves, kingValid);
    }

    @Test
    void test_castling() {
        ChessBoard board1 = new ChessBoard(); //board for queen side castle
        ChessBoard board2 = new ChessBoard(); //board for king side castle

        // initialize pieces
        King blackKing1 = new King(board1, ChessPiece.Color.BLACK);
        Rook blackRook1 = new Rook(board1, ChessPiece.Color.BLACK);
        King blackKing2 = new King(board2, ChessPiece.Color.BLACK);
        Rook blackRook2 = new Rook(board2, ChessPiece.Color.BLACK);
        King whiteKing1 = new King(board1, ChessPiece.Color.WHITE);
        Rook whiteRook1 = new Rook(board1, ChessPiece.Color.WHITE);
        King whiteKing2 = new King(board2, ChessPiece.Color.WHITE);
        Rook whiteRook2 = new Rook(board2, ChessPiece.Color.WHITE);

        // place pieces
        board1.placePiece(blackKing1, "f10");
        board1.placePiece(blackRook1, "b10");
        board1.placePiece(whiteKing1, "f1");
        board1.placePiece(whiteRook1, "b1");
        board2.placePiece(blackKing2, "f10");
        board2.placePiece(blackRook2, "i10");
        board2.placePiece(whiteKing2, "f1");
        board2.placePiece(whiteRook2, "i1");
        // place pawns to block rooks from preventing castle
        board1.placePiece(new Pawn(board1, ChessPiece.Color.BLACK), "e5");
        board2.placePiece(new Pawn(board2, ChessPiece.Color.BLACK), "g5");

        // test queen side castle
        try {
            board1.move("f10", "d10"); //black castles queen side
            board1.move("f1", "d1"); //white castles queen side
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }
        try {
            assertSame(board1.getPiece("d10"), blackKing1); //test black king's new position
            assertSame(board1.getPiece("d1"), whiteKing1); //test white king's new position
            assertSame(board1.getPiece("e10"), blackRook1); //test black rook's new position
            assertSame(board1.getPiece("e1"), whiteRook1); //test white rook's new position
            assertNull(board1.getPiece("f10")); //test black king's old position
            assertNull(board1.getPiece("f1")); //test white king's old position
            assertNull(board1.getPiece("b10")); //test black rook's old position
            assertNull(board1.getPiece("b1")); //test white rook's old position
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }

        // test king side castle
        try {
            board2.move("f10", "h10"); //black castles king side
            board2.move("f1", "h1"); //white castles king side
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }
        try {
            assertSame(board2.getPiece("h10"), blackKing2); //test black king's new position
            assertSame(board2.getPiece("h1"), whiteKing2); //test white king's new position
            assertSame(board2.getPiece("g10"), blackRook2); //test black rook's new position
            assertSame(board2.getPiece("g1"), whiteRook2); //test white rook's new position
            assertNull(board2.getPiece("f10")); //test black king's old position
            assertNull(board2.getPiece("f1")); //test white king's old position
            assertNull(board2.getPiece("i10")); //test black rook's old position
            assertNull(board2.getPiece("i1")); //test white rook's old position
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }

    }

    @Test
    void test_invalidCastle() {
        ChessBoard board1 = new ChessBoard();
        ChessBoard board2 = new ChessBoard();

        // initialize pieces
        King blackKing1 = new King(board1, ChessPiece.Color.BLACK);
        Rook blackRook1 = new Rook(board1, ChessPiece.Color.BLACK);
        King blackKing2 = new King(board2, ChessPiece.Color.BLACK);
        Rook blackRook2 = new Rook(board2, ChessPiece.Color.BLACK);
        King whiteKing1 = new King(board1, ChessPiece.Color.WHITE);
        Rook whiteRook1 = new Rook(board1, ChessPiece.Color.WHITE);
        King whiteKing2 = new King(board2, ChessPiece.Color.WHITE);
        Rook whiteRook2 = new Rook(board2, ChessPiece.Color.WHITE);
        Queen blackQueen1 = new Queen(board1, ChessPiece.Color.BLACK);
        Queen whiteQueen1 = new Queen(board1, ChessPiece.Color.WHITE);
        Queen blackQueen2 = new Queen(board2, ChessPiece.Color.BLACK);
        Queen whiteQueen2 = new Queen(board2, ChessPiece.Color.WHITE);

        // place pieces
        board1.placePiece(blackKing1, "f10");
        board1.placePiece(blackRook1, "b10");
        board1.placePiece(whiteKing1, "f1");
        board1.placePiece(whiteRook1, "b1");
        board1.placePiece(blackQueen1, "e5");
        board1.placePiece(whiteQueen1, "e6");
        board2.placePiece(blackKing2, "f10");
        board2.placePiece(blackRook2, "i10");
        board2.placePiece(whiteKing2, "f1");
        board2.placePiece(whiteRook2, "i1");
        board2.placePiece(blackQueen2, "g5");
        board2.placePiece(whiteQueen2, "g6");

        // test invalid queen side castle (queens are blocking)
        assertThrows(IllegalMoveException.class, () -> board1.move("f10", "d10"));
        assertThrows(IllegalMoveException.class, () -> board1.move("f1", "d1"));

        // test invalid king side castle (queens are blocking)
        assertThrows(IllegalMoveException.class, () -> board2.move("f10", "h10"));
        assertThrows(IllegalMoveException.class, () -> board2.move("f1", "h1"));

    }

    @Test
    public void testIsKingInCheck() {
        ChessBoard board = new ChessBoard();
        King king = new King(board, ChessPiece.Color.WHITE);
        board.placePiece(king, "f1");
        assertFalse(king.isKingInCheck());

        Bishop bishop = new Bishop(board, ChessPiece.Color.WHITE);
        board.placePiece(bishop, "d3");
        assertFalse(king.isKingInCheck());

        Rook rook = new Rook(board, ChessPiece.Color.BLACK);
        board.placePiece(rook, "b1");
        assertTrue(king.isKingInCheck());
        assertTrue(king.getCheckingPiece() instanceof Rook);

        board.placePiece(bishop, "b1");
        assertFalse(king.isKingInCheck());
        assertNull(king.getCheckingPiece());
    }
}
