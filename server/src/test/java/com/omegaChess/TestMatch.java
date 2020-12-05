package com.omegaChess;

import com.omegaChess.server.*;
import com.omegaChess.pieces.*;
import com.omegaChess.board.*;
import org.junit.jupiter.api.Test;

import java.security.BasicPermission;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestMatch {

    @Test
    void testConstructor(){
        UserProfile p1 = new UserProfile("NustinJeep", "Carsman", "HOTrod@omegachess.com"),
                p2 = new UserProfile("Emememe", "mmmmeeeee", "myspace@omegachess.com");

        Match match = new Match(p1.getNickname(), p2.getNickname());

        assertNotNull(match, "Failed to create match.");

    }

    @Test
    void testGettersAndSetters(){
        UserProfile p1 = new UserProfile("Joshathan", "McBr1de", "McJosh@omegachess.com"),
                p2 = new UserProfile("JimmyJam", "PB&Jay", "sandwitchLover@hotmail.com");

        Match match = new Match(p1.getNickname(), p2.getNickname());
        ChessBoard board = new ChessBoard();
        board.initialize();
        TurnTracker turn = new TurnTracker(p1.getNickname(), p2.getNickname());

        match.setBoard(board);
        assertEquals(board.toString(), match.getBoard().toString(), "Expected boards to be the same.");
        assertEquals(p1.getNickname(), match.getProfile1(), "Player 1 names are not the same.");
        assertEquals(p2.getNickname(), match.getProfile2(), "Player 2 names are not the same.");
        match.getBoard().setTurn(turn);
        assertEquals(turn.getCurrentTurnPlayer(), match.getBoard().getTurn().getCurrentTurnPlayer(), "Turns do not match.");
    }

    @Test
    void testEndMatch(){
        UserProfile p1 = new UserProfile("SerpentSlayer", "slitherrrrr", "slideintothedmsss@omegachess.com"),
                p2 = new UserProfile("Italian", "italianhandshake", "needsmorecheese@omegachess.com");

        Match match = new Match(p1.getNickname(), p2.getNickname());

        assertNotNull(match.endMatch(p2.getNickname(), p1.getNickname(), 15));
    }

    @Test
    void testCheckCheckmate(){
        UserProfile p1 = new UserProfile("DNDMonger", "RollTheDice", "alwaysgetsa1@omegachess.com"),
                p2 = new UserProfile("ClASSes", "Schollsux", "Fallingoutoftherealm@omegachess.com");

        Match match = new Match(p1.getNickname(), p2.getNickname());

        assertFalse(match.checkCheckmate(), "There are no pieces in checkmate.");

        ChessBoard board = new ChessBoard();
        board.placePiece(new King(board, ChessPiece.Color.BLACK), "i10");
        board.placePiece(new Rook(board, ChessPiece.Color.BLACK), "h10");
        board.placePiece(new Pawn(board, ChessPiece.Color.BLACK), "i9");
        board.placePiece(new Pawn(board, ChessPiece.Color.WHITE), "i8");
        board.placePiece(new Queen(board, ChessPiece.Color.WHITE), "j9");
        board.placePiece(new King(board, ChessPiece.Color.WHITE), "i1");
        for (ChessPiece piece: board.black_pieces )
            piece.setMoved(true);
        for (ChessPiece piece: board.white_pieces )
            piece.setMoved(true);

        board.initializeInvalidSpaces();

        match = new Match(p1.getNickname(), p2.getNickname());
        match.setBoard(board);
        TurnTracker turn = new TurnTracker(p1.getNickname(), p2.getNickname());
        turn.switchTurn();
        match.getBoard().setTurn(turn);

        assertTrue(match.checkCheckmate(), "Black should be checkmate.");

    }

}
