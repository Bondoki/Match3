package com.match3.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.match3.game.gamestate.GStateFindMatch;
import com.match3.game.registry.Registry;

public class Match3 extends Game { //ApplicationAdapter {

	public Registry reg;
	
	@Override
	public void create () {

		reg = new Registry();
		reg.changeGameState(new GStateFindMatch(reg));

		// draw
		reg.draw.update();
	}

	@Override
	public void render () {

		// Input
		//reg.input.update();

		// Logic
		reg.logic();

		reg.logic.update();

		// draw
		reg.draw.update();

		if (this.getScreen() != reg )
		{
			this.setScreen(reg);
			Gdx.input.setInputProcessor(reg.input);
		}
		super.render();

	}

	@Override
	public void resize(int width, int height)
	{
		//reg.windowWidth = width;
		//reg.windowHeight = height;
		reg.viewport.update(width, height, true);
		super.resize(width, height);
	}
	
	@Override
	public void dispose () {
		//batch.dispose();
		//img.dispose();
		reg.dispose();
	}
}
