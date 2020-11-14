package com.csc14.runtimeterrors.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.csc14.runtimeterrors.game.BoardAssets.GameBoard;
import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatchScreen implements Screen {
    private OmegaChess parent;
    private Stage stage;
    private Table table;
    private GameBoard board;
    private TextButton backBtn;
    private boolean isPopupDisplayed = false;

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

        //set up table to be at the center of the screen
        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center);
        table.setPosition(0, Gdx.graphics.getHeight()/2);

        //create the chess board and add squares to table
        initializeBoard();

        //add table to the stage
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        backBtn = new TextButton("Back", skin);

        //set up temporary back button
        backBtn.setTransform(true);
        backBtn.setScale(0.2f);
        backBtn.setPosition(0, 0);
        stage.addActor(backBtn);

        //add listener for the back button
        addListeners();

        //add listeners for all of the BoardSquare objects
        board.addListeners();
    }

    private void addListeners() {
        // back button will take user back to lobby screen
        backBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent even, float x, float y) {
                parent.changeScreen(OmegaChess.SCREEN.LOBBY);
            }
        });
    }

    public void showNotification(){
        isPopupDisplayed = true;
        JOptionPane.showMessageDialog(null, "Testing Popup!",
                "New Notification!", JOptionPane.INFORMATION_MESSAGE);
        isPopupDisplayed = false;

    }

    public boolean isPopupShown(){
        return isPopupDisplayed;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    private void initializeBoard() {
        board = new GameBoard(parent);
        for (int i = 11; i >= 0; i--) {
            for (int j = 0; j <=11; j++) {
                table.add(board.getSquare(i, j));
            }
            table.row();
        }
    }

    public void setMatchID(int id) {
        board.setMatchID(id);
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