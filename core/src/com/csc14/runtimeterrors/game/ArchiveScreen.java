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
    private final OmegaChess parent;
    private final Stage stage;
    private Skin skin;
    private boolean isPopupDisplayed = false;
    private final String nickname;

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

        // add buttons to the screen
        addButtonsToStage();

        // populate archiveBox
        populateArchiveBox();
    }

    private void populateArchiveBox() {
        int num_rows = 15;
        String label = nickname + "'s Archive";
        Table archiveTable = new Table();
        archiveTable.setWidth(525);
        archiveTable.setHeight(400);
        archiveTable.setPosition(50, 50);
        stage.addActor(archiveTable);

        Table labels = new Table();
        ScrollPane scroll = new ScrollPane(labels, skin);
        archiveTable.add(scroll).expand().fill();
        archiveTable.row();
        archiveTable.add(new Label(label, skin));

        OCMessage receivedMessage = parent.getClient().getGameRecords(nickname);

        labels.add(new Label("Game History", skin));
        labels.row();
        int activeCount = 1;

        if(receivedMessage.get("success").equals("true"))
        {
            int count = Integer.parseInt(receivedMessage.get("number"));
            for(int i = 0; i < count; i++)
            {
                activeCount++;
                String str;
                String otherPlayer = receivedMessage.get("user" + (i+1));
                String totalMoves = receivedMessage.get("moves" + (i+1));
                if( receivedMessage.get("result" + (i+1)).equals("tie"))
                {
                    str = "You and " + otherPlayer + " tied! In " + totalMoves + " moves.";
                }
                else if( receivedMessage.get("result" + (i+1)).equals(nickname))
                {
                    str = "You won against " + otherPlayer + " in " + totalMoves + " moves.";
                }
                else
                {
                    str = "You lost to " + otherPlayer + " in " + totalMoves + " moves.";
                }

                labels.add(new Label(str, skin) {
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

    private void addButtonsToStage() {
        TextButton profileBtn = new TextButton("Profile", skin);

        // set up lobby button
        profileBtn.setTransform(true);
        profileBtn.setScale(0.4f);
        profileBtn.setPosition(450, 15);
        stage.addActor(profileBtn);

        // profile button will take user to profile screen
        profileBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(OmegaChess.SCREEN.PROFILE);
            }
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
        stage.clear();
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
