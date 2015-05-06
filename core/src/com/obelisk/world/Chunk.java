package com.obelisk.world;

<<<<<<< HEAD
=======
import com.badlogic.gdx.graphics.Texture;
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.obelisk.InputHandler;
<<<<<<< HEAD
import com.obelisk.world.mapelems.Block;
import com.obelisk.world.mapelems.Stone;
import com.obelisk.world.items.ItemManager;
import com.obelisk.world.mapelems.GrassTile;
import com.obelisk.world.mapelems.LightGrassTile;
import com.obelisk.world.mapelems.MapElem;
import com.obelisk.world.mapelems.Tile;
=======
import com.obelisk.world.blocks.Block;
import com.obelisk.world.blocks.Stone;
import com.obelisk.world.items.ItemManager;
import com.obelisk.world.tiles.GrassTile;
import com.obelisk.world.tiles.LightGrassTile;
import com.obelisk.world.tiles.Tile;
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a

public class Chunk {
	DiamondSquare diamondsquare;
	InputHandler input;
	Block block;
	Tile tile;
	Map map;
	
	float x, y;
	float bottomleft, topleft, bottomright, topright;
	
<<<<<<< HEAD
	TextureRegion grasstiletexture;
	float grassthreshold = 7.8f;
	TextureRegion lightgrasstiletexture;
	float lightgrassthreshold = 7.8f;
	TextureRegion stonetexture;
	float stonethreshold = 8.1f;
	
	
=======
	Texture grasstiletexture;
	float grassthreshold = 7.8f;
	Texture lightgrasstiletexture;
	float lightgrassthreshold = 7.8f;
	Texture stonewalltexture;
	TextureRegion stonetexture;
	float stonethreshold = 8.1f;
	
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
	Array<Tile> tilearray = new Array<Tile>();
	Tile[][] tiles = new Tile[Map.CHUNK_SIZE][Map.CHUNK_SIZE];
	Array<Block> blockarray = new Array<Block>();
	Block[][] blocks = new Block[Map.CHUNK_SIZE][Map.CHUNK_SIZE];
	
	public Chunk(float x, float y, float bottomleft, float topleft, float bottomright, float topright){
		this.x = x;
		this.y = y;
		
		this.bottomleft = bottomleft;
		this.bottomright = bottomright;
		this.topleft = topleft;
		this.topright = topright;
	}
<<<<<<< HEAD
	public void show(TextureRegion grasstiletexture, TextureRegion lightgrasstiletexture, TextureRegion stonetexture, DiamondSquare diamondsquare, InputHandler input, Map map, ItemManager itemmanager){
		this.grasstiletexture = grasstiletexture;
		this.lightgrasstiletexture = lightgrasstiletexture;
		this.stonetexture = stonetexture;
=======
	public void show(Texture grasstiletexture, Texture lightgrasstiletexture, TextureRegion stonetexture, Texture stonewalltexture, DiamondSquare diamondsquare, InputHandler input, Map map, ItemManager itemmanager){
		this.grasstiletexture = grasstiletexture;
		this.lightgrasstiletexture = lightgrasstiletexture;
		this.stonetexture = stonetexture;
		this.stonewalltexture = stonewalltexture;
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
		this.input = input;
		this.map = map;
		
		diamondsquare.generateTileHeights(bottomleft, bottomright, topleft, topright);
		float heights[][] = diamondsquare.getHeights();
		for (int row = 0; row < Map.CHUNK_SIZE; row++){
			for (int col = 0; col < Map.CHUNK_SIZE; col++){
				if (heights[row][col] < grassthreshold){
					tilearray.add(new GrassTile(row * 1f + getX(), col * 1f + getY(), grasstiletexture));
					tiles[row][col] = tilearray.peek();
				}else if (heights[row][col] >= grassthreshold){
					tilearray.add(new LightGrassTile(row * 1f + getX(), col * 1f+  getY(), lightgrasstiletexture));
					tiles[row][col] = tilearray.peek();
				}
				if (heights[row][col] >= stonethreshold){
					blockarray.add(new Stone(row *1f + getX(), col *1f + getY(), stonetexture, input, itemmanager));
					blocks[row][col] = blockarray.peek();
				}
			}	
		}
	}
	public void render(SpriteBatch batch, Vector3 touchpos, int brightness){
		for (int i = 0; i < tilearray.size; i++){
			tile = tilearray.get(i);
<<<<<<< HEAD
			tile.render(batch);
		}
		for (int i = 0; i < blockarray.size; i++){
			block = blockarray.get(i);
			block.render(batch);
=======
			tile.render(batch, brightness);
		}
		for (int i = 0; i < blockarray.size; i++){
			block = blockarray.get(i);
			block.render(batch, touchpos);
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
			if (block.getAlive() == false){
				blockarray.removeIndex(i);
			}
		}
		for (int x = 0; x < Map.CHUNK_SIZE; x++){
			for (int y = 0; y < Map.CHUNK_SIZE; y++){
				//tiles[x][y].render(batch);
				if (blocks[x][y] != null){
					//blocks[x][y].render(batch, touchpos);
					if (blocks[x][y].getAlive() == false){
						map.updateWalkable(this.x + x,this.y + y, true);
						blocks[x][y] = null;
					}
				}

			}
		}
	}
	public void drawShader(SpriteBatch batch){
		for (int i = 0; i < tilearray.size; i++){
			tile = tilearray.get(i);
<<<<<<< HEAD
//			tile.drawShader(batch);
=======
			tile.drawShader(batch);
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
		}
	}
	public Block getBlock(float x, float y){
		return blocks[(int) x][(int) y];
	}
	public Tile getTile(float x, float y){
		return tiles[(int) x][(int) y];
	}
	public Array<Block> getBlockArray(){
		return blockarray;
	}
	public boolean addBlock(Block block, int x, int y){
		if (blocks[(int) x][(int) y] == null){
			blockarray.add(block);
			blocks[(int) x][(int) y] = block;
			System.out.println(block + " added at " + x + " " + y);
			map.updateWalkable(this.x + x,this.y + y, false);
			return true;
		}else{
			System.out.println("Block cannot be added: space filled");
			return false;
		}
	}
	
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	
	
	public float getBottomLeft(){
		return bottomleft;
	}
	public float getTopLeft(){
		return topleft;
	}
	public float getBottomRight(){
		return bottomright;
	}
	public float getTopRight(){
		return topright;
	}
	public int atEdgeX(float x){
		if (x < 1)
			return 0;
		if (x > Map.CHUNK_SIZE - 1)
			return 1;
		return 2;
	}
	public int atEdgeY(float y){
		if (y < 1)
			return 0;
		if (y > Map.CHUNK_SIZE - 1)
			return 1;
		return 2;
	}
}
