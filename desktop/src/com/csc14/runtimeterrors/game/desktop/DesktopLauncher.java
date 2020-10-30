package com.csc14.runtimeterrors.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.csc14.runtimeterrors.game.OmegaChess;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		int oldWidth = config.width;
		int oldHeight = config.height;
		//config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
		config.title = "Omega Chess";
		config.forceExit = false;		// suppresses execution failed on application close
		OmegaChess game = new OmegaChess();
		game.setDisplayRatios(oldWidth, oldHeight, config.width, config.height);
		new LwjglApplication(game, config);
	}
}
