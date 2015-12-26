package com.obelisk.world.mapelems;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.obelisk.world.items.ItemManager;

public class MapLoc {

	int x, y;
	Array<MapElem> data = new Array<MapElem>();
	ItemManager itemManager;
	
	public void show(ItemManager itemManager, byte[] saveData, ByteArrayInputStream reader){
		this.itemManager = itemManager;
		
		reader.read();
	}
	public byte[] save(ByteArrayOutputStream writer) {
		byte[] byteData;
		
		writer.write(x);
		writer.write(y);
		for(int i = 0; i < data.size; i++){
			try {
				writer.write(data.get(i).getType().getName().getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			writer.write("%".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byteData = writer.toByteArray();
		writer.reset();
		return byteData;
	}
	
	public void renderTiles(SpriteBatch batch){
		for(int i = 0; i < data.size; i++){
			if(data.get(i).getSuperType() == MapElem.tile){
				data.get(i).render(batch);
			}
		}
	}
	public void renderBlocks(SpriteBatch batch){
		for(int i = 0; i < data.size; i++){
			MapElem block = data.get(i);
			if(block.getSuperType() == MapElem.block){
				if(block.getLife() < 1){
					itemManager.getNewItem(block.getType().getDropItem(), true, x, y);
					data.removeIndex(i);
				}
				block.render(batch);
				
			}
		}
	}
	public void renderShaders(SpriteBatch batch, int ambientlight){
		for(int i = 0; i < data.size; i++){
			if(data.get(i).getSuperType() == MapElem.shader){
				data.get(i).render(batch, ambientlight);
			}
		}
	}
	
	public boolean addElem(MapElem elem){
		for(int i = 0; i < data.size; i++){
			if(data.get(i).getSuperType() == MapElem.block){
				System.out.println("Block cannot be added: space filled");
				return false;
			}
		}
		data.add(elem);
		return true;
	}
	public boolean getWalkable(){
		for(int i = 0; i < data.size; i++){
			if(!data.get(i).getType().isWalkable())
				return false;
		}
		return true;
	}
}
