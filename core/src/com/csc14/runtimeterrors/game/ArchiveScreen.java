package com.csc14.runtimeterrors.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import javax.swing.*;

public class ArchiveScreen implements Screen {
    private OmegaChess parent;
    private Stage stage;
    private TextButton profileBtn;
    private Skin skin;
    private Label.LabelStyle style_label;
    private String nickname;
    private boolean isPopupDisplayed = false;
    private Table archiveTable;

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

        // populate archiveBox
        populateArchiveBox();

        // add listener for buttons
        addListeners();
    }

    private void populateArchiveBox() {
        int num_rows = 15;
        String label = nickname + "'s Archive";
        archiveTable = new Table();
        archiveTable.setWidth(525);
        archiveTable.setHeight(400);
        archiveTable.setPosition(50, 50);
        stage.addActor(archiveTable);

        Table labels = new Table();
        ScrollPane scroll = new ScrollPane(labels, skin);
        archiveTable.add(scroll).expand().fill();
        archiveTable.row();
        archiveTable.add(new Label(label, skin));

        OCMessage receivedMessage = parent.getClient().getSentInvites(nickname);

        int spacingSeparation = 30;                   // number of spaces between columns
        int longest = Integer.parseInt(receivedMessage.get("maxNicknameLength"));  // length of the widest column
        int spacing = longest + spacingSeparation;

        String columns = String.format("%-" + spacing + "s%-" + spacing + "s",  // format
                "Player", "Result & Moves");

        labels.add(new Label(columns, skin));
        labels.row();
        int activeCount = 1;

        if(receivedMessage.get("success").equals("true"))
        {
            int count = Integer.parseInt(receivedMessage.get("totalCount"));
            for(int i = 0; i < count; i++)
            {
                // only show in inbox if it hasn't been accepted or declined
                if( receivedMessage.get("accepted" + i).equals("false") &&
                        receivedMessage.get("declined" + i).equals("false"))
                {
                    activeCount++;
                    String tmp = String.format("%-" + spacing + "s%-" + spacing + "s",  // format
                            receivedMessage.get("invitee" + i), "Invite Request");

                    labels.add(new Label(tmp, skin) {

                        public void draw(Batch batch, float parentAlpha) {

                            super.draw(batch, parentAlpha);
                        }
                    });
                    labels.row();
                }
            }

            // add empty rows to put text at the top of scroll pane rather than middle
            for(int i = 0; i < num_rows-activeCount; i++)
            {
                labels.add(new Label("", skin) {
                    public void draw(Batch batch, float parentAlpha) {
                        super.draw(batch, parentAlpha);
                    }
                });
                labels.row();
            }
        }
    }

    private void addButtonsToStage() {
        profileBtn = new TextButton("Profile", skin);

        // set up lobby button
        profileBtn.setTransform(true);
        profileBtn.setScale(0.4f);
        profileBtn.setPosition(450, 15);
        stage.addActor(profileBtn);
    }

    private void addListeners() {
        // back button will return user to main menu screen
        profileBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(OmegaChess.SCREEN.PROFILE);
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
