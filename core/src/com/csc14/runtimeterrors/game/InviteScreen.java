package com.csc14.runtimeterrors.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import sun.font.TextLabel;

import java.awt.font.TextLayout;

public class InviteScreen implements Screen {
    private OmegaChess parent;
    private Stage stage;
    private TextButton submit, home;
    private Label opponentName;
    private TextField anotherUser;

    public InviteScreen(OmegaChess omegaChess){
        parent = omegaChess;

        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        stage.act();
        stage.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.PURPLE;
        style.font.getData().setScale(1.75f);

        TextField title = new TextField("Invite another player to play Omega Chess with!", style);

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        opponentName = new Label("Insert the username of another player. (This is not case-sensitive)", skin);
        anotherUser = new TextField("", skin);

        submit = new TextButton("Invite Player", skin);
        home = new TextButton("Lobby", skin);

        // set up title label
        title.setHeight(30);
        title.setWidth(600);
        title.setPosition(75, 400);
        title.setDisabled(true);
        stage.addActor(title);

        // set up opponent username label
        opponentName.setWidth(350);
        opponentName.setWrap(true);
        opponentName.setPosition(130, 300);
        opponentName.setAlignment(Align.center);
        stage.addActor(opponentName);

        // set up field to insert username
        anotherUser.setWidth(300);
        anotherUser.setPosition(170, 200);
        anotherUser.setDisabled(false);
        stage.addActor(anotherUser);

        // set up submit button
        submit.setTransform(true);
        submit.setScale(0.5f);
        submit.setPosition(400, 30);
        stage.addActor(submit);

        // set up home button
        home.setTransform(true);
        home.setScale(0.5f);
        home.setPosition(30, 30);
        stage.addActor(home);

        // add listener
        addListeners();

    }

    private void addListeners() {
        // register button will handle registering the user
        submit.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // 1. get text from anotherUser.
                String otherUser = anotherUser.getText();

                // 2. send invite request

            };
        });

        // home button will return user to main menu screen
        home.addListener( new ClickListener() {
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
}
