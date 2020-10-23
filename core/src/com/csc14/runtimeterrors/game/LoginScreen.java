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
import com.badlogic.gdx.utils.viewport.ScreenViewport;

//import javax.xml.soap.Text;

public class LoginScreen implements Screen {
    private OmegaChess parent;
    private Stage stage;
    private TextButton loginBtn;
    private TextButton backBtn;

    public LoginScreen(OmegaChess omegachess){
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

        TextField title = new TextField("Login To Play OmegaChess!", style);

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        Label nicknameLabel = new Label("Nickname:", skin);
        TextField nicknameBox = new TextField("", skin);

        Label passwordLabel = new Label("Password:", skin);
        TextField passwordBox = new TextField("", skin);

        loginBtn = new TextButton("Login!", skin);
        backBtn = new TextButton("Back", skin);

        // set up title label
        title.setHeight(30);
        title.setWidth(450);
        title.setPosition(150, 400);
        title.setDisabled(true);
        stage.addActor(title);

        // set up nickname label
        nicknameLabel.setWidth(100);
        nicknameLabel.setPosition(150, 300);
        stage.addActor(nicknameLabel);

        // set up nickname text box
        nicknameBox.setWidth(300);
        nicknameBox.setPosition(250, 300);
        nicknameBox.setDisabled(false);
        stage.addActor(nicknameBox);

        // set up password label
        passwordLabel.setWidth(100);
        passwordLabel.setPosition(150, 200);
        stage.addActor(passwordLabel);

        // set up password text box
        passwordBox.setWidth(300);
        passwordBox.setPosition(250, 200);
        passwordBox.setDisabled(false);
        passwordBox.setPasswordCharacter('*');
        passwordBox.setPasswordMode(true);
        stage.addActor(passwordBox);

        // set up register button
        loginBtn.setTransform(true);
        loginBtn.setScale(0.5f);
        loginBtn.setPosition(450, 30);
        stage.addActor(loginBtn);

        backBtn.setTransform(true);
        backBtn.setScale(0.5f);
        backBtn.setPosition(50, 30);
        stage.addActor(backBtn);

        // add listener for register button
        addListeners();

    }

    private void addListeners() {
        // register button will handle registering the user
        loginBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // 1. get text from nickname, password.
                // 2. check that nickname exists
                // 3. attempt to login with nickname and password
            };
        });

        // back button will return user to main menu screen
        backBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(OmegaChess.MAIN_MENU_SCREEN);
            };
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        //parent.changeScreen(OmegaChess.REGISTER_SCREEN);
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
