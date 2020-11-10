package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;

public class InvalidSpace extends ChessPiece{

    public InvalidSpace(ChessBoard board, ChessPiece.Color color){ super(board, color); }

    @Override
    public String toString() {
        return "\u2613";
    }

    @Override
    public LegalMoves legalMoves(Boolean firstPass) {
        return null;
    }

    @Override
    public LegalMoves getNormalOrCheckMoves() { return null; }

    @Override
    public LegalMoves movesToBlockCheckingPiece(String kingPos) { return null; }
}