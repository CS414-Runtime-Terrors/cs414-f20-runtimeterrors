package com.csc14.runtimeterrors.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import javax.swing.*;

public class ArchiveScreen implements Screen {
    private OmegaChess parent;
    private Stage stage;
    private TextButton lobbyBtn;
    private Skin skin;
    private Label.LabelStyle style_label;
    private String nickname;
    private boolean isPopupDisplayed = false;

    public ArchiveScreen(OmegaChess omegachess){
        parent = omegachess;
        nickname = parent.getUser();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        stage.act();
        stage.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.WHITE;
        style.font.getData().setScale(3f);

        style_label = new Label.LabelStyle();
        style_label.font = new BitmapFont();
        style_label.fontColor = Color.PURPLE;
        style_label.font.getData().setScale(2f);

        // set up archive label
        TextField profileLabel = new TextField("Archive", style);
        profileLabel.setWidth(400);
        profileLabel.setPosition(200, 420);
        stage.addActor(profileLabel);

        // add buttons to the screen
        addButtonsToStage();

        // add listener for buttons
        addListeners();
    }

    private void addButtonsToStage() {
        lobbyBtn = new TextButton("Lobby", skin);

        // set up lobby button
        lobbyBtn.setTransform(true);
        lobbyBtn.setScale(0.4f);
        lobbyBtn.setPosition(450, 15);
        stage.addActor(lobbyBtn);
    }

    private void addListeners() {
        // back button will return user to main menu screen
        lobbyBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(OmegaChess.SCREEN.LOBBY);
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

    }

    @Override
    public void dispose() {

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
}
