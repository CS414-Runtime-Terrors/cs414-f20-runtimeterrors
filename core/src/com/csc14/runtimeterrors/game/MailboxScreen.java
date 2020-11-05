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
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MailboxScreen implements Screen {
    private OmegaChess parent;
    private Stage stage;
    private Skin skin;
    private TextButton inboxBtn, outboxBtn;
    private String nickname;
    private Label.LabelStyle style_label;
    private Table inboxTable, outboxTable, mailboxTable;
    private ScrollPane scrollView;

    // columns for inbox : "Sent By:", "Date Received", "Message Type"
    // sent by will be nickname, date received will be date, message type notification/invite

    // columns for outbox : "Sent To:", "Date Sent", "Message Type"
    // sent to will be nickname, date sent will be date, message type notification/invite

    // small view/expand button on far right

    public MailboxScreen(OmegaChess omegachess){
        parent = omegachess;     // setting the argument to our field.
        nickname = parent.getUser();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        stage.act();
        stage.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.PURPLE;
        style.font.getData().setScale(3f);

        style_label = new Label.LabelStyle();
        style_label.font = new BitmapFont();
        style_label.fontColor = Color.WHITE;
        style_label.font.getData().setScale(2f);

        inboxBtn = new TextButton("Inbox", skin, "toggle");
        outboxBtn = new TextButton("Outbox", skin, "toggle");
        ButtonGroup optionsGrp = new ButtonGroup(inboxBtn, outboxBtn);
        optionsGrp.setMinCheckCount(1);
        optionsGrp.setMaxCheckCount(1);

        inboxBtn.setTransform(true);
        inboxBtn.setPosition(0, 450);
        inboxBtn.setWidth(320);
        stage.addActor(inboxBtn);

        outboxBtn.setTransform(true);
        outboxBtn.setPosition(320, 450);
        outboxBtn.setWidth(320);
        stage.addActor(outboxBtn);

        // add listener for buttons
        addListeners();
    }

    private void addListeners() {
        // unregister button will handle unregistering the user and returning to main menu screen
        inboxBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clearStage();
                populateInbox();
            };
        });

        // back button will return user to main menu screen
        outboxBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clearStage();
                populateOutbox();
            };
        });
    }

    private void clearStage() {
        if(mailboxTable != null )
        {
            mailboxTable.remove();
        }
    }

    private void populateOutbox() {
    }

    private void populateInbox() {
        String label = nickname + "'s Mailbox";
        mailboxTable = new Table();
        mailboxTable.setWidth(525);
        mailboxTable.setHeight(400);
        mailboxTable.setPosition(50, 50);
        stage.addActor(mailboxTable);

        Table labels = new Table();
        mailboxTable.add(new ScrollPane(labels, skin)).expand().fill();
        mailboxTable.row();
        mailboxTable.add(new Label(label, skin));

        // this will loop through the inbox messages
        for (int i = 0; i < 50; i++) {
            labels.add(new Label("Label: " + i, skin) {

                public void draw(Batch batch, float parentAlpha) {
                    
                    super.draw(batch, parentAlpha);
                    int drawn = 0;
                    drawn++;
                }
            });
            labels.row();
        }
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
