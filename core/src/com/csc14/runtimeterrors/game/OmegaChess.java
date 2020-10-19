package com.csc14.runtimeterrors.game;

import com.badlogic.gdx.Game;

public class OmegaChess extends Game {
	private MainMenuScreen mainMenuScreen;
	private LoginScreen loginScreen;
	private RegisterScreen registerScreen;

	public final static int LOGIN_SCREEN = 0;
	public final static int REGISTER_SCREEN = 1;
	public final static int MAIN_MENU_SCREEN = 2;

	@Override
	public void create() {
		mainMenuScreen = new MainMenuScreen(this);
		setScreen(mainMenuScreen);
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
