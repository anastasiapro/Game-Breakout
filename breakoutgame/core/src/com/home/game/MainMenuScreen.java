package com.home.game;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.home.game.BreakoutGame;

public class MainMenuScreen implements Screen, InputProcessor {
	
	private BreakoutGame gameG;
	private Texture bgTexture;
	private SpriteBatch spriteBatch;
	float CAMERA_WIDTH = Gdx.graphics.getWidth();
	float CAMERA_HEIGHT = Gdx.graphics.getHeight();
	public HashMap<String, Texture> textures;
	boolean downBtn;

	@Override
	public void show() {
		textures = new HashMap<String, Texture>();
		downBtn = false;
		spriteBatch = new SpriteBatch();
		loadTextures();
	}

	private void loadTextures() {

		bgTexture = new Texture(Gdx.files.internal("background.jpg"));
		textures.put("starton", new Texture(Gdx.files.internal("starton.jpg")));
		textures.put("startoff", new Texture(Gdx.files.internal("startoff.jpg")));
	}

	public void showMenu() {
		if (downBtn) {
			spriteBatch.draw(textures.get("startoff"),
					CAMERA_WIDTH / 3.5f, CAMERA_HEIGHT - CAMERA_HEIGHT * 0.6f,	CAMERA_WIDTH * 0.4f, CAMERA_HEIGHT * 0.2f);
		} else {
			spriteBatch.draw(textures.get("starton"),
					CAMERA_WIDTH / 3.5f, CAMERA_HEIGHT - CAMERA_HEIGHT * 0.6f,	CAMERA_WIDTH * 0.4f, CAMERA_HEIGHT * 0.2f);
		}

	}

	public void showBG() {
		spriteBatch.draw(bgTexture, 0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

	}

	public MainMenuScreen(final BreakoutGame game) {
		this.gameG = game;
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public boolean keyUp(final int keycode) {
		return true;
	}

	@Override
	public void hide() {
	}

	@Override
	public void render(final float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		showBG();
		showMenu();
		spriteBatch.end();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean keyTyped(final char character) {
		return false;
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		bgTexture.dispose();
		textures.clear();
		this.dispose();
	}

	@Override
	public boolean touchDragged(final int x, final int y, final int pointer) {
		return false;
	}

	// @Override
	public boolean touchMoved(final int x, final int y) {
		return false;
	}

	@Override
	public boolean mouseMoved(final int x, final int y) {
		return false;
	}

	@Override
	public boolean scrolled(final int amount) {
		return false;
	}

	@Override
	public boolean touchDown(final int x, final int y, final int pointer,
			final int button) {
		if (x > CAMERA_WIDTH / 2.5f	&& y > CAMERA_HEIGHT - CAMERA_HEIGHT * 0.98f) {
			downBtn = true;
		}
		return true;
	}

	@Override
	public boolean keyDown(final int keycode) {
		return true;
	}

	@Override
	public boolean touchUp(final int x, final int y, final int pointer,
			final int button) {
		if (downBtn) {
			gameG.breakoutScreen = new BreakoutScreen(gameG);
			gameG.setScreen(gameG.breakoutScreen);
			downBtn = false;
		}
		return true;
	}

	@Override
	public void resize(final int width, final int height) {
	}
	
}