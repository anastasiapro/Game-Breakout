package com.home.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;


public class BreakoutGame extends Game {

	public static float WIDTH = 800;
	public static float HEIGHT = 600;
	public static Vector2 scale = new Vector2();

	private MainMenuScreen mainMenuScreen;
	private AssetManager manager;
	BreakoutScreen breakoutScreen;

	@Override
	public void create() {
		scale.x = Gdx.graphics.getWidth() / BreakoutGame.WIDTH;
		scale.y = Gdx.graphics.getHeight() / BreakoutGame.HEIGHT;

		manager = new AssetManager();
		mainMenuScreen = new MainMenuScreen(this);
		breakoutScreen = new BreakoutScreen(this);
		setScreen(mainMenuScreen);
		
	}

	

	@Override
	public void dispose() {
		manager.dispose();
		mainMenuScreen.dispose();
    	breakoutScreen.dispose();
	}

	public void startGame() {
		breakoutScreen.reset();
		setScreen(breakoutScreen);
	}

	public void endGame() {
	}


	public AssetManager getAssetManager() {
		return manager;
	}
}
