package com.csc14.runtimeterrors.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.csc14.runtimeterrors.game.BoardAssets.BoardSquare;

public class MatchScreen implements Screen {
    private OmegaChess parent;
    private Stage stage;
    private Table table;

    public MatchScreen(OmegaChess omegachess) {
        parent = omegachess;     // setting the argument to our field.

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center);
        table.setPosition(0, Gdx.graphics.getHeight()/2);

        initializeBoard();

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    private void initializeBoard() {
        BoardSquare blank = new BoardSquare(Color.BLACK);

        BoardSquare w4 = new BoardSquare(Color.GRAY, "blackWizard.png");
        BoardSquare w3 = new BoardSquare(Color.WHITE, "blackWizard.png");

        BoardSquare a10 = new BoardSquare(Color.GRAY, "blackChampion.png");
        BoardSquare b10 = new BoardSquare(Color.WHITE, "blackRook.png");
        BoardSquare c10 = new BoardSquare(Color.GRAY, "blackKnight.png");
        BoardSquare d10 = new BoardSquare(Color.WHITE, "blackBishop.png");
        BoardSquare e10 = new BoardSquare(Color.GRAY, "blackQueen.png");
        BoardSquare f10 = new BoardSquare(Color.WHITE, "blackKing.png");
        BoardSquare g10 = new BoardSquare(Color.GRAY, "blackBishop.png");
        BoardSquare h10 = new BoardSquare(Color.WHITE, "blackKnight.png");
        BoardSquare i10 = new BoardSquare(Color.GRAY, "blackRook.png");
        BoardSquare j10 = new BoardSquare(Color.WHITE, "blackChampion.png");

        BoardSquare a9 = new BoardSquare(Color.WHITE, "blackPawn.png");
        BoardSquare b9 = new BoardSquare(Color.GRAY, "blackPawn.png");
        BoardSquare c9 = new BoardSquare(Color.WHITE, "blackPawn.png");
        BoardSquare d9 = new BoardSquare(Color.GRAY, "blackPawn.png");
        BoardSquare e9 = new BoardSquare(Color.WHITE, "blackPawn.png");
        BoardSquare f9 = new BoardSquare(Color.GRAY, "blackPawn.png");
        BoardSquare g9 = new BoardSquare(Color.WHITE, "blackPawn.png");
        BoardSquare h9 = new BoardSquare(Color.GRAY, "blackPawn.png");
        BoardSquare i9 = new BoardSquare(Color.WHITE, "blackPawn.png");
        BoardSquare j9 = new BoardSquare(Color.GRAY, "blackPawn.png");

        BoardSquare a8 = new BoardSquare(Color.GRAY);
        BoardSquare b8 = new BoardSquare(Color.WHITE);
        BoardSquare c8 = new BoardSquare(Color.GRAY);
        BoardSquare d8 = new BoardSquare(Color.WHITE);
        BoardSquare e8 = new BoardSquare(Color.GRAY);
        BoardSquare f8 = new BoardSquare(Color.WHITE);
        BoardSquare g8 = new BoardSquare(Color.GRAY);
        BoardSquare h8 = new BoardSquare(Color.WHITE);
        BoardSquare i8 = new BoardSquare(Color.GRAY);
        BoardSquare j8 = new BoardSquare(Color.WHITE);

        BoardSquare a7 = new BoardSquare(Color.WHITE);
        BoardSquare b7 = new BoardSquare(Color.GRAY);
        BoardSquare c7 = new BoardSquare(Color.WHITE);
        BoardSquare d7 = new BoardSquare(Color.GRAY);
        BoardSquare e7 = new BoardSquare(Color.WHITE);
        BoardSquare f7 = new BoardSquare(Color.GRAY);
        BoardSquare g7 = new BoardSquare(Color.WHITE);
        BoardSquare h7 = new BoardSquare(Color.GRAY);
        BoardSquare i7 = new BoardSquare(Color.WHITE);
        BoardSquare j7 = new BoardSquare(Color.GRAY);

        BoardSquare a6 = new BoardSquare(Color.GRAY);
        BoardSquare b6 = new BoardSquare(Color.WHITE);
        BoardSquare c6 = new BoardSquare(Color.GRAY);
        BoardSquare d6 = new BoardSquare(Color.WHITE);
        BoardSquare e6 = new BoardSquare(Color.GRAY);
        BoardSquare f6 = new BoardSquare(Color.WHITE);
        BoardSquare g6 = new BoardSquare(Color.GRAY);
        BoardSquare h6 = new BoardSquare(Color.WHITE);
        BoardSquare i6 = new BoardSquare(Color.GRAY);
        BoardSquare j6 = new BoardSquare(Color.WHITE);

        BoardSquare a5 = new BoardSquare(Color.WHITE);
        BoardSquare b5 = new BoardSquare(Color.GRAY);
        BoardSquare c5 = new BoardSquare(Color.WHITE);
        BoardSquare d5 = new BoardSquare(Color.GRAY);
        BoardSquare e5 = new BoardSquare(Color.WHITE);
        BoardSquare f5 = new BoardSquare(Color.GRAY);
        BoardSquare g5 = new BoardSquare(Color.WHITE);
        BoardSquare h5 = new BoardSquare(Color.GRAY);
        BoardSquare i5 = new BoardSquare(Color.WHITE);
        BoardSquare j5 = new BoardSquare(Color.GRAY);

        BoardSquare a4 = new BoardSquare(Color.GRAY);
        BoardSquare b4 = new BoardSquare(Color.WHITE);
        BoardSquare c4 = new BoardSquare(Color.GRAY);
        BoardSquare d4 = new BoardSquare(Color.WHITE);
        BoardSquare e4 = new BoardSquare(Color.GRAY);
        BoardSquare f4 = new BoardSquare(Color.WHITE);
        BoardSquare g4 = new BoardSquare(Color.GRAY);
        BoardSquare h4 = new BoardSquare(Color.WHITE);
        BoardSquare i4 = new BoardSquare(Color.GRAY);
        BoardSquare j4 = new BoardSquare(Color.WHITE);

        BoardSquare a3 = new BoardSquare(Color.WHITE);
        BoardSquare b3 = new BoardSquare(Color.GRAY);
        BoardSquare c3 = new BoardSquare(Color.WHITE);
        BoardSquare d3 = new BoardSquare(Color.GRAY);
        BoardSquare e3 = new BoardSquare(Color.WHITE);
        BoardSquare f3 = new BoardSquare(Color.GRAY);
        BoardSquare g3 = new BoardSquare(Color.WHITE);
        BoardSquare h3 = new BoardSquare(Color.GRAY);
        BoardSquare i3 = new BoardSquare(Color.WHITE);
        BoardSquare j3 = new BoardSquare(Color.GRAY);

        BoardSquare a2 = new BoardSquare(Color.GRAY, "whitePawn.png");
        BoardSquare b2 = new BoardSquare(Color.WHITE, "whitePawn.png");
        BoardSquare c2 = new BoardSquare(Color.GRAY, "whitePawn.png");
        BoardSquare d2 = new BoardSquare(Color.WHITE, "whitePawn.png");
        BoardSquare e2 = new BoardSquare(Color.GRAY, "whitePawn.png");
        BoardSquare f2 = new BoardSquare(Color.WHITE, "whitePawn.png");
        BoardSquare g2 = new BoardSquare(Color.GRAY, "whitePawn.png");
        BoardSquare h2 = new BoardSquare(Color.WHITE, "whitePawn.png");
        BoardSquare i2 = new BoardSquare(Color.GRAY, "whitePawn.png");
        BoardSquare j2 = new BoardSquare(Color.WHITE, "whitePawn.png");

        BoardSquare a1 = new BoardSquare(Color.WHITE, "whiteChampion.png");
        BoardSquare b1 = new BoardSquare(Color.GRAY, "whiteRook.png");
        BoardSquare c1 = new BoardSquare(Color.WHITE, "whiteKnight.png");
        BoardSquare d1 = new BoardSquare(Color.GRAY, "whiteBishop.png");
        BoardSquare e1 = new BoardSquare(Color.WHITE, "whiteQueen.png");
        BoardSquare f1 = new BoardSquare(Color.GRAY, "whiteKing.png");
        BoardSquare g1 = new BoardSquare(Color.WHITE, "whiteBishop.png");
        BoardSquare h1 = new BoardSquare(Color.GRAY, "whiteKnight.png");
        BoardSquare i1 = new BoardSquare(Color.WHITE, "whiteRook.png");
        BoardSquare j1 = new BoardSquare(Color.GRAY, "whiteChampion.png");

        BoardSquare w1 = new BoardSquare(Color.WHITE, "whiteWizard.png");
        BoardSquare w2 = new BoardSquare(Color.GRAY, "whiteWizard.png");


        table.add(w4);
        table.add(blank);
        table.add(blank);
        table.add(blank);
        table.add(blank);
        table.add(blank);
        table.add(blank);
        table.add(blank);
        table.add(blank);
        table.add(blank);
        table.add(blank);
        table.add(w3);
        table.row();

        table.add(blank);
        table.add(a10);
        table.add(b10);
        table.add(c10);
        table.add(d10);
        table.add(e10);
        table.add(f10);
        table.add(g10);
        table.add(h10);
        table.add(i10);
        table.add(j10);
        table.add(blank);
        table.row();

        table.add(blank);
        table.add(a9);
        table.add(b9);
        table.add(c9);
        table.add(d9);
        table.add(e9);
        table.add(f9);
        table.add(g9);
        table.add(h9);
        table.add(i9);
        table.add(j9);
        table.add(blank);
        table.row();

        table.add(blank);
        table.add(a8);
        table.add(b8);
        table.add(c8);
        table.add(d8);
        table.add(e8);
        table.add(f8);
        table.add(g8);
        table.add(h8);
        table.add(i8);
        table.add(j8);
        table.add(blank);
        table.row();

        table.add(blank);
        table.add(a7);
        table.add(b7);
        table.add(c7);
        table.add(d7);
        table.add(e7);
        table.add(f7);
        table.add(g7);
        table.add(h7);
        table.add(i7);
        table.add(j7);
        table.add(blank);
        table.row();

        table.add(blank);
        table.add(a6);
        table.add(b6);
        table.add(c6);
        table.add(d6);
        table.add(e6);
        table.add(f6);
        table.add(g6);
        table.add(h6);
        table.add(i6);
        table.add(j6);
        table.add(blank);
        table.row();

        table.add(blank);
        table.add(a5);
        table.add(b5);
        table.add(c5);
        table.add(d5);
        table.add(e5);
        table.add(f5);
        table.add(g5);
        table.add(h5);
        table.add(i5);
        table.add(j5);
        table.add(blank);
        table.row();

        table.add(blank);
        table.add(a4);
        table.add(b4);
        table.add(c4);
        table.add(d4);
        table.add(e4);
        table.add(f4);
        table.add(g4);
        table.add(h4);
        table.add(i4);
        table.add(j4);
        table.add(blank);
        table.row();

        table.add(blank);
        table.add(a3);
        table.add(b3);
        table.add(c3);
        table.add(d3);
        table.add(e3);
        table.add(f3);
        table.add(g3);
        table.add(h3);
        table.add(i3);
        table.add(j3);
        table.add(blank);
        table.row();

        table.add(blank);
        table.add(a2);
        table.add(b2);
        table.add(c2);
        table.add(d2);
        table.add(e2);
        table.add(f2);
        table.add(g2);
        table.add(h2);
        table.add(i2);
        table.add(j2);
        table.add(blank);
        table.row();

        table.add(blank);
        table.add(a1);
        table.add(b1);
        table.add(c1);
        table.add(d1);
        table.add(e1);
        table.add(f1);
        table.add(g1);
        table.add(h1);
        table.add(i1);
        table.add(j1);
        table.add(blank);
        table.row();

        table.add(w1);
        table.add(blank);
        table.add(blank);
        table.add(blank);
        table.add(blank);
        table.add(blank);
        table.add(blank);
        table.add(blank);
        table.add(blank);
        table.add(blank);
        table.add(blank);
        table.add(w2);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}