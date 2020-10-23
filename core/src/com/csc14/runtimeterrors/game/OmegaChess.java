package com.csc14.runtimeterrors.game;

import com.badlogic.gdx.Game;

public class OmegaChess extends Game {

	private OCClient client;

	private MainMenuScreen mainMenuScreen;
	private LoginScreen loginScreen;
	private RegisterScreen registerScreen;

	public final static int LOGIN_SCREEN = 0;
	public final static int REGISTER_SCREEN = 1;
	public final static int MAIN_MENU_SCREEN = 2;

	@Override
	public void create() {

		try {
			client = new OCClient(); // if this fails then server is probably not running

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

	public void changeScreen(int screen){
		switch(screen){
			case MAIN_MENU_SCREEN:
				if(mainMenuScreen == null) mainMenuScreen = new MainMenuScreen(this);
				this.setScreen(mainMenuScreen);
				break;
			case LOGIN_SCREEN:
				if(loginScreen == null) loginScreen = new LoginScreen(this);
				this.setScreen(loginScreen);
				break;
			case REGISTER_SCREEN:
				if(registerScreen == null) registerScreen = new RegisterScreen(this);
				this.setScreen(registerScreen);
				break;
		}
	}
}
