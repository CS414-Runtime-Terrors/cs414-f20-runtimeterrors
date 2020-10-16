package com.omegaChess;

import java.util.ArrayList;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.pieces.Champion;
import com.omegaChess.pieces.ChessPiece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("JUnit Champion Class Test")
class TestChampion {

    @Test
    public void testGetColor(){
        ChessBoard board = new ChessBoard();
        Champion champ = new Champion(board, ChessPiece.Color.WHITE);
        assert champ.getColor() == ChessPiece.Color.WHITE: "Expected color to be white";
    }

    @Test
    public void testGetPosition(){
        ChessBoard board = new ChessBoard();
        Champion champ = new Champion(board, ChessPiece.Color.WHITE);
        board.placePiece(champ, "a4");
        assert champ.getPosition().equals("a4"): "Piece expected to return \"a4\"";
    }

    @Test
    public void testLegalMoves(){
        ChessBoard board = new ChessBoard();
        Champion champ = new Champion(board, ChessPiece.Color.BLACK);
        board.placePiece(champ, "c7");
        ArrayList<String> moves = new ArrayList<>();
        moves.add("c8"); moves.add("c9"); moves.add("c6"); moves.add("c5");
        moves.add("d7"); moves.add("e7"); moves.add("b7"); moves.add("a7");
        moves.add("a9"); moves.add("e9"); moves.add("e5"); moves.add("a5");
        assert moves.equals(champ.legalMoves()): "Expected moves not provided";
    }
}