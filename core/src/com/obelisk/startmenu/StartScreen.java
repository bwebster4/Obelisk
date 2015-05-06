package com.obelisk.startmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
<<<<<<< HEAD
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.obelisk.GameMain;
=======
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.obelisk.GameMain;
import com.obelisk.InputHandler;
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a

public class StartScreen implements Screen{

	GameMain GM;
<<<<<<< HEAD
	
	Skin skin;
	Stage stage;
	Table menu;
	TextButton newWorld, exit;
	
	public StartScreen(GameMain GM){
		this.GM = GM;
=======
	InputHandler input;
	
	Sprite new_button, exit_button;
	SpriteBatch batch;
	Texture buttons;
	TextureRegion new_on, new_off, exit_on, exit_off;
	
	Vector3 mousepos;
	
	public StartScreen(GameMain GM, InputHandler input){
		this.GM = GM;
		this.input = input;
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
	}
	
	@Override
	public void render(float delta) {
<<<<<<< HEAD
		stage.act();
		stage.draw();
=======
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
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
<<<<<<< HEAD
				
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin(Gdx.files.internal("res/uiskin.json"));	

		menu = new Table();
		menu.setFillParent(true);
		menu.left();
		
		newWorld = new TextButton("New World", skin);
		newWorld.addAction(new NewWorldAction(newWorld));
		menu.add(newWorld).pad(20).size(100, 50).row();
		
		exit = new TextButton("Quit", skin);
		exit.addAction(new ExitAction(exit));
		menu.add(exit).pad(20).size(100, 50);
		
		stage.addActor(menu);
=======
		
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
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
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
<<<<<<< HEAD
		
	}

	
	private class NewWorldAction extends Action{
		TextButton button;
		
		public NewWorldAction(TextButton button){
			this.button = button;
		}
		@Override
		public boolean act(float delta) {
			if (button.isPressed())
				GM.startGame();
			return false;
		}
	}
	private class ExitAction extends Action{
		TextButton button;
		
		public ExitAction(TextButton button){
			this.button = button;
		}
		@Override
		public boolean act(float delta) {
			if(button.isPressed())
				GM.exit();
			return false;
		}
	}
=======
		buttons.dispose();
	}

>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
}
