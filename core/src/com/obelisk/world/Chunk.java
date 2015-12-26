package com.obelisk.world;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.obelisk.InputHandler;
import com.obelisk.world.items.ItemManager;
import com.obelisk.world.mapelems.ElemManager;
import com.obelisk.world.mapelems.MapElem;
import com.obelisk.world.mapelems.MapElemType;
import com.obelisk.world.mapelems.MapLoc;

public class Chunk {
	DiamondSquare diamondsquare;
	InputHandler input;
	
	int chunkSize;
	
	MapLoc[][] elems;
	ElemManager elemManager;
	
	public Chunk(int chunkSize){
		this.chunkSize = chunkSize;

		elems = new MapLoc[chunkSize][chunkSize];
	}

	public void show(int chunkX, int chunkY, ItemManager itemManager, ElemManager elemManager, FileHandle saveFile){
		this.elemManager = elemManager;
		
		byte[] saveData = saveFile.readBytes();
		
				
		for(int x =  0; x < chunkSize; x++){
			for(int y = 0; y < chunkSize; y++){
				elems[x][y] = new MapLoc();
				elems[x][y].show(itemManager);
				elems[x][y].addElem(elemManager.getNewElem(MapElemType.Grass, chunkX + x, chunkY + y));
				elems[x][y].addElem(elemManager.getNewElem(MapElemType.Shader, chunkX + x, chunkY + y));
			}
		}
	}
	public void render(SpriteBatch batch, Vector3 touchpos){
		for(int x =  0; x < chunkSize; x++){
			for(int y = 0; y < chunkSize; y++){
				elems[x][y].renderTiles(batch);
			}
		}
		for(int x =  0; x < chunkSize; x++){
			for(int y = 0; y < chunkSize; y++){
				elems[x][y].renderBlocks(batch);
			}
		}
	}
	public FileHandle save(String directory, ByteArrayOutputStream writer, int number){
		byte[] saveData = new byte[0];
		writer.reset();
		for(int x = 0; x < chunkSize; x++){
			for(int y = 0; y < chunkSize; y++){
				elems[x][y].save(writer);
				saveData = concatByte(saveData, elems[x][y].save(writer));
			}
		}
		FileHandle chunkFile = Gdx.files.local(directory + "/chunk" + Integer.toString(number) + ".bin");
		chunkFile.writeBytes(saveData, false);
		return chunkFile;
	}
	private byte[] concatByte(byte[] a, byte[] b){
		byte[] c = Arrays.copyOf(a, a.length + b.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
		
	}
	public boolean addElem(MapElem elem, int x, int y){
		return elems[x][y].addElem(elem);
	}
	
	public void renderShaders(SpriteBatch batch, int brightness){
		for(int x =  0; x < chunkSize; x++){
			for(int y = 0; y < chunkSize; y++){
				elems[x][y].renderShaders(batch, brightness);
			}
		}
	}
	

	public boolean getWalkable(int x, int y){
		return elems[x][y].getWalkable();
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
