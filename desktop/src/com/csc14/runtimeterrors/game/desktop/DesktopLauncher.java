package com.csc14.runtimeterrors.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.csc14.runtimeterrors.game.MainMenuScreen;
import com.csc14.runtimeterrors.game.OmegaChess;

public class DesktopLauncher {
	private MainMenuScreen loginScreen;
	//private PreferencesScreen preferencesScreen;
	//private MenuScreen menuScreen;
	//private MainScreen mainScreen;
	//private EndScreen endScreen;

	public final static int LOGIN_SCREEN = 0;
	public final static int PREFERENCES = 1;
	public final static int APPLICATION = 2;
	public final static int ENDGAME = 3;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.forceExit = false;		// suppresses execution failed on application close
		new LwjglApplication(new OmegaChess(), config);
	}
}
