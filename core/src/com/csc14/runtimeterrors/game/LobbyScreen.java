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

public class LobbyScreen implements Screen {
    private OmegaChess parent;
    private Stage stage;
    private TextButton createGameBtn, resumeGameBtn, logoutBtn, profileBtn, exitBtn;

    public LobbyScreen(OmegaChess omegachess) {
        parent = omegachess;     // setting the argument to our field.

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
        style.font.getData().setScale(2f);

        TextField title = new TextField("Welcome to Omega Chess", style);

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        createGameBtn = new TextButton("New Game", skin);
        resumeGameBtn = new TextButton("Resume Game", skin);
        profileBtn = new TextButton("Profile", skin);
        logoutBtn = new TextButton("Logout", skin);
        exitBtn = new TextButton("Exit", skin);

        // set title label
        title.setHeight(30);
        title.setWidth(450);
        title.setPosition(160, 450);
        title.setDisabled(true);
        stage.addActor(title);

        // set buttons
        createGameBtn.setTransform(true);
        createGameBtn.setScale(0.5f);
        createGameBtn.setPosition(235, 350);
        stage.addActor(createGameBtn);

        resumeGameBtn.setTransform(true);
        resumeGameBtn.setScale(0.5f);
        resumeGameBtn.setPosition(215, 250);
        stage.addActor(resumeGameBtn);

        profileBtn.setTransform(true);
        profileBtn.setScale(0.5f);
        profileBtn.setPosition(250, 150);
        stage.addActor(profileBtn);

        logoutBtn.setTransform(true);
        logoutBtn.setScale(0.5f);
        logoutBtn.setPosition(150, 50);
        stage.addActor(logoutBtn);

        exitBtn.setTransform(true);
        exitBtn.setScale(0.5f);
        exitBtn.setPosition(350, 50);
        stage.addActor(exitBtn);

        // add listeners
        addListeners();
    }

    private void addListeners() {
        // create game button will open invitation screen to invite screen
        createGameBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(OmegaChess.SCREEN.INVITE);
            };
        });

        // resume game will request user in-progress games and display for selection
        resumeGameBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            };
        });

        // profile button will open the profile screen profile screen
        profileBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            };
        });

        // logout button will clear user field and return to main menu
        logoutBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.user = "";
                parent.changeScreen(OmegaChess.SCREEN.MAIN_MENU);
            };
        });

        // exit button will close application
        exitBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.dispose();
                Gdx.app.exit();
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