package com.omegaChess;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalMoveException;
import com.omegaChess.exceptions.IllegalPositionException;
import com.omegaChess.pieces.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("JUnit Pawn Class Test")
class TestPawn {

    @Test
    void test_createInstance() {
        ChessBoard board = new ChessBoard();

        Pawn pawn = new Pawn(board, ChessPiece.Color.BLACK);

        assertNotNull(pawn);
    }

    @Test
    void test_getColor() {
        ChessBoard board = new ChessBoard();

        Pawn pawn = new Pawn(board, ChessPiece.Color.BLACK);

        assertEquals(pawn.getColor(), ChessPiece.Color.BLACK);
    }

    @Test
    void test_position() {
        // test_position test both getPosition and setPosition
        ChessBoard board = new ChessBoard();

        Pawn pawn = new Pawn(board, ChessPiece.Color.BLACK);

        try {
            pawn.setPosition("e3");
        } catch (IllegalPositionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertEquals("e3", pawn.getPosition());
    }

    @Test
    void test_toString() {
        ChessBoard board = new ChessBoard();

        Pawn pawn = new Pawn(board, ChessPiece.Color.BLACK);

        assertEquals("\u265F", pawn.toString());

        Pawn pawn_w = new Pawn(board, ChessPiece.Color.WHITE);

        assertEquals("\u2659", pawn_w.toString());
    }

    @Test
    void test_legalMoves() {
        ChessBoard board = new ChessBoard();

        Pawn pawn = new Pawn(board, ChessPiece.Color.WHITE);
        Knight knight = new Knight(board, ChessPiece.Color.BLACK);
        King king = new King(board, ChessPiece.Color.WHITE);

        board.placePiece(pawn, "c2");
        board.placePiece(king, "j2");

        // test 1 from initial position - no blocking pieces
        ArrayList<String> validMoves = new ArrayList<String>();
        validMoves.add("c3");
        validMoves.add("c4");
        validMoves.add("c5");

        LegalMoves moves = pawn.legalMoves(true);
        ArrayList<String> pawnValid = moves.getListOfMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 2 from initial position - yes blocking pieces
        board.placePiece(knight, "c4");

        validMoves.clear();
        validMoves.add("c3");

        moves = pawn.legalMoves(true);
        pawnValid = moves.getListOfMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 3 from initial position - yes both blocking pieces
        board.placePiece(knight, "c3");

        validMoves.clear();

        moves = pawn.legalMoves(true);
        pawnValid = moves.getListOfMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 4 from initial position - piece diagonal left available for capture
        board = new ChessBoard();

        pawn = new Pawn(board, ChessPiece.Color.WHITE);
        knight = new Knight(board, ChessPiece.Color.BLACK);
        king = new King(board, ChessPiece.Color.WHITE);

        board.placePiece(pawn, "c2");
        board.placePiece(knight, "b3");
        board.placePiece(king, "j2");

        validMoves.clear();
        validMoves.add("b3");
        validMoves.add("c3");
        validMoves.add("c4");
        validMoves.add("c5");

        moves = pawn.legalMoves(true);
        pawnValid = moves.getListOfMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 5 from initial position - piece diagonal right available for capture
        board = new ChessBoard();

        pawn = new Pawn(board, ChessPiece.Color.BLACK);
        knight = new Knight(board, ChessPiece.Color.WHITE);
        king = new King(board, ChessPiece.Color.BLACK);

        board.placePiece(pawn, "c9");
        board.placePiece(knight, "d8");
        board.placePiece(king, "j2");

        validMoves.clear();
        validMoves.add("c8");
        validMoves.add("c7");
        validMoves.add("c6");
        validMoves.add("d8");

        moves = pawn.legalMoves(true);
        pawnValid = moves.getListOfMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 6 from non initial position - no blocking pieces
        board = new ChessBoard();

        pawn = new Pawn(board, ChessPiece.Color.BLACK);
        pawn.setMoved(true);
        king = new King(board, ChessPiece.Color.BLACK);

        board.placePiece(pawn, "c7");
        board.placePiece(king, "j2");

        validMoves.clear();
        validMoves.add("c6");

        moves = pawn.legalMoves(true);
        pawnValid = moves.getListOfMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 7 from non initial position - yes blocking pieces
        board = new ChessBoard();

        pawn = new Pawn(board, ChessPiece.Color.WHITE);

        pawn.setMoved(true);
        knight = new Knight(board, ChessPiece.Color.BLACK);
        king = new King(board, ChessPiece.Color.WHITE);

        board.placePiece(pawn, "c4");
        board.placePiece(knight, "c5");
        board.placePiece(king, "j2");

        validMoves.clear();

        moves = pawn.legalMoves(true);
        pawnValid = moves.getListOfMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 8 from non initial position - no blocking and yes diag left
        board = new ChessBoard();

        pawn = new Pawn(board, ChessPiece.Color.WHITE);

        pawn.setMoved(true);
        knight = new Knight(board, ChessPiece.Color.BLACK);
        king = new King(board, ChessPiece.Color.WHITE);

        board.placePiece(pawn, "c4");
        board.placePiece(knight, "b5");
        board.placePiece(king, "j2");

        validMoves.clear();
        validMoves.add("c5");
        validMoves.add("b5");

        moves = pawn.legalMoves(true);
        pawnValid = moves.getListOfMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 9 from non initial position - yes blocking and yes diag right
        board = new ChessBoard();

        pawn = new Pawn(board, ChessPiece.Color.WHITE);

        pawn.setMoved(true);

        knight = new Knight(board, ChessPiece.Color.BLACK);
        king = new King(board, ChessPiece.Color.WHITE);

        board.placePiece(pawn, "c4");
        board.placePiece(knight, "c5");
        board.placePiece(knight, "d5");
        board.placePiece(king, "j2");

        validMoves.clear();
        validMoves.add("d5");

        moves = pawn.legalMoves(true);
        pawnValid = moves.getListOfMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 10 - non initial position - yes blocking and same color piece diag left
        board = new ChessBoard();

        pawn = new Pawn(board, ChessPiece.Color.WHITE);

        pawn.setMoved(true);
        knight = new Knight(board, ChessPiece.Color.BLACK);
        Knight knight2 = new Knight(board, ChessPiece.Color.WHITE);
        king = new King(board, ChessPiece.Color.WHITE);

        board.placePiece(pawn, "c4");
        board.placePiece(knight, "c5");
        board.placePiece(knight2, "b5");
        board.placePiece(king, "j2");

        validMoves.clear();

        moves = pawn.legalMoves(true);
        pawnValid = moves.getListOfMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 11 - non initial position, yes blocking and both diags available
        board = new ChessBoard();

        pawn = new Pawn(board, ChessPiece.Color.WHITE);

        pawn.setMoved(true);

        knight = new Knight(board, ChessPiece.Color.BLACK);
        king = new King(board, ChessPiece.Color.WHITE);

        board.placePiece(pawn, "c4");
        board.placePiece(knight, "b5");
        board.placePiece(knight, "d5");
        board.placePiece(knight, "c5");
        board.placePiece(king, "j2");

        validMoves.clear();
        validMoves.add("d5");
        validMoves.add("b5");

        moves = pawn.legalMoves(true);
        pawnValid = moves.getListOfMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 12 - en pessant left
        board = new ChessBoard();

        pawn = new Pawn(board, ChessPiece.Color.BLACK);
        pawn.setMoved(true);
        Pawn otherPawn = new Pawn(board, ChessPiece.Color.WHITE);
        king = new King(board, ChessPiece.Color.BLACK);
        King otherKing = new King(board, ChessPiece.Color.WHITE);

        board.placePiece(pawn, "d4");
        board.placePiece(otherPawn, "c2");
        board.placePiece(king, "j2");
        board.placePiece(otherKing, "j7");
        try {
            board.move("c2", "c4");
        }
        catch (IllegalMoveException e) {

        }

        validMoves.clear();
        validMoves.add("d3");
        validMoves.add("c3");

        moves = pawn.legalMoves(true);
        pawnValid = moves.getListOfMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 13 - en pessant right
        board = new ChessBoard();

        pawn = new Pawn(board, ChessPiece.Color.BLACK);
        pawn.setMoved(true);
        otherPawn = new Pawn(board, ChessPiece.Color.WHITE);
        king = new King(board, ChessPiece.Color.WHITE);
        otherKing = new King(board, ChessPiece.Color.BLACK);

        board.placePiece(pawn, "b4");
        board.placePiece(otherPawn, "c2");
        board.placePiece(king, "j2");
        board.placePiece(otherKing, "j7");
        try {
            board.move("c2", "c4");
        }
        catch (IllegalMoveException e) {

        }

        validMoves.clear();
        validMoves.add("b3");
        validMoves.add("c3");

        moves = pawn.legalMoves(true);
        pawnValid = moves.getListOfMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);
    }

