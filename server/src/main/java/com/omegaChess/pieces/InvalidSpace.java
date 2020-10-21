package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;

public class InvalidSpace extends ChessPiece{

    public InvalidSpace(ChessBoard board, ChessPiece.Color color){super(board, null);}

    @Override
    public String toString() {
        return "\u2613";
    }

    @Override
    public LegalMoves legalMoves() {
        return null;
    }

    @Override
    public LegalMoves movesToBlockCheckingPiece(String kingPos) { return null; }
}