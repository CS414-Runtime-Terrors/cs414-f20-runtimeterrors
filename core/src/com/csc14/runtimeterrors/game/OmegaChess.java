package com.csc14.runtimeterrors.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

/*class getNotifications extends TimerTask {
	private OmegaChess chessClass;
	public getNotifications(OmegaChess chess){
		chessClass = chess;
	}
	public void run() {
		chessClass.showNotification();
		System.out.println("Hello World!");
	}
}*/

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
	private String currentDate;

	enum SCREEN{
		LOGIN, REGISTER, MAIN_MENU, LOBBY, INVITE, MATCH, PROFILE, MAILBOX, RESUME_GAME
	}

	private String user = "";

	public OmegaChess(boolean useLocalArg)
	{
		useLocal = useLocalArg;

		final java.util.Timer t = new java.util.Timer(true);
		final TimerTask tt = new TimerTask() {
			@Override
			public void run() {
				showNotification();
				System.out.println("Hey");

			}
		};

		t.scheduleAtFixedRate(tt, 0,30000);
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
		Screen screen = this.getScreen();

		if(screen instanceof MainMenuScreen ) {
		}
		else if( screen instanceof LoginScreen ) {
		}
		else if( screen instanceof  RegisterScreen ) {
		}
		else if( screen instanceof LobbyScreen ) {
			// only show the popup if it isn't already displayed
			if(!lobbyScreen.isPopupShown())
			{
				lobbyScreen.showNotification();
			}
		}
		else if( screen instanceof InviteScreen ) {
		}
		else if( screen instanceof MatchScreen ) {
			System.out.println("Here");
			// only show the popup if it isn't already displayed
			if(!matchScreen.isPopupShown())
			{
				System.out.println("Here2");
				matchScreen.showNotification();
			}
		}
		else if( screen instanceof ProfileScreen ) {
		}
		else if( screen instanceof MailboxScreen ) {
		}


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
