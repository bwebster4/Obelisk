package com.obelisk.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.obelisk.InputHandler;
import com.obelisk.world.mapelems.Block;
import com.obelisk.world.mapelems.Stone;
import com.obelisk.world.items.ItemManager;
import com.obelisk.world.mapelems.Tile;
import com.obelisk.world.pathfinding.FloodFill;
import com.obelisk.world.pathfinding.Node;
import com.obelisk.world.pathfinding.PathfindingManager;


public class Map {

	float grassthreshold = 7.8f;
	float lightgrassthreshold = 7.8f;
	float stonethreshold = 20f;//8.1f;

	TextureAtlas mapElems;
	TextureRegion grassTexture, lightGrassTexture, stoneTexture;
	
	Tile tile;
	Block block;
	Chunk chunk;
	InputHandler input;
	ItemManager itemmanager;
	PathfindingManager PM;
	FloodFill floodfill;
	
	Thread areaGen;
	boolean isGenerating = true;
	
	public static final int CHUNK_SIZE = 16;
	public static final int WORLD_CHUNKS = 16;
	public static final int WORLD_SIZE = CHUNK_SIZE * WORLD_CHUNKS;
	
	int time = 17000;
	int maxtime = 86400;
	int maxbrightness = 20;
	int ambientlight = 7;
	
	Array<Chunk> chunkarray = new Array<Chunk>();
	Chunk[][] chunks = new Chunk[WORLD_CHUNKS][WORLD_CHUNKS];
	Boolean[][] walkable = new Boolean[WORLD_SIZE][WORLD_SIZE];
	
	Array<Area> areas = new Array<Area>();
	
