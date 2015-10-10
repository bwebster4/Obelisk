package com.obelisk.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.obelisk.GameMain;
import com.obelisk.InputHandler;
import com.obelisk.world.entities.EntityManager;
import com.obelisk.world.items.ItemManager;
import com.obelisk.world.pathfinding.PathfindingManager;
import com.obelisk.world.physics.Collisions;

public class WorldMain implements Screen {

	int state;
	static final int LOADING = 0;
	static final int RUNNING = 1;
	static final int PAUSED = 2;
	static final int ENDING = 3;
	
	// Loading Screen ==========
	Stage stage;
	Skin skin;
	Label loading;
	int loadCounter = 3;
	
	float WIDTH, HEIGHT;
	float SEED;
	public OrthographicCamera camera;
	private SpriteBatch batch;
	float camerax, cameray;
	public static Vector3 touchpos;
	public Vector3 cursorpos, viewportsize, campos;
	Texture cursortexture;
	Sprite cursorsprite;
	Rectangle camrect;
	
	boolean created = false, paused = false, shaded = false;
	
	public Ui ui;

	public static Map map;
	public static EntityManager entitymanager;
	public static PathfindingManager PM;
	ItemManager itemmanager;
	InputHandler input;
	Collisions collisions;
	GameMain GM;
	DiamondSquare diamondsquare;
	
	public static int tic = 0;
	
	public WorldMain (GameMain GM, InputHandler input, float WIDTH, float HEIGHT){
		this.GM = GM;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.input = input;
		
		SEED = 8.1f;
	}
	
