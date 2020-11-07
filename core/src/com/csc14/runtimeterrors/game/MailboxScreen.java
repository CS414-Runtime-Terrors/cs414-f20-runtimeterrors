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
    private Table mailboxTable;

    // small view/expand button on far right

    public MailboxScreen(OmegaChess omegachess){
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
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.PURPLE;
        style.font.getData().setScale(3f);

        inboxBtn = new TextButton("Inbox", skin, "toggle");
        outboxBtn = new TextButton("Outbox", skin, "toggle");
        ButtonGroup optionsGrp = new ButtonGroup(inboxBtn, outboxBtn);
        optionsGrp.setMinCheckCount(1);
        optionsGrp.setMaxCheckCount(1);
        optionsGrp.setChecked("Inbox");

        inboxBtn.setTransform(true);
        inboxBtn.setPosition(0, 450);
        inboxBtn.setWidth(320);
        stage.addActor(inboxBtn);

        outboxBtn.setTransform(true);
        outboxBtn.setPosition(320, 450);
        outboxBtn.setWidth(320);
        stage.addActor(outboxBtn);

        // default is inbox
        populateInbox();

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
        int num_rows = 15;
        String label = nickname + "'s Outbox";
        mailboxTable = new Table();
        mailboxTable.setWidth(525);
        mailboxTable.setHeight(400);
        mailboxTable.setPosition(50, 50);
        stage.addActor(mailboxTable);

        Table labels = new Table();
        ScrollPane scroll = new ScrollPane(labels, skin);
        mailboxTable.add(scroll).expand().fill();
        mailboxTable.row();
        mailboxTable.add(new Label(label, skin));

        OCMessage receivedMessage = parent.getClient().getSentInvites(nickname);

        int spacingSeparation = 30;                   // number of spaces between columns
        int longest = Integer.parseInt(receivedMessage.get("maxNicknameLength"));  // length of the widest column
        int spacing = longest + spacingSeparation;

        String columns = String.format("%-" + spacing + "s%-" + spacing + "s",  // format
                "Sent To:", "Message Type");

        labels.add(new Label(columns, skin));
        labels.row();

        if(receivedMessage.get("success").equals("true"))
        {
            int count = Integer.parseInt(receivedMessage.get("totalCount"));
            int activeCount = 1;
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

    private void populateInbox() {
        int num_rows = 15;
        String label = nickname + "'s Inbox";
        mailboxTable = new Table();
        mailboxTable.setWidth(525);
        mailboxTable.setHeight(400);
        mailboxTable.setPosition(50, 50);
        stage.addActor(mailboxTable);

        Table labels = new Table();
        ScrollPane scroll = new ScrollPane(labels, skin);
        mailboxTable.add(scroll).expand().fill();
        mailboxTable.row();
        mailboxTable.add(new Label(label, skin));

        OCMessage receivedMessage = parent.getClient().getReceivedInvites(nickname);

        int spacingSeparation = 30;                   // number of spaces between columns
        int longest = Integer.parseInt(receivedMessage.get("maxNicknameLength"));  // length of the widest column
        int spacing = longest + spacingSeparation;

        String columns = String.format("%-" + spacing + "s%-" + spacing + "s",  // format
                "Sent By:", "Message Type");

        labels.add(new Label(columns, skin));
        labels.row();

        if(receivedMessage.get("success").equals("true"))
        {
            int count = Integer.parseInt(receivedMessage.get("totalCount"));
            int activeCount = 1;
            for(int i = 0; i < count; i++)
            {
                // only show in inbox if it hasn't been accepted or declined
                if( receivedMessage.get("accepted" + i).equals("false") &&
                    receivedMessage.get("declined" + i).equals("false"))
                {
                    activeCount++;
                    String tmp = String.format("%-" + spacing + "s%-" + spacing + "s",  // format
                            receivedMessage.get("inviter" + i), "Invite Request");

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
