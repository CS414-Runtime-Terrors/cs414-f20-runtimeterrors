package com.csc14.runtimeterrors.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

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
	private boolean useLocal;

	enum SCREEN{
		LOGIN, REGISTER, MAIN_MENU, LOBBY, INVITE, MATCH, PROFILE, MAILBOX
	}

	private String user = "";

	public OmegaChess(boolean useLocalArg)
	{
		useLocal = useLocalArg;
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
		}
		else if( screen instanceof InviteScreen ) {
		}
		else if( screen instanceof MatchScreen ) {
			matchScreen.showNotification();
		}
		else if( screen instanceof ProfileScreen ) {
		}
		else if( screen instanceof MailboxScreen ) {
		}


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
		}
	}
}
