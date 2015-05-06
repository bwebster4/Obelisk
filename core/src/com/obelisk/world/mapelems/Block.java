package com.obelisk.world.mapelems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.obelisk.InputHandler;
import com.obelisk.world.entities.ActiveEntity;
import com.obelisk.world.items.ItemManager;

public abstract class Block extends MapElem{

	public float width, height;
	InputHandler input;
	ItemManager itemmanager;
	int type;
	int life;
	boolean alive;
	
	public Block (float x, float y, TextureRegion texture, InputHandler input, ItemManager itemmanager){
		super(x, y, texture);
		this.input = input;
		this.itemmanager = itemmanager;
		
		alive = true;

		width = sprite.getWidth();
		height = sprite.getHeight();
	}
	
	public int getType(){
		return type;
	}
	public boolean getAlive(){
		return alive;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public abstract void entityCollision(ActiveEntity entity);
	public abstract void mouseTouch();
	public abstract void doDamage(int damage);
	public abstract int getLife();
	public abstract void destroy();
	
	public void render(SpriteBatch batch){
		if (getLife() < 1){
			destroy();
		}
		super.render(batch);
	}

}
