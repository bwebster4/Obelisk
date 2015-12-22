package com.obelisk.world;

import com.badlogic.gdx.utils.Array;
import com.obelisk.world.items.ItemManager;
import com.obelisk.world.mapelems.MapLoc;

public class MapGen {

	int CHUNKSIZE;
	int WORLDSIZE;
	float[][] heights;
	
	MapLoc[][] map;
	
	public MapGen(int chunkSize, int worldSize){
		this.CHUNKSIZE = chunkSize;
		this.WORLDSIZE = worldSize;
		
		map = new MapLoc[WORLDSIZE][WORLDSIZE];
		
	}
	
	public MapLoc[][] generateMap(ItemManager itemManager, int plateCount){
		
		// MapLoc creation
		for(int x = 0; x < WORLDSIZE; x++){
			for(int y = 0; y < WORLDSIZE; y++){
				map[x][y] = new MapLoc(x, y, 0, 0, itemManager);
			}
		}
		
		// Plate Formation
		for(int i = 1; i <= plateCount; i++){
			
		}
		
		return map;
		
	}
	
	private class Plate{
		
		
		
	}
	
}
