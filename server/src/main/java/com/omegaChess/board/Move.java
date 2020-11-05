package com.omegaChess.board;

import com.omegaChess.pieces.ChessPiece;

public class Move {
    private ChessPiece piece;
    private String fromPosition;
    private String toPosition;

    public Move(ChessPiece piece, String from, String to) {
        this.piece = piece;
        this.fromPosition = from;
        this.toPosition = to;
    }

    //only has getters since nothing should be changed after initialization
    public ChessPiece getMovedPiece() {
        return this.piece;
    }

    public String getMovedFromPosition() {
        return this.fromPosition;
    }

    public String getMovedToPosition() {
        return this.toPosition;
    }

    public void save() {
        // TODO
    }

    public void load() {
        // TODO
    }
}
