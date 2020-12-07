package com.csc14.runtimeterrors.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.csc14.runtimeterrors.game.BoardAssets.MatchScreen;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;


public class OmegaChess extends Game {

	private OCClient client;

	private MainMenuScreen mainMenuScreen;
	private LoginScreen loginScreen;
	private RegisterScreen registerScreen;
	private LobbyScreen lobbyScreen;
	private InviteScreen inviteScreen;
	private ProfileScreen profileScreen;
	private MailboxScreen mailboxScreen;
	private ResumeScreen resumeScreen;
	private RulesScreen rulesScreen;
	private ArchiveScreen archiveScreen;
	private MatchScreen matchScreen;
	private final boolean useLocal;
	private Date currentDate;

	private boolean matchShown = false;
	private int matchId;
	private String whitePlayer;
	private String blackPlayer;

	enum SCREEN{
		LOGIN, REGISTER, MAIN_MENU, LOBBY, INVITE, MATCH, PROFILE, MAILBOX, RESUME_GAME, RULES, ARCHIVE
	}

	private String user = "";

	public OmegaChess(boolean useLocalArg, MatchScreen table)
	{
		useLocal = useLocalArg;
		currentDate = new Date();
		matchScreen = table;

		final java.util.Timer t = new java.util.Timer(true);
		final TimerTask tt = new TimerTask() {
			@Override
			public void run() {
				showNotification();
			}
		};
		t.scheduleAtFixedRate(tt, 0,15000);
	}

	@Override
	public void create() {

		try {
			client = new OCClient(useLocal); // if this fails then server is probably not running

			if (client.sendSquareRequest("10").equals("Square of 10 is 100")) {
				System.out.println("Server/client relationship established.");
			}
			else {
				System.out.println("An error has occurred setting up the server/client relationship.");
			}

		} catch (Exception e) {
			System.out.println("Client failed to initialize. Server is likely not running.");
		}

		mainMenuScreen = new MainMenuScreen(this);
		setScreen(mainMenuScreen);
	}

	public OCClient getClient() {
		return client;
	}

	public String getUser() { return user; }

	public void setUser(String newUser) { user =newUser; }

	public void setMatchInfo(int matchID, String whitePlayer, String blackPlayer) {
		this.matchId = matchID;
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
	}

	public void showNotification() {
		int messageCount = 0;
		if(client != null)
		{
			OCMessage receivedMessage = client.getNotifications(user);	// get the notifications from the user
			System.out.println(receivedMessage.get("success"));
			if(receivedMessage.get("success").equals("true")) {
				int num_messages = Integer.parseInt(receivedMessage.get("count"));
				StringBuilder message = new StringBuilder();

				for (int i = 0; i < num_messages; i++) {
					Date date = getDateFromString(receivedMessage.get("datestring" + (i + 1)));
					if (date.compareTo(currentDate) > 0) {
						currentDate = date;    // update current date
						messageCount += 1;

						message.append(receivedMessage.get("message" + (i + 1)));
					}
				}

				Screen screen = this.getScreen();
				if (messageCount > 0) {
					if (screen instanceof LobbyScreen) {
						// only show the popup if it isn't already displayed
						if (!lobbyScreen.isPopupShown()) {
							lobbyScreen.showNotification(message.toString(), messageCount);
						}
					} else if (screen instanceof InviteScreen) {
						// only show the popup if it isn't already displayed
						if (!inviteScreen.isPopupShown()) {
							inviteScreen.showNotification(message.toString(), messageCount);
						}
					} else if (screen instanceof ProfileScreen) {
						// only show the popup if it isn't already displayed
						if (!profileScreen.isPopupShown()) {
							profileScreen.showNotification(message.toString(), messageCount);
						}
					} else if (screen instanceof MailboxScreen) {
						// only show the popup if it isn't already displayed
						if (!mailboxScreen.isPopupShown()) {
							mailboxScreen.showNotification(message.toString(), messageCount);
						}
					} else if (screen instanceof RulesScreen) {
						// only show the popup if it isn't already displayed
						if (!rulesScreen.isPopupShown()) {
							rulesScreen.showNotification(message.toString(), messageCount);
						}
					} else if (screen instanceof ArchiveScreen) {
						// only show the popup if it isn't already displayed
						if (!archiveScreen.isPopupShown()) {
							archiveScreen.showNotification(message.toString(), messageCount);
						}
					} else if( screen instanceof ResumeScreen) {
						// only show the popup if it isn't already displayed
						if (!resumeScreen.isPopupShown()) {
							resumeScreen.showNotification(message.toString(), messageCount);
						}
					}
				}
			}
		}
	}

	private Date getDateFromString(String s) {
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			date = formatter.parse(s);
		} catch(Exception e) {
			System.out.println("Something went wrong parsing date string: " + s);
			e.printStackTrace();
		}
		return date;
	}

	public void changeScreen(SCREEN screen){
		switch(screen){
			case MAIN_MENU:
				if(mainMenuScreen == null) mainMenuScreen = new MainMenuScreen(this);
				this.setScreen(mainMenuScreen);
				break;
			case LOGIN:
				if(loginScreen == null) loginScreen = new LoginScreen(this);
				this.setScreen(loginScreen);
				break;
			case REGISTER:
				if(registerScreen == null) registerScreen = new RegisterScreen(this);
				this.setScreen(registerScreen);
				break;
			case LOBBY:
				if(lobbyScreen == null) lobbyScreen = new LobbyScreen(this);
				this.setScreen(lobbyScreen);
				break;
			case INVITE:
				if(inviteScreen == null) inviteScreen = new InviteScreen(this);
				this.setScreen(inviteScreen);
				break;
			case MATCH:
				if(!matchShown)
				{
					matchScreen.setTable(this, matchId, whitePlayer, blackPlayer);
					matchShown = true;

					if(lobbyScreen == null) lobbyScreen = new LobbyScreen(this);
					this.setScreen(lobbyScreen);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Can't open a match when one is already open!!", "Error", JOptionPane.ERROR_MESSAGE);
				}

				break;
			case PROFILE:
				if(profileScreen == null) profileScreen = new ProfileScreen(this);
				this.setScreen(profileScreen);
				break;
			case MAILBOX:
				if(mailboxScreen == null) mailboxScreen = new MailboxScreen(this);
				this.setScreen(mailboxScreen);
				break;
			case RESUME_GAME:
				if (resumeScreen == null) resumeScreen = new ResumeScreen(this);
				if(matchShown)
				{
					JOptionPane.showMessageDialog(null, "Can't open a match when one is already open!!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					this.setScreen(resumeScreen);
				}

				break;
			case RULES:
				if (rulesScreen == null) rulesScreen = new RulesScreen(this);
				this.setScreen(rulesScreen);
				break;
			case ARCHIVE:
				if (archiveScreen == null) archiveScreen = new ArchiveScreen(this);
				this.setScreen(archiveScreen);
				break;
		}
	}

	public void changeScreenFromMatch() {
		matchShown = false;
	}

}
