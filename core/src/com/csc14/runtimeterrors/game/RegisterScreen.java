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

public class RegisterScreen implements Screen {
    private OmegaChess parent;
    private Stage stage;
    private TextButton registerBtn;
    private TextButton backBtn;

    private Label emailLabel;
    private TextField emailBox;

    private Label nicknameLabel;
    private TextField nicknameBox;

    private Label passwordLabel;
    private TextField passwordBox;

    public RegisterScreen(OmegaChess omegachess){
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

        TextField title = new TextField("Register To Play Omega Chess!", style);

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        emailLabel = new Label("Email:", skin);
        emailBox = new TextField("", skin);

        nicknameLabel = new Label("Nickname:", skin);
        nicknameBox = new TextField("", skin);

        passwordLabel = new Label("Password:", skin);
        passwordBox = new TextField("", skin);

        registerBtn = new TextButton("Register!", skin);
        backBtn = new TextButton("Back", skin);

        // set up title label
        title.setHeight(30);
        title.setWidth(450);
        title.setPosition(150, 400);
        title.setDisabled(true);
        stage.addActor(title);

        // set up email label
        emailLabel.setWidth(50);
        emailLabel.setPosition(150, 300);
        stage.addActor(emailLabel);

        // set up email text box
        emailBox.setWidth(300);
        emailBox.setPosition(250, 300);
        emailBox.setDisabled(false);
        stage.addActor(emailBox);

        // set up nickname label
        nicknameLabel.setWidth(100);
        nicknameLabel.setPosition(150, 200);
        stage.addActor(nicknameLabel);

        // set up nickname text box
        nicknameBox.setWidth(300);
        nicknameBox.setPosition(250, 200);
        nicknameBox.setDisabled(false);
        stage.addActor(nicknameBox);

        // set up password label
        passwordLabel.setWidth(100);
        passwordLabel.setPosition(150, 100);
        stage.addActor(passwordLabel);

        // set up password text box
        passwordBox.setWidth(300);
        passwordBox.setPosition(250, 100);
        passwordBox.setDisabled(false);
        passwordBox.setPasswordCharacter('*');
        passwordBox.setPasswordMode(true);
        stage.addActor(passwordBox);

        // set up register button
        registerBtn.setTransform(true);
        registerBtn.setScale(0.5f);
        registerBtn.setPosition(450, 30);
        stage.addActor(registerBtn);

        backBtn.setTransform(true);
        backBtn.setScale(0.5f);
        backBtn.setPosition(50, 30);
        stage.addActor(backBtn);

        // add listener for register button
        addListeners();

    }

    private void addListeners() {
        // register button will handle registering the user
        registerBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // 1. get text from email, nickname, password.
                String email = emailBox.getText();
                String nickname = nicknameBox.getText();
                String password = passwordBox.getText();

                // 2. registration functions will make sure nickname is unique
                // TODO

                // 3. decide password complexity and check that before sending request to register
                // TODO

                // 4. do any error checking to make sure email is valid (has '@')
                // TODO

                // 5. send register request
                if (parent.getClient().sendRegisterRequest(email, nickname, password)) {
                    parent.user = nickname;
                    parent.changeScreen(OmegaChess.SCREEN.LOBBY); // go to login screen if successful
                }
            };
        });

        // back button will return user to main menu screen
        backBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(OmegaChess.SCREEN.MAIN_MENU);
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
