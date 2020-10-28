package com.csc14.runtimeterrors.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.csc14.runtimeterrors.game.OmegaChess;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.forceExit = false;		// suppresses execution failed on application close
		new LwjglApplication(new OmegaChess(), config);
	}
}
