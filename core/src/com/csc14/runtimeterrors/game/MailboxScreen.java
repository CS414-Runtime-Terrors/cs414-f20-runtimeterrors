package com.csc14.runtimeterrors.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import javax.swing.*;

public class MailboxScreen implements Screen {
    private OmegaChess parent;
    private Stage stage;
    private Skin skin, btnSkin;
    private TextButton inboxBtn, outboxBtn, refreshBtn, lobbyBtn;
    private String nickname;
    private Table mailboxTable;
    private ButtonGroup optionsGrp;
    private boolean isPopupDisplayed = false;

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
        btnSkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        inboxBtn = new TextButton("Inbox", skin, "toggle");
        outboxBtn = new TextButton("Outbox", skin, "toggle");
        optionsGrp = new ButtonGroup(inboxBtn, outboxBtn);
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

        lobbyBtn = new TextButton("Lobby", btnSkin);
        refreshBtn = new TextButton("Refresh", btnSkin);

        // set up lobby button
        lobbyBtn.setTransform(true);
        lobbyBtn.setScale(0.4f);
        lobbyBtn.setPosition(50, 15);
        stage.addActor(lobbyBtn);

        // set up refresh button
        refreshBtn.setTransform(true);
        refreshBtn.setScale(0.4f);
        refreshBtn.setPosition(450, 15);
        stage.addActor(refreshBtn);

        // default is inbox
        populateInbox();

        // add listener for buttons
        addListeners();
    }

    private void addListeners() {
        // inbox button will show inbox
        inboxBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clearStage();
                populateInbox();
            };
        });

        // outbox button will show outbox
        outboxBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clearStage();
                populateOutbox();
            };
        });

        // refresh button will refresh current screen
        refreshBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if( optionsGrp.getCheckedIndex() == 0 )
                {
                    clearStage();
                    populateInbox();
                }
                else
                {
                    clearStage();
                    populateOutbox();
                }
            };
        });

        // outbox button will show outbox
        lobbyBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.clear();
                parent.changeScreen(OmegaChess.SCREEN.LOBBY);
            };
        });

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

        final OCMessage receivedMessage = parent.getClient().getReceivedInvites(nickname);

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

                    final String inviter = receivedMessage.get("inviter" + i);

                    Label tmpLabel = new Label(tmp, skin){
                        public void draw(Batch batch, float parentAlpha) {
                            super.draw(batch, parentAlpha);
                        }
                    };

                    tmpLabel.addListener( new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            // first bring confirmation box to make sure user wants to unregister
                            int result = JOptionPane.showConfirmDialog(null, inviter +
                                            " wants to play OmegaChess! Would you like to play?",
                                    "Accept/Decline Invitation",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE);
                            if(result == JOptionPane.YES_OPTION){
                                // send request to send the accept response
                                OCMessage message = parent.getClient().acceptInvite(nickname, inviter);
                                stage.clear();
                                int matchID = Integer.parseInt(message.get("matchID"));
                                parent.changeScreen(OmegaChess.SCREEN.MATCH);
                                parent.setMatchID(matchID);
                            }
                            else if(result == JOptionPane.NO_OPTION){
                                // send request to send the decline response
                                parent.getClient().declineInvite(nickname, inviter);

                                // refresh inbox
                                clearStage();
                                populateInbox();
                            }
                        };
                    });

                    labels.add(tmpLabel);
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
