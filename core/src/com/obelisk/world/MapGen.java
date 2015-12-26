package com.obelisk.world;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.obelisk.world.items.ItemManager;
import com.obelisk.world.mapelems.MapLoc;

public class MapGen {

	int worldChunks;
	float[][] heights;
	
	Chunk[][] map;
	ByteArrayOutputStream writer = new ByteArrayOutputStream();
	
	// Map Sizes
	public static int SMALL = 32, MEDIUM = 64, LARGE = 128;
	
	public MapGen(){

	}
	
	public FileHandle generateMap(String name, int size, int plateCount){
		
		worldChunks = size;
		
		map = new Chunk[worldChunks][worldChunks];
		
		
		// MapLoc creation
		for(int x = 0; x < worldChunks; x++){
			for(int y = 0; y < worldChunks; y++){
				map[x][y] = new Chunk(x, y, y, y, y);
			}
		}
		
		// Plate Formation
		for(int i = 1; i <= plateCount; i++){
			
		}
		
		// Writing to File
		/*
		 * File data in the following order, separated by %:
		 * name%
		 * id, type, 
		 * x, y, plate, region, elemNames %  <---mapLocs
		 * 
		 */
		String directory = name + "/World";
		FileHandle folder = Gdx.files.local(directory);
		int number = 0;
		for(int x = 0; x < worldChunks; x++){
			for(int y = 0; y < worldChunks; y++){
				map[x][y].save(directory, writer, number);
				number++;
			}
		}

		return folder;
		
	}
	

	
}
