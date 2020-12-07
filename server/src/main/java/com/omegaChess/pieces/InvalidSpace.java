package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;

import java.util.ArrayList;

public class InvalidSpace extends ChessPiece{

    public InvalidSpace(ChessBoard board, ChessPiece.Color color){ super(board, color); }

    public InvalidSpace() {
        super();
    }

    @Override
    public String toString() {
        return "\u2613";
    }

    @Override
    public LegalMoves legalMoves(Boolean firstPass, Boolean protectedPieceChecking) {
        return null;
    }

    @Override
    public LegalMoves getNormalOrCheckMoves() { return null; }

    @Override
    public LegalMoves movesToBlockCheckingPiece(String kingPos) { return null; }

    @Override
    public ArrayList<String> movesToCaptureWhileBlocking(String oppPos) { return null; }
}