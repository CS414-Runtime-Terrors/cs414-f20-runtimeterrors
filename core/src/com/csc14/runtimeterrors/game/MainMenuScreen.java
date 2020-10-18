package com.csc14.runtimeterrors.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        //table.setDebug(true);
        stage.addActor(table);

        // set up title text widget
        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.PURPLE;
        style.font.getData().setScale(2f);
        title = new TextField("OmegaChess Main Menu", style);

        // set up button widgets
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        loginBtn = new TextButton("Login", skin);
        registerBtn = new TextButton("Register", skin);
        exitBtn = new TextButton("Exit", skin);

        // add button listeners
        addListeners();

        // make the buttons smaller
        loginBtn.setTransform(true);
        loginBtn.setScale(0.5f);
        registerBtn.setTransform(true);
        registerBtn.setScale(0.5f);
        exitBtn.setTransform(true);
        exitBtn.setScale(0.5f);

        table.center();
        table.add(title).width(400).fillX().uniformX().colspan(2);
        table.row().pad(10,0,10,0);
        table.add(loginBtn).fillX().uniformX();
        table.row();
        table.add(registerBtn).fillX().uniformX();
        table.row();
        table.add(exitBtn).fillX().uniformX();
    }

    private void addListeners() {
        // login button will change screen to login screen
        loginBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.clear();
                parent.changeScreen(OmegaChess.LOGIN_SCREEN);

            };
        });

        // register button will change screen to register screen
        registerBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(OmegaChess.REGISTER_SCREEN);
            };
        });

        // exit button will close application
        exitBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            };
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        parent.changeScreen(OmegaChess.MAIN_MENU_SCREEN);
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
