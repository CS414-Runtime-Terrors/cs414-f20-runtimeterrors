package com.csc14.runtimeterrors.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.csc14.runtimeterrors.game.BoardAssets.GameBoard;

import javax.swing.*;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

public class MatchScreen implements Screen {
    private final OmegaChess parent;
    private final Stage stage;
    private Table table;
    private GameBoard board;
    private TextField currentTurn;
    Skin skin;
    Map<String, Texture> textures;
    private boolean isPopupDisplayed = false, justFinishedTurn = false;

    public MatchScreen(OmegaChess omegachess) {
        parent = omegachess;     // setting the argument to our field.

        board = new GameBoard(parent, this);

        textures = new HashMap<>();
        initalizeTextures();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    private void initalizeTextures() {
        textures.put("blackBishop.png", new Texture(Gdx.files.internal("blackBishop.png")));
        textures.put("blackChampion.png", new Texture(Gdx.files.internal("blackChampion.png")));
        textures.put("blackKing.png", new Texture(Gdx.files.internal("blackKing.png")));
        textures.put("blackKnight.png", new Texture(Gdx.files.internal("blackKnight.png")));
        textures.put("blackPawn.png", new Texture(Gdx.files.internal("blackPawn.png")));
        textures.put("blackQueen.png", new Texture(Gdx.files.internal("blackQueen.png")));
        textures.put("blackRook.png", new Texture(Gdx.files.internal("blackRook.png")));
        textures.put("blackWizard.png", new Texture(Gdx.files.internal("blackWizard.png")));

        textures.put("whitekBishop.png", new Texture(Gdx.files.internal("whiteBishop.png")));
        textures.put("whiteChampion.png", new Texture(Gdx.files.internal("whiteChampion.png")));
        textures.put("whiteKing.png", new Texture(Gdx.files.internal("whiteKing.png")));
        textures.put("whiteKnight.png", new Texture(Gdx.files.internal("whiteKnight.png")));
        textures.put("whitePawn.png", new Texture(Gdx.files.internal("whitePawn.png")));
        textures.put("whiteQueen.png", new Texture(Gdx.files.internal("whiteQueen.png")));
        textures.put("whiteRook.png", new Texture(Gdx.files.internal("whiteRook.png")));
        textures.put("whiteWizard.png", new Texture(Gdx.files.internal("whiteWizard.png")));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        //create the chess board and add squares to table
        initializeBoard();

        //add table to the stage
        stage.addActor(table);

        // set turn of current player
        setTurn();

        // Add a field that shows whose turn it is
        if (currentTurn == null){
            TextField.TextFieldStyle style = new TextField.TextFieldStyle();
            style.font = new BitmapFont();
            style.fontColor = Color.WHITE;
            style.font.getData().scale(.5f);
            currentTurn = new TextField("Current Turn:" + board.getTurn(), style);
            currentTurn.setWidth(300);
            currentTurn.setPosition(230, 420);
            stage.addActor(currentTurn);
        }else{
            currentTurn.setText("Current turn:" + board.getTurn());
        }

        //add listener for the back and forfeit buttons
        addButtons();

        //add listeners for all of the BoardSquare objects
        board.addListeners();
    }

    public Texture getTexture(String piece)
    {
        return textures.get(piece);
    }

    private void addButtons() {
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        TextButton backBtn = new TextButton("Back", skin);
        TextButton forfeit = new TextButton("Forfeit", skin);
        TextButton refresh = new TextButton("Refresh", skin);

        // set up back button
        backBtn.setTransform(true);
        backBtn.setScale(0.5f);
        backBtn.setPosition(30, 0);
        stage.addActor(backBtn);

        // set up forfeit button
        forfeit.setTransform(true);
        forfeit.setScale(0.5f);
        forfeit.setPosition(460, 0);
        stage.addActor(forfeit);

        // set up refresh button
        refresh.setTransform(true);
        refresh.setScale(0.5f);
        refresh.setPosition(0, 420);
        stage.addActor(refresh);

        // back button will take user back to lobby screen
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent even, float x, float y) {
                parent.changeScreen(OmegaChess.SCREEN.LOBBY);
            }
        });

        // forfeit button will end the match between the users
        forfeit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent even, float x, float y) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to forfeit?",
                        "Forfeit?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (result == JOptionPane.YES_OPTION) {
                    if (parent.getUser().equalsIgnoreCase(board.getWhitePlayer())) {
                        parent.getClient().endMatch(board.getMatchID(), parent.getUser(), board.getBlackPlayer());
                    } else {
                        parent.getClient().endMatch(board.getMatchID(), parent.getUser(), board.getWhitePlayer());
                    }
                    parent.changeScreen(OmegaChess.SCREEN.LOBBY);
                }
        }
        });

        refresh.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clearStage();

                initializeBoard();
                stage.addActor(table);

                setTurn();
                board.addListeners();

                currentTurn.setText("Current Turn: " + board.getTurn());
            }
        });

        //add listeners for all of the BoardSquare objects
        board.addListeners();
    }

    private void refresh() {
        if (!parent.getUser().equals(board.getTurn())) {
            clearStage();

            initializeBoard();
            stage.addActor(table);

            setTurn();
            board.addListeners();

            currentTurn.setText("Current Turn: " + board.getTurn());

            //check for forfeit
            checkForfeit();

            // just switched turns, check checkmate
            if (parent.getUser().equals(board.getTurn()) || justFinishedTurn) {
                checkCheckmate(justFinishedTurn);
            }
            justFinishedTurn = false;
        }
    }

    // called at the end of move by GameBoard
    public void setJustFinishedTurnTrue() {
        justFinishedTurn = true;
    }

    private void checkCheckmate(boolean startOfOppTurn) {
        OCMessage receivedMessage = parent.getClient().getCheckmate(board.getMatchID());

        if (receivedMessage.get("success").equals("true")) {
            if (receivedMessage.get("checkmate").equals("true")) {
                String title = "Checkmate!";
                String message = "";
                if (startOfOppTurn) {
                    message = "You put " + receivedMessage.get("loser") + " in checkmate!";
                } else {
                    message = receivedMessage.get("winner") + " put you in checkmate!";
                }
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
                parent.getClient().endMatch(board.getMatchID(), receivedMessage.get("winner"), receivedMessage.get("loser"));
                parent.changeScreen(OmegaChess.SCREEN.LOBBY);
            }
        }
    }

    private void checkForfeit() {
        OCMessage receivedMessage = parent.getClient().getForfeit(board.getMatchID());

        if (receivedMessage.get("success").equals("true")) {
            if (receivedMessage.get("forfeit").equals("true")) {
                String title = "Forfeit!";
                String message = board.getTurn() + " has forfeit the match!";

                JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
                parent.getClient().endMatch(board.getMatchID(), parent.getUser(), board.getTurn());
                parent.changeScreen(OmegaChess.SCREEN.LOBBY);
            }
        }
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

        refresh();

        stage.act();
        stage.draw();
    }

    private void initializeBoard() {
        //set up table to be at the center of the screen
        table.setWidth(stage.getWidth());
        table.align(Align.center);
        table.setPosition(0, Gdx.graphics.getHeight()/2);
        board.initializeBoard();
        board.populateBoard();
        for (int i = 11; i >= 0; i--) {
            for (int j = 0; j <=11; j++) {
                table.add(board.getSquare(i, j));
            }
            table.row();
        }
    }

    private void clearStage(){
        if (table != null){
            table.remove();
        }
    }

    private void setTurn(){
        board.setTurn(parent.getClient().getTurn(board.getMatchID()).get("user"));
        Color turnColor = null;
        switch (parent.getClient().getTurn(board.getMatchID()).get("color")){
            case "WHITE":
                turnColor = Color.WHITE;
                break;
            case "BLACK":
                turnColor = Color.BLACK;
                break;
        }
        board.setTurnColor(turnColor);
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
        stage.clear();
    }

    @Override
    public void dispose() {
        for (String piece : textures.keySet())
            textures.get(piece).dispose();

        table.remove();
        currentTurn.remove();
        skin.dispose();
        stage.dispose();
        board = null;
    }
}