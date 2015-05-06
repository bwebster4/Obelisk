package com.obelisk.world.blocks;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.obelisk.InputHandler;
import com.obelisk.world.entities.ActiveEntity;
import com.obelisk.world.items.ItemManager;

public abstract class Block {

	public Vector3 pos;
	public float width, height;
	TextureRegion texture;
	Sprite sprite;
	InputHandler input;
	ItemManager itemmanager;
	int type;
	int life;
	boolean alive;
	
	public Block (float x, float y, TextureRegion texture, InputHandler input, ItemManager itemmanager){
		pos = new Vector3(x, y, 0);
		this.texture = texture;
		this.input = input;
		this.itemmanager = itemmanager;
		
		alive = true;
		
		sprite = new Sprite(texture);
		sprite.setPosition(pos.x, pos.y);
		sprite.setSize(1f, 1f);

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
		return (int) pos.x;
	}
	public int getY(){
		return (int) pos.y;
	}
	
	public abstract void entityCollision(ActiveEntity entity);
	public abstract void mouseTouch();
	public abstract void doDamage(int damage);
	public abstract int getLife();
	public abstract void destroy();
	
	public void render(SpriteBatch batch, Vector3 touchpos){
		if (getLife() < 1){
			destroy();
		}

		sprite.draw(batch);
	}

}
