package com.obelisk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.obelisk.startmenu.StartScreen;
import com.obelisk.world.MapGen;
import com.obelisk.world.WorldMain;
import com.obelisk.world.mapelems.MapLoc;

public class GameMain extends Game {
	public WorldMain planetscreen;
	public StartScreen startscreen;
	MapGen mapGen;
	InputHandler input;
	
	boolean setup = false;
	
	boolean running;
	static int secCounter = 0, halfCounter = 0;
	public float WIDTH, HEIGHT;
	
	@Override
	public void create() {		
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		
		input = new InputHandler(WIDTH, HEIGHT);
		running = true;
		
		mapGen = new MapGen();
		
		startscreen = new StartScreen(this);
		setScreen(startscreen);
	}

	@Override
	public void dispose() {
		startscreen.dispose();
		if (planetscreen != null)
			planetscreen.dispose();
		Gdx.app.exit();
	}

	@Override
	public void render() {
		if (!running){
			dispose();
		}else{
			isSec(true);
			isHalfSec(true);
			input.update();
			if (input.isEscPressed){
				if (getScreen().equals(startscreen)){
					exit();
				}else if (getScreen().equals(planetscreen)){
					if (planetscreen.getPaused())
						planetscreen.resume();
					else
						planetscreen.pause();
				}
			}
			getScreen().render(1);
			//System.out.println(Gdx.graphics.getFramesPerSecond());
		}
	}

	public void exit(){
		running = false;
	}
	public void endGame(){
		planetscreen.dispose();
		setScreen(startscreen);

		planetscreen = null;
		
	}
	
	@Override
	public void resize(int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
		
		if (setup){
			planetscreen.resize(width, height);
			input.resize(width, height);
		}
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	public void startGame(FileHandle map){
		planetscreen = new WorldMain(this, input, WIDTH, HEIGHT, map);
		setScreen(planetscreen);
		setup = true;
	}
	public void createWorld(int size, int plates){
		mapGen.generateMap("Saves/NewWorld.bin", MapGen.SMALL, 4);
	}
	// ====== Game Time Counters
	
	static boolean isSec = false;
	public static boolean isSec(boolean update){
		if (update){
			secCounter++;
			if (secCounter == 60){
				secCounter = 0;
				isSec = true;
			}else
				isSec = false;
		}
		return isSec;
	}
	
	static boolean halfSec = false;
	public static boolean isHalfSec(boolean update){
		if (update){
			halfCounter++;
			if (halfCounter == 30){
				halfCounter = 0;
				halfSec = true;
			}else
				halfSec = false;
		}
		return halfSec;
	}
	
	// ========== Dice Rolls
	
	public static int rollDice(String dice){
		int count = Character.getNumericValue(dice.charAt(0));
		int type = Integer.parseInt(dice.substring(2));
		int n = 0;
		for (int i = 0; i < count; i++){
			n += MathUtils.random(1, type);
		}
		return n;
	}

}
