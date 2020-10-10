package com.omegaChess;

import com.omegaChess.pieces.LegalMoves;
import com.omegaChess.pieces.Wizard;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import com.omegaChess.board.ChessBoard;
import com.omegaChess.pieces.ChessPiece;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JUnit Wizard Class Test")
class TestWizard{

    @Test
    public void testGetColor(){
        ChessBoard board = new ChessBoard();
        Wizard wiz = new Wizard(board, ChessPiece.Color.WHITE);
        assertEquals(ChessPiece.Color.WHITE, wiz.getColor(), "Expected color to be white");
    }

    @Test
    public void testGetPosition(){
        ChessBoard board = new ChessBoard();
        Wizard wiz = new Wizard(board, ChessPiece.Color.WHITE);
        board.placePiece(wiz, "a4");
        assertEquals("a4", wiz.getPosition(),"Piece expected to return \"a4\"");
    }

    @Test
    public void testLegalMoves(){
        ChessBoard board = new ChessBoard();
        Wizard wiz = new Wizard(board, ChessPiece.Color.BLACK);
        board.placePiece(wiz, "g5");
        ArrayList<String> expectedMoves = new ArrayList<>();
        expectedMoves.add("f6"); expectedMoves.add("f8"); expectedMoves.add("h6"); expectedMoves.add("j6");
        expectedMoves.add("h4"); expectedMoves.add("h2"); expectedMoves.add("f4"); expectedMoves.add("d4");
        expectedMoves.add("h8"); expectedMoves.add("j4"); expectedMoves.add("f2"); expectedMoves.add("d6");
        LegalMoves moves = wiz.legalMoves();
        ArrayList<String> actualMoves = moves.getListOfMoves();
        assertEquals(expectedMoves, actualMoves, "Expected moves not provided");
    }
}