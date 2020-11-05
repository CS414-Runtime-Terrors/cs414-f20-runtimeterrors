package com.csc14.runtimeterrors.game.BoardAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BoardSquare extends Actor {

    public Sprite currentPiece = null;
    public final Color squareColor;

    public BoardSquare(Color squareColor) {
        this.setColor(squareColor);
        this.squareColor = squareColor;
        this.setHeight(30);
        this.setWidth(30);
    }

    public BoardSquare(Color squareColor, String piece) {
        this.setColor(squareColor);
        this.squareColor = squareColor;
        this.setHeight(30);
        this.setWidth(30);

        //set piece depending on the file given
        this.currentPiece = new Sprite(new Texture(Gdx.files.internal(piece)));

        //set size to 30x30
        currentPiece.setSize(30, 30);
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

    public void setPiece(Sprite piece) { this.currentPiece = piece; }

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