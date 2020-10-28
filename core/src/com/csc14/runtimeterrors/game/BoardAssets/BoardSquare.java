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

    public BoardSquare(Color squareColor) {
        this.setColor(squareColor);
        this.setHeight(30);
        this.setWidth(30);
    }

    public BoardSquare(Color squareColor, String piece) {
        this.setColor(squareColor);
        this.setHeight(30);
        this.setWidth(30);
        this.currentPiece = new Sprite(new Texture(Gdx.files.internal(piece)));
        currentPiece.setSize(30, 30);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //draw board square
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.getColor());
        shapeRenderer.rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
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

    public void setPiece(String piece) {
        this.currentPiece = new Sprite(new Texture(Gdx.files.internal(piece)));
    }

    public void removePiece() {
        this.currentPiece = null;
    }

    public void highlight() {
        this.setColor(Color.YELLOW);
    }

}