	public void show(InputHandler input, DiamondSquare diamondsquare, PathfindingManager PM, float SEED, ItemManager itemmanager){
		this.input = input;
		this.itemmanager = itemmanager;
		this.PM = PM;
		
		mapElems = new TextureAtlas("res/mapelems.txt");
		
		grassTexture = mapElems.findRegion("GrassTile");
		lightGrassTexture = mapElems.findRegion("LightGrassTile");
		stoneTexture = mapElems.findRegion("Block");
		
		
		//====== Generating Land
		diamondsquare.generateChunkHeights();
		float chunkheights[][] = diamondsquare.heights;
		for (int x = 0; x < WORLD_CHUNKS; x++){
			for (int y = 0; y < WORLD_CHUNKS; y++){
				chunkarray.add(new Chunk(x * 1f * CHUNK_SIZE, y * 1f * CHUNK_SIZE, chunkheights[x][y], chunkheights[x][y+1], chunkheights[x+1][y], chunkheights[x+1][y+1]));
				chunks[x][y] = chunkarray.peek();	
				chunks[x][y].show(grassTexture, lightGrassTexture, stoneTexture, diamondsquare, input, this, itemmanager);
				System.out.println("Chunk " + chunkarray.size + " created");
			}
		}

		//====== Determining Walkability
		for (int x = 0; x < WORLD_SIZE; x++){
			for (int y = 0; y < WORLD_SIZE; y++){
				if (getBlock(x, y) == null && getTile(x, y).isWalkable())
					walkable[x][y] = true;
				else 
					walkable[x][y] = false;
			}
		}

		areaGen = new Thread(new AreaGenerator(this));
		areaGen.start();
		

	}
	public void render(SpriteBatch batch, Vector3 touchpos, Rectangle camrect){
		time+= 1;
		if(time >= maxtime)
			time = 0;
		
		/*
		 * Sunrise = 21600
		 * Sunset = 68400
		 * Change time = 7200
		 */
		
//		if (time%100 == 0){
//			Gdx.app.log("Time", "" + time);
//		}
		
		if (time == 72000)
			ambientlight = 7;
		else if (time >= 18000 && time < 25200)
			ambientlight = (int)(13 * ((time - 18000f) / 7200f)) + 7;
		else if (time == 25200)
			ambientlight = 20;
		else if (time >= 64800 && time < 72000)
			ambientlight = (int)(13 * ((72000f - time) / 7200f)) + 7;
					
		//addLight(18, WorldMain.entitymanager.loadedentities.first().pos);
		
		for (float x = camrect.x / CHUNK_SIZE; x <= (camrect.x + camrect.width) / CHUNK_SIZE; x++){
			for (float y = camrect.y / CHUNK_SIZE; y <= (camrect.y + camrect.height) / CHUNK_SIZE; y++){
				if (x >= 0 && x <= WORLD_CHUNKS && y >= 0 && y <= WORLD_CHUNKS)
					chunks[(int) x][(int) y].render(batch, touchpos, ambientlight);
			}
		}
	}
	public void drawShader(SpriteBatch batch, Rectangle camrect){
		for (float x = camrect.x / CHUNK_SIZE; x <= (camrect.x + camrect.width) / CHUNK_SIZE; x++){
			for (float y = camrect.y / CHUNK_SIZE; y <= (camrect.y + camrect.height) / CHUNK_SIZE; y++){
				if (x >= 0 && x <= WORLD_CHUNKS && y >= 0 && y <= WORLD_CHUNKS)
					chunks[(int) x][(int) y].drawShader(batch);
			}
		}
	}
	public boolean getWalkable(float x, float y){
		if (x > 0 && y > 0 && x < WORLD_SIZE && y < WORLD_SIZE)
			return walkable[(int) x][(int) y];
		else return false;
	}
	public void updateWalkable(float x, float y, boolean iswalkable){
		walkable[(int) x][(int) y] = iswalkable;
		
		if (iswalkable == true){
			addtoArea(x, y);
		}else{
			getTile(x, y).setArea(0);
		}
	}
	public Block getBlock(float x, float y){
		return chunks[(int) (x/CHUNK_SIZE)][(int) (y/CHUNK_SIZE)].getBlock(x%CHUNK_SIZE, y%CHUNK_SIZE);
	}
	public Tile getTile(float x, float y){
		try{
			return chunks[(int) (x/CHUNK_SIZE)][(int) (y/CHUNK_SIZE)].getTile(x%CHUNK_SIZE, y%CHUNK_SIZE);
		}catch(Exception e){
			//Gdx.app.log("Map.getTile", "Array Index Out of Bounds Exception at tile " + x + ", " + y);
			return null;
		}
	}
	public Chunk getChunk(float x, float y){
		try{
			return chunks[(int) (x/CHUNK_SIZE)][(int) (y/CHUNK_SIZE)];
		}catch(Exception e){
			System.out.println("Entity out of bounds at: " + x + " " + y);
			return null;
		}

	}
	public boolean addBlock(int id, int x, int y){
		Block block;
//		switch(id){
//		case 1:
			block = new Stone(x, y, stoneTexture, input, itemmanager);

//		default:
//			block = null;
//		}
		return chunks[(int) (x / CHUNK_SIZE)][(int) (y / CHUNK_SIZE)].addBlock(block, x%CHUNK_SIZE, y%CHUNK_SIZE);
	}
	public void addLight(int brightness, Vector3 pos){
		
		Array<Node> litarea = floodfill.FloodLtd4(pos, brightness - ambientlight);
		
		for (int i = 0; i < litarea.size; i++){
			Node node = litarea.get(i);
			int distance = (int) node.getF();
			int addedlight = brightness - distance;
			if (addedlight < 0)
				addedlight = 0;
//			getTile(node.getX(), node.getY()).addLight(addedlight);
		}
		
//		Circle circle = new Circle((int) (pos.x) + .5f, (int) (pos.y) + .5f, range);
//		for (int x = (int) (pos.x - range); x < (int) (pos.x - range) + 2 * range; x++){
//			for (int y = (int) (pos.y - range); y < (int) (pos.y - range) + 2 * range; y++){
//				if (circle.contains(x + .5f, y + .5f)){
//					float distance = (float) Math.sqrt((pos.x - x) * (pos.x - x) + (pos.y-y) * (pos.y - y));
//					float addedlight = brightness - brightness * (distance / range);
//					if (addedlight < 0)
//						addedlight = 0;
//					getTile(x, y).addLight(addedlight);
//					
//				}
//			}
//		}
	}
	public void setAreas(Array<Area> areas){
		this.areas = areas;
	}
	public void doneGenerating(){
		isGenerating = false;
		Gdx.app.log("Map.doneGenerating", "Finished");
	}
	public boolean isGenerating(){
		return isGenerating;
	}
	public void addtoArea(float x, float y){
		
//		if (getTile(x, y - 1) != null && getTile(x, y - 1).getArea() != 0)
//			getTile(x,y).setArea(getTile(x, y - 1).getArea());
//		else if (getTile(x - 1, y) != null && getTile(x - 1, y).getArea() != 0)
//			getTile(x,y).setArea(getTile(x - 1, y).getArea());
//		else if (getTile(x + 1, y) != null && getTile(x + 1, y).getArea() != 0)
//			getTile(x,y).setArea(getTile(x + 1, y).getArea());
//		else if (getTile(x, y + 1) != null && getTile(x, y + 1).getArea() != 0)
//			getTile(x,y).setArea(getTile(x, y + 1).getArea());
//		else{
//			areas.add(areas.size + 1);
//			getTile(x, y).setArea(areas.peek());
//		}
	
	}
	public void dispose() {
		mapElems.dispose();

	}
	
	private class AreaGenerator implements Runnable{

		Map map;
		Array<Area> areas = new Array<Area>();
		
		public AreaGenerator(Map map){
			this.map = map;
		}
		
		@Override
		public void run() {
			try{
				PM.show(map);
				floodfill = PM.getFloodFill();
				Area area;
				Tile tile;
				//====== Area Filling
				for (int x = 0; x < WORLD_SIZE; x++){
					for (int y = 0; y < WORLD_SIZE; y++){
						if (getWalkable(x, y) && getTile(x, y).getArea() == 0){
							areas.add(new Area(areas.size, PM.getNodes(), map));
							area = areas.peek();
							area.setArea(floodfill.FloodWalk4(new Vector3(x, y, 0)));
							for(int i = 0; i < area.getArea().size; i++){
								tile = getTile(area.getArea().get(i).getX(), area.getArea().get(i).getY());
								tile.setArea(area.getID());
							}
						}
					}
				}
				
				map.setAreas(areas);
				map.doneGenerating();
			}catch(Exception e){
				Gdx.app.error("Map.AreaGenerator", "Error: " + e);
			}

		}
		
	}
	
}
