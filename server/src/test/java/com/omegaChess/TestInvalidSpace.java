package com.omegaChess;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;
import com.omegaChess.pieces.InvalidSpace;
import com.omegaChess.pieces.ChessPiece;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestInvalidSpace{

    @Test
    public void testGetPosition(){
        ChessBoard board = new ChessBoard();
        InvalidSpace iS = new InvalidSpace(board, null);
        assertNull(iS.getPosition());
    }

    @Test
    public void testSetPosition(){
        ChessBoard board = new ChessBoard();
        InvalidSpace iS = new InvalidSpace(board, null);
        assertThrows(IllegalPositionException.class, () -> iS.setPosition(""));
    }

}