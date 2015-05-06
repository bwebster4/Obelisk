package com.obelisk.world.mapelems;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Tile extends MapElem {
	
	int area = 0;
	
	public Tile(float x, float y, TextureRegion texture){
		super(x, y, texture);
	}
	
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public int getArea(){
		return area;
	}
	public void setArea(int area){
		this.area = area;
	}
	public Sprite getSprite(){
		return sprite;
	}
	public abstract boolean isWalkable();
	public abstract int getType();
	public abstract float getMoveCost();
	
}
