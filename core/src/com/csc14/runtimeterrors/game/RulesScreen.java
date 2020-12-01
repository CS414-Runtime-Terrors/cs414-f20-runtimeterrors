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
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import javax.swing.*;

public class RulesScreen implements Screen {
    private final OmegaChess parent;
    private final Stage stage;

    private final String rules;
    private boolean isPopupDisplayed = false;

    public RulesScreen(OmegaChess omegachess){
        parent = omegachess;     // setting the argument to our field.
        rules = Gdx.files.local("GameRules.txt").readString();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.WHITE;
        style.font.getData().setScale(2f);

        TextField title = new TextField("Rules", style);

        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Skin btnSkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        TextButton lobbyBtn = new TextButton("Lobby", btnSkin);

        // set title label
        title.setHeight(30);
        title.setWidth(100);
        title.setPosition(275, 450);
        stage.addActor(title);

        // set lobby button
        lobbyBtn.setTransform(true);
        lobbyBtn.setScale(0.5f);
        lobbyBtn.setPosition(250, 15);
        stage.addActor(lobbyBtn);

        //set rules text
        Table rulesTable = new Table();
        rulesTable.setWidth(575);
        rulesTable.setHeight(375);
        rulesTable.setPosition(35, 75);
        stage.addActor(rulesTable);

        Table labels = new Table(skin);
        labels.add(rules);
        ScrollPane scroll = new ScrollPane(labels, skin);
        scroll.setFadeScrollBars(false);
        rulesTable.add(scroll).expand().fill();

        lobbyBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(OmegaChess.SCREEN.LOBBY);
            };
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

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
