package com.csc14.runtimeterrors.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.csc14.runtimeterrors.game.BoardAssets.MatchScreen;
import com.csc14.runtimeterrors.game.OmegaChess;

public class DesktopLauncher {
	static private OmegaChess chessApp;
	public static void main (String[] arg) {
		MatchScreen table = new MatchScreen();
		if( arg.length == 1 )
		{
			if(arg[0].equals("true"))
			{
				chessApp = new OmegaChess(true, table);
			}
		}
		else
		{
			chessApp = new OmegaChess(false, table);
		}



		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.forceExit = false;		// suppresses execution failed on application close
		new LwjglApplication(chessApp, config);


	}
}
