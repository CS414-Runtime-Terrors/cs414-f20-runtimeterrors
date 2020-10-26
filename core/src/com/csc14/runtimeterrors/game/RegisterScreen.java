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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

                // 2. do any error checking to make sure email is valid (has '@')
                if(!is_valid_email(email))
                {
                    String errorMsg = "Error! Email is not a valid format!";
                    JOptionPane.showMessageDialog(null, errorMsg, "Email Error!", JOptionPane.ERROR_MESSAGE);

                    // return if invalid emailo
                    return;
                }

                // 3. make sure nickname isn't empty - register request handles unique
                if(nickname.isEmpty())
                {
                    String errorMsg = "Error! Must enter a nickname";
                    JOptionPane.showMessageDialog(null, errorMsg, "Nickname Error!", JOptionPane.ERROR_MESSAGE);

                    // return if invalid nickname
                    return;
                }

                // 4. decide password complexity and check that before sending request to register
                if(!is_valid_password(password))
                {
                    String errorMsg = "Error! Password does not meet requirements!\nRequirements are:" +
                            "\n*At least 8 characters and max of 20 characters" +
                            "\n*At least 1 digit" +
                            "\n*At least 1 upper case character" +
                            "\n*At least 1 lower case character" +
                            "\n*At least 1 special character from the list: !@#$%&*()-+=^" +
                            "\n*No spaces";
                    JOptionPane.showMessageDialog(null, errorMsg, "Password Error!", JOptionPane.ERROR_MESSAGE);

                    // return if invalid password
                    return;
                }

                // 5. send register request and successful
                if (parent.getClient().sendRegisterRequest(email, nickname, password)) {
                    parent.user = nickname;
                    parent.changeScreen(OmegaChess.SCREEN.LOBBY); // go to login screen if successful
                }
                // unsuccessful registration
                else
                {
                    String errorMsg = "Error with email or nickname!\nCheck to make sure valid email and unique nickname";
                    JOptionPane.showMessageDialog(null, errorMsg, "Register Error!", JOptionPane.ERROR_MESSAGE);

                    // return if invalid nickname
                    return;
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

    /* This function will check if an email address is valid.
        A valid email address contains an '@' sign, no extra
        punctuation before the '.com'/'.org', etc.
     */
    private boolean is_valid_email(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        // generate the regular expression patter to match later
        Pattern p = Pattern.compile(emailRegex);

        // password must not be empty
        if (email.isEmpty()) {
            return false;
        }

        // Use pattern matcher class to see if email
        // fits all criteria of regular expression
        Matcher m = p.matcher(email);

        // Return true if the email matches the criteria
        return m.matches();
    }

    /*  This function will check if a password a user enters is valid
        or not. It checks for: being between 8-20 characters, containing
        at least 1 digit, 1 upper case, 1 lower case, 1 special character
        and no white space.

        Found idea for how to check password complexity on :
        https://www.geeksforgeeks.org/how-to-validate-a-password-using-regular-expressions-in-java/
     */
    private boolean is_valid_password(String password) {
        // Regular expression to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        // generate the regular expression patter to match later
        Pattern p = Pattern.compile(regex);

        // password must not be empty
        if (password.isEmpty()) {
            return false;
        }

        // Use pattern matcher class to see if password
        // fits all criteria of regular expression
        Matcher m = p.matcher(password);

        // Return true if the password matches the criteria
        return m.matches();
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
