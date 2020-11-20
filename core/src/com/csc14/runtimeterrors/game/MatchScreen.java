package com.csc14.runtimeterrors.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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

import javax.swing.*;

public class MatchScreen implements Screen {
    private OmegaChess parent;
    private Stage stage;
    private Table table;
    private GameBoard board;
    private int matchID;
    private TextButton backBtn, forfeit;
    private boolean isPopupDisplayed = false;

    public MatchScreen(OmegaChess omegachess) {
        parent = omegachess;     // setting the argument to our field.

        board = new GameBoard(parent);

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

        // set turn of current player
        board.setTurn(parent.getClient().getTurn(board.getMatchID()).get("user"));
        Color turnColor = null;
        switch (parent.getClient().getTurn(board.getMatchID()).get("color")){
            case "White":
                turnColor = Color.WHITE;
                break;
            case "Black":
                turnColor = Color.BLACK;
                break;
        }
        board.setTurnColor(turnColor);

        // A timer to refresh the board for a new move if it isn't their turn
        /*if (!parent.getUser().equals(board.getTurn())) {
            final java.util.Timer t = new java.util.Timer(true);
            final TimerTask tt = new TimerTask() {
                @Override
                public void run() {
                    if (parent.getClient().getTurn(board.getMatchID()).get("user").equals(parent.getUser())){
                        board.populateBoard(board.getMatchID());
                    }

                }
            };
            t.scheduleAtFixedRate(tt, 0, 15000);
        }*/

        //add table to the stage
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        backBtn = new TextButton("Back", skin);
        forfeit = new TextButton("Forfeit", skin);

        //set up temporary back button
        backBtn.setTransform(true);
        backBtn.setScale(0.5f);
        backBtn.setPosition(30, 0);
        stage.addActor(backBtn);

        // set up forfeit button
        forfeit.setTransform(true);
        forfeit.setScale(0.5f);
        forfeit.setPosition(460, 0);
        stage.addActor(forfeit);

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

        // forfeit button will end the match between the users
        forfeit.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent even, float x, float y) {
                if (parent.getUser().equalsIgnoreCase(board.getWhitePlayer())) {
                    parent.getClient().endMatch(board.getMatchID(), parent.getUser(), board.getBlackPlayer());
                }else{
                    parent.getClient().endMatch(board.getMatchID(), parent.getUser(), board.getWhitePlayer());
                }
                parent.changeScreen(OmegaChess.SCREEN.LOBBY);
        }
        });
    }

    public void showNotification(String message, int messageCount){
        isPopupDisplayed = true;
        String title = "New Notification!";

        if( messageCount > 1 )
        {
            title = "New Notifications!";
        }

        JOptionPane.showMessageDialog(null, message,
                title, JOptionPane.INFORMATION_MESSAGE);
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
        board.populateBoard();
        for (int i = 11; i >= 0; i--) {
            for (int j = 0; j <=11; j++) {
                table.add(board.getSquare(i, j));
            }
            table.row();
        }
    }

    public void setMatchID(int id) { board.setMatchID(id); }

    public void setWhitePlayer(String whitePlayer) { board.setWhitePlayer(whitePlayer); }

    public void setBlackPlayer(String blackPlayer) { board.setBlackPlayer(blackPlayer); }

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