    @Test
    void test_pawnPromotion() {
        // set up board and simulated user input
        ChessBoard board = new ChessBoard();
        InputStream sysInBackup = System.in;
        ByteArrayInputStream queenInput = new ByteArrayInputStream("queen".getBytes());
        ByteArrayInputStream bishopInput = new ByteArrayInputStream("bishop".getBytes());
        ByteArrayInputStream knightInput = new ByteArrayInputStream("knight".getBytes());
        ByteArrayInputStream rookInput = new ByteArrayInputStream("rook".getBytes());
        ByteArrayInputStream championInput = new ByteArrayInputStream("champion".getBytes());
        ByteArrayInputStream wizardInput = new ByteArrayInputStream("wizard".getBytes());

        // place pawns and set them as moved to avoid legalmoves checking out of bounds
        Pawn pawn1 = new Pawn(board, ChessPiece.Color.BLACK);
        pawn1.setMoved(true);
        Pawn pawn2 = new Pawn(board, ChessPiece.Color.BLACK);
        pawn2.setMoved(true);
        Pawn pawn3 = new Pawn(board, ChessPiece.Color.BLACK);
        pawn3.setMoved(true);
        Pawn pawn4 = new Pawn(board, ChessPiece.Color.WHITE);
        pawn4.setMoved(true);
        Pawn pawn5 = new Pawn(board, ChessPiece.Color.WHITE);
        pawn5.setMoved(true);
        Pawn pawn6 = new Pawn(board, ChessPiece.Color.WHITE);
        pawn6.setMoved(true);
        King wKing = new King(board, ChessPiece.Color.WHITE);
        King bKing = new King(board, ChessPiece.Color.BLACK);
        board.placePiece(pawn1, "e2");
        board.placePiece(pawn2, "f2");
        board.placePiece(pawn3, "g2");
        board.placePiece(pawn4, "e9");
        board.placePiece(pawn5, "f9");
        board.placePiece(pawn6, "g9");
        board.placePiece(wKing, "j2");
        board.placePiece(bKing, "j8");
        try {
            System.setIn(queenInput);
            board.move("e2", "e1");
            System.setIn(bishopInput);
            board.move("f2", "f1");
            System.setIn(knightInput);
            board.move("g2", "g1");
            System.setIn(rookInput);
            board.move("e9", "e10");
            System.setIn(championInput);
            board.move("f9", "f10");
            System.setIn(wizardInput);
            board.move("g9", "g10");
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }

        // get pieces after promotion
        ChessPiece newPiece1 = null;
        ChessPiece newPiece2 = null;
        ChessPiece newPiece3 = null;
        ChessPiece newPiece4 = null;
        ChessPiece newPiece5 = null;
        ChessPiece newPiece6 = null;
        try {
            newPiece1 = board.getPiece("e1");
            newPiece2 = board.getPiece("f1");
            newPiece3 = board.getPiece("g1");
            newPiece4 = board.getPiece("e10");
            newPiece5 = board.getPiece("f10");
            newPiece6 = board.getPiece("g10");
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }

        // reset System.in
        System.setIn(sysInBackup);

        // test that pieces have been promoted properly
        assertTrue(newPiece1 instanceof Queen);
        assertTrue(newPiece2 instanceof Bishop);
        assertTrue(newPiece3 instanceof Knight);
        assertTrue(newPiece4 instanceof Rook);
        assertTrue(newPiece5 instanceof Champion);
        assertTrue(newPiece6 instanceof Wizard);
    }

}
