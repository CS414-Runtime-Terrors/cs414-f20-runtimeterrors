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
import com.badlogic.gdx.utils.Align;

import javax.swing.*;

public class LoginScreen implements Screen {
    private OmegaChess parent;
    private Stage stage;
    private TextButton loginBtn;
    private TextButton backBtn;

    private Label nicknameLabel, passwordLabel;
    private TextField nicknameBox, passwordBox, title;


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
        float scale = Math.min(Math.max(2, (parent.widthRatio * parent.heightRatio) / 1.5f), 5);
        style.font.getData().setScale(scale);

        title = new TextField("Login To Play Omega Chess!", style);

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        nicknameLabel = new Label("Nickname:", skin);
        nicknameBox = new TextField("", skin);

        passwordLabel = new Label("Password:", skin);
        passwordBox = new TextField("", skin);

        loginBtn = new TextButton("Login!", skin);
        backBtn = new TextButton("Back", skin);

        float newXPos;
        float newYPos;

        // set up title label
        title.setAlignment(Align.center);
        title.setHeight(30 * parent.heightRatio);
        title.setWidth(450 * parent.widthRatio);
        newXPos = parent.midWidth - (title.getWidth() / 2);
        newYPos = parent.displayHeight - (2.5f * title.getHeight());
        title.setPosition(newXPos, newYPos);
        title.setDisabled(true);
        stage.addActor(title);

 /*       // set up nickname label
        //nicknameLabel.setWidth(100);
        nicknameLabel.setFontScale(1.5f);
        nicknameLabel.setPosition(150, 300);
        stage.addActor(nicknameLabel);

        // set up nickname text box
        nicknameBox.setWidth(300 * Math.max((parent.widthRatio / 2), 1));
        nicknameBox.setPosition(250, 300);
        nicknameBox.setDisabled(false);
        stage.addActor(nicknameBox);

        // set up password label
        //passwordLabel.setWidth(100);
        passwordLabel.setFontScale(1.5f);
        passwordLabel.setPosition(150, 200);
        stage.addActor(passwordLabel);

        // set up password text box
        passwordBox.setWidth(300 * Math.max((parent.widthRatio / 2), 1));
        passwordBox.setPosition(250, 200);
        passwordBox.setDisabled(false);
        passwordBox.setPasswordCharacter('*');
        passwordBox.setPasswordMode(true);
        stage.addActor(passwordBox);
*/
        // set label and box properties
        nicknameLabel.setWidth(100 * Math.max((parent.widthRatio / 2), 1));
        nicknameLabel.setFontScale(1.5f);

        nicknameBox.setWidth(300 * Math.max((parent.widthRatio / 2), 1));
        nicknameBox.setDisabled(false);

        passwordLabel.setWidth(100 * Math.max((parent.widthRatio / 2), 1));
        passwordLabel.setFontScale(1.5f);

        passwordBox.setWidth(300 * Math.max((parent.widthRatio / 2), 1));
        passwordBox.setDisabled(false);
        passwordBox.setPasswordCharacter('*');
        passwordBox.setPasswordMode(true);

        // set label and box positions
        newXPos = parent.midWidth - (nicknameBox.getWidth() / 4) - nicknameLabel.getWidth();
        newYPos = title.getTop() - title.getHeight() - (parent.displayHeight / 12) - (loginBtn.getHeight() * 1.5f);
        nicknameLabel.setPosition(newXPos, newYPos);
        stage.addActor(nicknameLabel);

        newXPos = nicknameLabel.getRight();
        //newYPos = nicknameLabel.getTop() - ((nicknameLabel.getHeight() * 1.5f) / 2) - ((nicknameBox.getHeight() * 1.5f) / 2);
        nicknameBox.setPosition(newXPos, newYPos);
        stage.addActor(nicknameBox);

        newXPos = parent.midWidth - (passwordBox.getWidth() / 4) - passwordLabel.getWidth();
        newYPos = nicknameLabel.getTop() - (nicknameLabel.getHeight() * 1.5f) - (parent.displayHeight / 12) - (loginBtn.getHeight() * 1.5f);
        passwordLabel.setPosition(newXPos, newYPos);
        stage.addActor(passwordLabel);

        newXPos = passwordLabel.getRight();
        //newYPos = passwordLabel.getTop() - ((passwordLabel.getHeight() * 1.5f) / 2) - ((passwordBox.getHeight() * 1.5f) / 2);
        passwordBox.setPosition(newXPos, newYPos);
        stage.addActor(passwordBox);

        // set up register button
        loginBtn.setTransform(true);
        scale = 1.5f - (parent.heightRatio / parent.widthRatio);
        loginBtn.setScale(scale);
        loginBtn.setPosition((parent.displayWidth - 50 - (loginBtn.getWidth() * scale)), 30);
        stage.addActor(loginBtn);

        backBtn.setTransform(true);
        backBtn.setScale(scale);
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
                String nickname = nicknameBox.getText();
                String password = passwordBox.getText();

                // make sure user entered data for login attempt
                if( nickname.isEmpty() || password.isEmpty())
                {
                    String errorMsg = "Error! Must enter a nickname and password!";
                    JOptionPane.showMessageDialog(null, errorMsg, "Login Error!", JOptionPane.ERROR_MESSAGE);

                    // return if empty nickname or password
                    return;
                }

                // 2. send login request
                OCMessage receivedMessage = parent.getClient().sendLoginRequest(nickname, password);
                if (receivedMessage.get("success").equals("true")) {
                    parent.user = nickname;
                    parent.changeScreen(OmegaChess.SCREEN.LOBBY); // go to lobby screen if successful
                }
                else {
                    // if nickname didn't exist, alert user
                    if (receivedMessage.get("reason").equals("nickname wasn't found")) {
                        String errorMsg = "Error! Nickname wasn't found!";
                        JOptionPane.showMessageDialog(null, errorMsg, "Login Error!", JOptionPane.ERROR_MESSAGE);
                    }

                    // if password was wrong, alert user
                    if (receivedMessage.get("reason").equals("wrong password")) {
                        String errorMsg = "Error! Wrong Password!";
                        JOptionPane.showMessageDialog(null, errorMsg, "Login Error!", JOptionPane.ERROR_MESSAGE);
                    }
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
