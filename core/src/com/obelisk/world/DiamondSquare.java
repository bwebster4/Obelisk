package com.obelisk.world;

import com.badlogic.gdx.math.MathUtils;

public class DiamondSquare {

	int CHUNK_SIZE;
	int WORLD_SIZE;
	float SEED;
	float[][] heights;
	float h1 = 3f;
	float h2 = 1f;
	
	public void show(float SEED){
		this.CHUNK_SIZE = Map.CHUNK_SIZE + 1;
		this.WORLD_SIZE = Map.WORLD_SIZE + 1;
		this.SEED = SEED;
	}
	public float[][] getHeights(){
		return heights;
	}
	
	public void generateTileHeights(float bottomleft, float bottomright, float topleft, float topright){
		heights = new float[CHUNK_SIZE][CHUNK_SIZE];
		heights[0][0] = bottomleft;
		heights[0][CHUNK_SIZE - 1] = topleft;
		heights[CHUNK_SIZE - 1][0] = bottomright;
		heights[CHUNK_SIZE -1][CHUNK_SIZE -1] = topright;
		
		for (int sideLength = CHUNK_SIZE - 1; sideLength >= 2; sideLength /= 2, h1 /= 2){
			int halfSide = sideLength / 2;
			
			for (int x = 0; x < CHUNK_SIZE - 1; x+= sideLength){
				for (int y = 0; y < CHUNK_SIZE - 1; y+= sideLength){
					float avg = heights[x][y] + heights[x + sideLength][y] + heights[x][y + sideLength] + heights[x + sideLength][y + sideLength];
					avg /= 4.0f;
					heights[x + halfSide][y + halfSide] = (avg + (MathUtils.random() * 2 * h1) - h1);
				}
			}
			for (int x = 0; x < CHUNK_SIZE; x += halfSide){
				for (int y = (x + halfSide)%sideLength; y < CHUNK_SIZE; y += sideLength){
					float avg = heights[(x - halfSide + CHUNK_SIZE - 1)%(CHUNK_SIZE - 1)][y] + 
								heights[(x + halfSide)%(CHUNK_SIZE - 1)][y] + 
								heights[x][(y + halfSide)%(CHUNK_SIZE - 1)] + 
								heights[x][(y - halfSide + CHUNK_SIZE - 1)%(CHUNK_SIZE - 1)];
					avg /= 4.0f;
					heights[x][y] = avg;
					
					if (x == 0) heights[CHUNK_SIZE -1][y] = avg;
					if (y == 0) heights[x][CHUNK_SIZE - 1] = avg;
				}
			}
		}
		/*
		for(float[] row : heights){
			  for(float d : row){
			    System.out.printf("%8.3f ", d);
			  }
			  System.out.println();
			}
		
		/*
		FileHandle chunk = new FileHandle("chunk");
		try {
			chunk.file().createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}
	public void generateChunkHeights(){
		heights = new float[WORLD_SIZE][WORLD_SIZE];
		heights[0][0] = heights[0][WORLD_SIZE - 1] = heights[WORLD_SIZE - 1][0] = heights[WORLD_SIZE -1][WORLD_SIZE -1] = SEED;
		
		for (int sideLength = WORLD_SIZE - 1; sideLength >= 2; sideLength /= 2){
			int halfSide = sideLength / 2;
			
			for (int x = 0; x < WORLD_SIZE - 1; x+= sideLength){
				for (int y = 0; y < WORLD_SIZE - 1; y+= sideLength){
					float avg = heights[x][y] + heights[x + sideLength][y] + heights[x][y + sideLength] + heights[x + sideLength][y + sideLength];
					avg /= 4.0f;
					heights[x + halfSide][y + halfSide] = (avg + (MathUtils.random() * 2 * h2) - h2);
					if (x + halfSide == (WORLD_SIZE - 1) / 2 && y + halfSide == (WORLD_SIZE - 1) / 2)
						heights[x + halfSide][y + halfSide] = SEED;
				}
			}
			for (int x = 0; x < WORLD_SIZE; x += halfSide){
				for (int y = (x + halfSide)%sideLength; y < WORLD_SIZE; y += sideLength){
					float avg = heights[(x - halfSide + WORLD_SIZE - 1)%(WORLD_SIZE - 1)][y] + 
								heights[(x + halfSide)%(WORLD_SIZE - 1)][y] + 
								heights[x][(y + halfSide)%(WORLD_SIZE - 1)] + 
								heights[x][(y - halfSide + WORLD_SIZE - 1)%(WORLD_SIZE - 1)];
					avg /= 4.0f;
					heights[x][y] = avg;
					
					if (x == 0) heights[WORLD_SIZE -1][y] = avg;
					if (y == 0) heights[x][WORLD_SIZE - 1] = avg;
				}
			}
		}
		
		/*
		FileHandle chunk = new FileHandle("chunk");
		try {
			chunk.file().createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}
}
