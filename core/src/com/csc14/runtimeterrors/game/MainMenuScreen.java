package com.csc14.runtimeterrors.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {
    private OmegaChess parent;
    private Stage stage;

    private TextButton loginBtn, registerBtn, exitBtn;
    private TextField title;

    public MainMenuScreen(OmegaChess omegachess){
        parent = omegachess;     // setting the argument to our field.

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        // set up title text widget
        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.PURPLE;
        float scale = Math.min(Math.max(2, (parent.widthRatio * parent.heightRatio)), 5);
        style.font.getData().setScale(scale);
        title = new TextField("Omega Chess Main Menu", style);

        // set up button widgets
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        loginBtn = new TextButton("Login", skin);
        registerBtn = new TextButton("Register", skin);
        exitBtn = new TextButton("Exit", skin);

        // add button listeners
        addListeners();

        float newXPos;
        float newYPos;

        // set up title label
        title.setAlignment(Align.center);
        title.setHeight(30 * parent.heightRatio);
        title.setWidth(350 * parent.widthRatio);
        newXPos = parent.midWidth - (title.getWidth() / 2);
        newYPos = parent.displayHeight - (2.5f * title.getHeight());
        title.setPosition(newXPos, newYPos);
        title.setDisabled(true);
        stage.addActor(title);

        // set up login button
        loginBtn.setTransform(true);
        scale = 1.5f - (parent.heightRatio / parent.widthRatio);
        loginBtn.setScale(scale);
        newXPos = parent.midWidth - ((loginBtn.getWidth() * scale) / 2);
        newYPos = title.getTop() - title.getHeight() - (parent.displayHeight / 6) - (loginBtn.getHeight() * scale);
        loginBtn.setPosition(newXPos, newYPos);
        stage.addActor(loginBtn);

        // set up register button
        registerBtn.setTransform(true);
        registerBtn.setScale(scale);
        newXPos = parent.midWidth - ((registerBtn.getWidth() * scale) / 2);
        newYPos = loginBtn.getTop() - (loginBtn.getHeight() * scale) - (parent.displayHeight / 6) - (registerBtn.getHeight() * scale);
        registerBtn.setPosition(newXPos, newYPos);
        stage.addActor(registerBtn);

        // set up exit button
        exitBtn.setTransform(true);
        exitBtn.setScale(scale);
        newXPos = parent.midWidth - ((exitBtn.getWidth() * scale) / 2);
        newYPos = registerBtn.getTop() - (registerBtn.getHeight() * scale) - (parent.displayHeight / 6) - (exitBtn.getHeight() * scale);
        exitBtn.setPosition(newXPos, newYPos);
        stage.addActor(exitBtn);
    }

    private void addListeners() {
        // login button will change screen to login screen
        loginBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.clear();
                parent.changeScreen(OmegaChess.SCREEN.LOGIN);

            };
        });

        // register button will change screen to register screen
        registerBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.clear();
                parent.changeScreen(OmegaChess.SCREEN.REGISTER);
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

        stage.act();
        stage.draw();

        //parent.changeScreen(OmegaChess.MAIN_MENU_SCREEN);
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
        stage.dispose();

    }
}