	@Override
	public void render(float delta) {
		switch(state){
		case LOADING:
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			if(!map.isGenerating()){
				state = RUNNING;	
			}
			
			if(GameMain.isSec(false)){
				switch(loadCounter){
				case 1:
					loading.setText("Loading.");
					loadCounter = 2;
					break;
				
				case 2:
					loading.setText("Loading..");
					loadCounter = 3;
					break;
				
				case 3:
					loading.setText("Loading...");
					loadCounter = 1;
					break;
				}
			}
			
			stage.act();
			stage.draw();
			break;

		// When running do this ---------------------------
			case RUNNING:{
			tic++;
			if (tic == 86400)
				tic = 0;
			
			collisions.updateChunkArray(map.chunkarray);
			
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			camerax = entitymanager.getplayerX(); cameray = entitymanager.getplayerY();
			camera.position.set(camerax, cameray, 1f); 
			camera.zoom = 28f;
			if (camera.position.x - camera.viewportWidth * camera.zoom / 2 < 0)
				camera.position.x = camera.viewportWidth * camera.zoom / 2;
			else if (camera.position.x + camera.viewportWidth * camera.zoom / 2 > Map.WORLD_SIZE)
				camera.position.x = Map.WORLD_SIZE - camera.viewportWidth * camera.zoom / 2;
			if (camera.position.y - camera.viewportHeight * camera.zoom / 2  < 0)
				camera.position.y = camera.viewportHeight * camera.zoom / 2;
			else if (camera.position.y + camera.viewportHeight * camera.zoom / 2> Map.WORLD_SIZE)
				camera.position.y = Map.WORLD_SIZE - camera.viewportHeight * camera.zoom / 2;
			camera.update();
			viewportsize.set(camera.viewportWidth, camera.viewportHeight, 0);
			camera.unproject(viewportsize);
			campos.set(camera.position.x, camera.position.y, 0);
			camera.unproject(campos);
			camrect.set(campos.x - camera.viewportWidth * 40f, campos.y - camera.viewportHeight * 40f, 2 * camera.viewportWidth * 40f, 2 * camera.viewportHeight*40f);
			
			touchpos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchpos);
			cursorsprite.setPosition(touchpos.x - cursorsprite.getWidth() / 2, touchpos.y  - cursorsprite.getHeight() / 2);
			float angle = MathUtils.atan2(touchpos.y - entitymanager.getplayerY(), touchpos.x - entitymanager.getplayerX());

			touchpos.x = cursorsprite.getX() + cursorsprite.getWidth() / 2;
			touchpos.y = cursorsprite.getY() + cursorsprite.getHeight() / 2;
			
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			
			map.render(batch, touchpos, camrect);
			itemmanager.render(batch);
			entitymanager.render(batch, angle, camrect);
			
			map.drawShader(batch, camrect);
			
			batch.end();
			
			ui.render(state);

			break;
			}
			// When paused do this --------------------------------
			case PAUSED:
			ui.render(state);

			break;
			
			case ENDING:
				GM.endGame();
				break;

	}

	}
	
	@Override
	public void resize(int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
		
		camera.viewportWidth = 1;
		camera.viewportHeight = HEIGHT/WIDTH;
	}

	@Override
	public void show() {
		
		created = true;
		state = LOADING;
		
		// ===== Loading Screen stuff
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("res/uiskin.json"));
		loading = new Label("Loading...", skin);
		loading.setFontScale(1.5f);
		loading.setOrigin(WIDTH / 2 - loading.getWidth() / 2, HEIGHT / 2 - loading.getHeight() / 2);
		stage.addActor(loading);
		
		camera = new OrthographicCamera(1, HEIGHT/WIDTH);
		viewportsize = new Vector3(camera.viewportWidth, camera.viewportHeight, 0);
		camera.unproject(viewportsize);
		campos = new Vector3(camera.position.x, camera.position.y, 0);
		camera.unproject(campos);
		camrect = new Rectangle(campos.x, campos.y, viewportsize.x, viewportsize.y);
		
		batch = new SpriteBatch();
		//batch.setBlendFunction(GL10.GL_NEAREST, GL10.GL_NEAREST);
		//batch.disableBlending();
		
		touchpos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		cursorpos = new Vector3(touchpos);

		itemmanager = new ItemManager();
		
		ui = new Ui();
		ui.show(HEIGHT, WIDTH, GM);
		
		cursortexture = new Texture(Gdx.files.internal("res/Cursor.png"));
		cursorsprite = new Sprite(cursortexture);
		cursorsprite.setOrigin(cursorsprite.getWidth() / 2, cursorsprite.getHeight() / 2);
		cursorsprite.setSize(.4f, .4f * cursorsprite.getHeight() / cursorsprite.getWidth());
		cursorsprite.setColor(0, 0, 0, .5f);
		//Gdx.input.setCursorCatched(true);
		
		diamondsquare = new DiamondSquare();
		diamondsquare.show(SEED);
		
		map = new Map();
		PM = new PathfindingManager();
		map.show(input, diamondsquare, PM, SEED, itemmanager);
		
		collisions = new Collisions(map.chunkarray, input, map);
		
		PM.show(map);
		entitymanager = new EntityManager();
		entitymanager.show(input, collisions, this, itemmanager, PM);
	}

//	public void addItem(Item item, Character c){
//		itemmanager.addItem(item);
//		c.addItem(item, false);
//	}
//	public void removeItem(Item item, Character c){
//		itemmanager.removeItem(item);
//		c.removeItem(item);
//	}
//	public int getTic(){
//		return tic;
//	}
	
	@Override
	public void hide() {
	}

	@Override
	public void pause() {
		state = PAUSED;
		paused = true;
		shaded = false;
		Gdx.input.setCursorCatched(false);

		

	}
	public boolean getPaused(){
		return paused;
	}
	@Override
	public void resume() {	
		state = RUNNING;
		paused = false;

		//Gdx.input.setCursorCatched(true);

	}

	public void endGame(){
		state = ENDING;
	}
	
	@Override
	public void dispose() {
		if (created){
			map.dispose();
			entitymanager.dispose();
			cursortexture.dispose();
			ui.dispose();	
		}


	}

}
