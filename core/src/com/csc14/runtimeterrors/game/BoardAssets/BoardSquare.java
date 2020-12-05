package com.csc14.runtimeterrors.game.BoardAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BoardSquare extends Actor {

    private Sprite currentPiece = null;
    private final Color squareColor;
    private Color pieceColor;
    private final int[] position = new int[2];

    public BoardSquare(Color squareColor, int row, int col) {
        this.setColor(squareColor);
        this.squareColor = squareColor;
        this.setHeight(30);
        this.setWidth(30);
        position[0] = row;
        position[1] = col;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float x = this.getX();
        float y = this.getY();
        float width = this.getWidth();
        float height = this.getHeight();

        //draw board square
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.getColor());
        shapeRenderer.rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        shapeRenderer.end();

        //outline square in black
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.line(x, y, x, y + height);
        shapeRenderer.line(x, y + height, x + width, y + height);
        shapeRenderer.line(x + width, y +height, x + width, y);
        shapeRenderer.line(x + width, y, x, y);
        shapeRenderer.end();

        //draw piece if there is a piece to draw
        if(currentPiece != null){
            batch.end();
            batch.begin();
            currentPiece.setPosition(this.getX(), this.getY());
            currentPiece.draw(batch);
            batch.end();
            batch.begin();
        }
    }

    public int[] getPosition() { return this.position; }

    public Sprite getCurrentPiece() { return this.currentPiece; }

    public Color getPieceColor() { return pieceColor; }

    public void setPiece(Sprite piece, Color color) {
        this.currentPiece = piece;
        pieceColor = color;
    }

    public void setPiece(String piece, Color color, Texture texture) {
        this.currentPiece = new Sprite(texture);
        currentPiece.setSize(30, 30);
        pieceColor = color;
    }

    public void removePiece() {
        this.currentPiece = null;
    }

    public boolean hasPiece() { return !(this.currentPiece == null); }

    public boolean isHighlighted() { return this.getColor().equals(Color.YELLOW); }

    public void highlight() {
        this.setColor(Color.YELLOW);
    }

    public void unHighlight() { this.setColor(squareColor); }

}