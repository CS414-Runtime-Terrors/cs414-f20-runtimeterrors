package com.csc14.runtimeterrors.game.BoardAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

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

    /*public boolean isHighlighted() { return this.getColor().equals(Color.YELLOW); }

    /public void highlight() {
        this.setColor(Color.YELLOW);
    }

    public void unHighlight() { this.setColor(squareColor); }*/

}