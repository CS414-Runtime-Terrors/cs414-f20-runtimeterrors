package com.csc14.runtimeterrors.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ResumeScreen implements Screen {
    private final OmegaChess parent;
    private final Stage stage;
    private TextButton resumeBtn;
    private List<String> matches;
    private ArrayList<String> matchOpponents, matchIDs, playerIDs;
    private String nickname;
    private boolean isPopupDisplayed = false;

    public ResumeScreen(OmegaChess omegachess) {
        parent = omegachess;     // setting the argument to our field.

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        stage.act();
        stage.draw();
    }

    @Override
    public void show() {
        nickname = parent.getUser();

        matchOpponents = new ArrayList<>();
        matchIDs = new ArrayList<>();
        playerIDs = new ArrayList<>();
        Gdx.input.setInputProcessor(stage);

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.WHITE;
        style.font.getData().setScale(2f);

        TextField title = new TextField("Select a Game", style);

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        matches = new List<String>(skin);

        title.setWidth(400);
        title.setPosition(230, 420);
        stage.addActor(title);

       addButtons();

        listMatches();
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

    private void listMatches() {
        // request list of matches user is in
        OCMessage receivedMessage = parent.getClient().getResumeMatches(nickname);

        if (receivedMessage.get("success").equals("true")) {
            System.out.println(receivedMessage);
            if (Integer.parseInt(receivedMessage.get("count")) == 0){
                resumeBtn.setDisabled(false);
            }else {
                for (int i = 1; i <= Integer.parseInt(receivedMessage.get("count")); i++) {
                    matchOpponents.add(receivedMessage.get("opponent" + i));
                    matchIDs.add(receivedMessage.get("ID" + i));
                    playerIDs.add(receivedMessage.get("playerIndex" + i));
                }

                if (matchOpponents.size() > 0) {
                    matches.setItems(matchOpponents.toArray(new String[0]));
                    resumeBtn.setDisabled(false);
                } else {
                    resumeBtn.setDisabled(true);
                }

                matches.setWidth(550);
                matches.setHeight(325);
                matches.setPosition(50, 85);
                stage.addActor(matches);
            }
        }
        else {
            resumeBtn.setDisabled(true);

            TextField.TextFieldStyle style = new TextField.TextFieldStyle();
            style.font = new BitmapFont();
            style.fontColor = Color.WHITE;
            style.font.getData().setScale(1.25f);

            TextField successError = new TextField("Something went wrong. Could not retrieve list of matches.", style);
            successError.setWidth(475);
            successError.setPosition(100, 250);
            stage.addActor(successError);
        }
    }

    private void addButtons() {
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        TextButton backBtn = new TextButton("Lobby", skin);
        resumeBtn = new TextButton("Resume", skin);

        backBtn.setTransform(true);
        backBtn.setScale(0.35f);
        backBtn.setPosition(30, 15);
        stage.addActor(backBtn);

        resumeBtn.setTransform(true);
        resumeBtn.setScale(0.5f);
        resumeBtn.setPosition(250, 15);
        stage.addActor(resumeBtn);

        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(OmegaChess.SCREEN.LOBBY);
            };
        });

        resumeBtn.addListener( new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               if (!resumeBtn.isDisabled()) {
                   int selectedID = Integer.valueOf(matchIDs.get(matchOpponents.indexOf(matches.getSelected())));
                   int playerID = Integer.valueOf(playerIDs.get(matchOpponents.indexOf(matches.getSelected())));
                   String whitePlayer = "", blackPlayer = "";
                   if (playerID == 1) {
                       whitePlayer = parent.getUser();
                       blackPlayer = matches.getSelected();
                   }else {
                       whitePlayer = matches.getSelected();
                       blackPlayer = parent.getUser();
                   }
                   parent.setMatchInfo(selectedID, whitePlayer, blackPlayer);
                   parent.changeScreen(OmegaChess.SCREEN.MATCH);
               }
           };
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
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
        stage.clear();
    }

    @Override
    public void dispose() {

    }
}
