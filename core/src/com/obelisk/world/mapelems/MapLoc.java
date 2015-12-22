package com.obelisk.world.mapelems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.obelisk.world.items.ItemManager;

public class MapLoc {

	int x, y;
	int plate, region;
	Array<MapElem> data = new Array<MapElem>();
	ItemManager itemManager;
	
	public MapLoc(int x, int y, int plate, int region, ItemManager itemManager){
		this.x = x;
		this.y = y;
		this.plate = plate;
		this.region = region;
		this.itemManager = itemManager;
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
	
	public void addElem(MapElem elem){
		data.add(elem);
	}
	public void setPlate(int plate){
		this.plate = plate;
	}
	public int getPlate(){
		return plate;
	}
}
