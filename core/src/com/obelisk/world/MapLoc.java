package com.obelisk.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.obelisk.world.mapelems.MapElem;

public class MapLoc {

	int x, y;
	Array<MapElem> data = new Array<MapElem>();
	
	public MapLoc(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void renderLower(SpriteBatch batch){
		
	}
	
	public void addElem(MapElem elem){
		data.add(elem);
	}
}
