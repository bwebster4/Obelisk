package com.obelisk.startmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.obelisk.GameMain;
import com.obelisk.InputHandler;

public class StartScreen implements Screen{

	GameMain GM;
	InputHandler input;
	
	Sprite new_button, exit_button;
	SpriteBatch batch;
	Texture buttons;
	TextureRegion new_on, new_off, exit_on, exit_off;
	
	Vector3 mousepos;
	
	public StartScreen(GameMain GM, InputHandler input){
		this.GM = GM;
		this.input = input;
	}
	
	@Override
	public void render(float delta) {
		mousepos = input.getMousePos();
		if (new_button.getBoundingRectangle().contains(mousepos.x, mousepos.y)){
			new_button.setRegion(new_on);
			if (input.isLeftButtonPressed()){
				GM.startGame();
			}
		}else{
			new_button.setRegion(new_off);
		}	
		if (exit_button.getBoundingRectangle().contains(mousepos.x, mousepos.y)){
			exit_button.setRegion(exit_on);
			if (input.isLeftButtonPressed()){
				GM.exit();
			}
		}else{
			exit_button.setRegion(exit_off);
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		new_button.draw(batch);
		exit_button.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		
		batch = new SpriteBatch();
		
		buttons = new Texture(Gdx.files.internal("res/Buttons.png"));
		
		new_on = new TextureRegion(buttons, 0, 64, 128, 64);
		new_off = new TextureRegion(buttons, 128, 64, 128, 64);
		new_button = new Sprite(new_off);
		new_button.setPosition(10, 10);
		
		exit_on = new TextureRegion(buttons, 0, 0, 128, 64);
		exit_off = new TextureRegion(buttons, 128, 0, 128, 64);
		exit_button = new Sprite(exit_off);
		exit_button.setPosition(10, 100);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		buttons.dispose();
	}

}
