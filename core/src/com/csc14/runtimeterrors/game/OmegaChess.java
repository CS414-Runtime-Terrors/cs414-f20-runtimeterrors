package com.csc14.runtimeterrors.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;

public class OmegaChess extends Game {
	//SpriteBatch batch;
	//Texture img;
	private LoginScreen loginScreen;
	//private PreferencesScreen preferencesScreen;
	//private MenuScreen menuScreen;
	//private MainScreen mainScreen;
	//private EndScreen endScreen;

	public final static int LOGIN_SCREEN = 0;
	public final static int PREFERENCES = 1;
	public final static int APPLICATION = 2;
	public final static int ENDGAME = 3;
	
	/*@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}*/

	@Override
	public void create() {
		loginScreen = new LoginScreen(this);
		setScreen(loginScreen);
	}

	public void changeScreen(int screen){
		switch(screen){
			case LOGIN_SCREEN:
				if(loginScreen == null) loginScreen = new LoginScreen(this);
				this.setScreen(loginScreen);
				break;
			case PREFERENCES:
				//if(preferencesScreen == null) preferencesScreen = new PreferencesScreen();
				//this.setScreen(preferencesScreen);
				break;
			case APPLICATION:
				//if(mainScreen == null) mainScreen = new MainScreen();
				//this.setScreen(mainScreen);
				break;
			case ENDGAME:
				//if(endScreen == null) endScreen = new EndScreen();
				//this.setScreen(endScreen);
				break;
		}
	}
}
