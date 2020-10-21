package com.omegaChess;

import com.omegaChess.board.*;
import com.omegaChess.exceptions.*;
import com.omegaChess.pieces.InvalidSpace;
import com.omegaChess.pieces.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestInvalidSpace{

    @Test
    public void testGetPosition(){
        ChessBoard board = new ChessBoard();
        InvalidSpace iS = new InvalidSpace(board, null);
        board.placePiece(iS, "x5");
        assertEquals("x5", iS.getPosition(), "piece didn't get placed properly");
    }

    @Test
    public void testSetPosition(){
        ChessBoard board = new ChessBoard();
        InvalidSpace iS = new InvalidSpace(board, null);
        try {
            iS.setPosition("x5");
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        assertEquals("x5", iS.getPosition(), "Failed to set position correctly.");
        assertThrows(IllegalPositionException.class, () -> iS.setPosition("t54"));
    }

    @Test
    public void testLegalMoves(){
        ChessBoard board = new ChessBoard();
        board.initialize();
        ChessPiece iS = null;
        try {
            iS = board.getPiece("x1");
        } catch (IllegalPositionException e){
            e.printStackTrace();
        }
        assertTrue(iS instanceof InvalidSpace, "Not and InvalidSpace.");
        assertNull(iS.getNormalOrCheckMoves(), "Moves are supposed to be empty.");
    }

    @Test
    public void testToString(){
        ChessBoard board = new ChessBoard();
        InvalidSpace iS = new InvalidSpace(board, null);
        assertEquals("\u2613", iS.toString(), "ToString invalid string.");
    }

}