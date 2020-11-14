package com.csc14.runtimeterrors.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

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
	private MatchScreen matchScreen;
	private ProfileScreen profileScreen;
	private MailboxScreen mailboxScreen;
	private ResumeScreen resumeScreen;
	private boolean useLocal;
	private Date currentDate = null;

	enum SCREEN{
		LOGIN, REGISTER, MAIN_MENU, LOBBY, INVITE, MATCH, PROFILE, MAILBOX, RESUME_GAME
	}
	public enum NotificationType {
		NEW_MATCH, INVITE_REQUEST, ACCEPTED_INVITE, DECLINED_INVITE, MATCH_ENDED,
		INVITE_CANCELLED
	}

	private String user = "";

	public OmegaChess(boolean useLocalArg)
	{
		useLocal = useLocalArg;
		currentDate = new Date();

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
					} else if (screen instanceof MatchScreen) {
						// only show the popup if it isn't already displayed
						if (!matchScreen.isPopupShown()) {
							matchScreen.showNotification(message.toString(), messageCount);
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

	public void setMatchID(int matchID) { matchScreen.setMatchID(matchID); }

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
				if(matchScreen == null) matchScreen = new MatchScreen(this);
				this.setScreen(matchScreen);
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
				this.setScreen(resumeScreen);
				break;
		}
	}
}
