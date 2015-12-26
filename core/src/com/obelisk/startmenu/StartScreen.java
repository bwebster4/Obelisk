package com.obelisk.startmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.obelisk.GameMain;
import com.obelisk.world.MapGen;

public class StartScreen implements Screen{

	GameMain GM;
	
	Skin skin;
	Stage stage;
	Table menu;
	TextButton newWorld, play, exit;
	
	public StartScreen(GameMain GM){
		this.GM = GM;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {				
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin(Gdx.files.internal("res/uiskin.json"));	

		menu = new Table();
		menu.setFillParent(true);
		menu.left();
		
		newWorld = new TextButton("New World", skin);
		newWorld.addAction(new NewWorldAction(newWorld));
		menu.add(newWorld).pad(20).size(100, 50).row();
		
		play = new TextButton("play", skin);
		play.addAction(new PlayAction(play));
		menu.add(play).pad(20).size(100, 50).row();
		
		exit = new TextButton("Quit", skin);
		exit.addAction(new ExitAction(exit));
		menu.add(exit).pad(20).size(100, 50);
		
		stage.addActor(menu);
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
		
	}

	
	private class NewWorldAction extends Action{
		TextButton button;
		
		public NewWorldAction(TextButton button){
			this.button = button;
		}
		@Override
		public boolean act(float delta) {
			if (button.isPressed())
				GM.createWorld(MapGen.SMALL, 4);
			return false;
		}
	}
	private class PlayAction extends Action{
		TextButton button;
		
		public PlayAction(TextButton button){
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


}
