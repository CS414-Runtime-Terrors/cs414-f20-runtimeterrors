package com.csc14.runtimeterrors.game.BoardAssets;

import java.awt.*;

public class BoardSquare {
    String currentPiece;
    private Color pieceColor;
    private final int[] position = new int[2];

    public BoardSquare( int row, int col, String piece) {
        currentPiece = piece;
        position[0] = row;
        position[1] = col;
    }

    public String getPiece() {
        return currentPiece;
    }

    public int[] getPosition() { return this.position; }

    public String getCurrentPiece() { return this.currentPiece; }

    public Color getPieceColor() { return pieceColor; }

    public void setPiece(String piece, Color pcolor) {
        this.currentPiece = piece;
        pieceColor = pcolor;
    }

    public void removePiece() {
        this.currentPiece = "";
    }

    public Color getColor()
    {
        return pieceColor;
    }

    public boolean hasPiece() { return !(this.currentPiece == null); }
}