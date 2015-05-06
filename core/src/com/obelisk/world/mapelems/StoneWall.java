package com.obelisk.world.mapelems;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.obelisk.InputHandler;
import com.obelisk.world.entities.ActiveEntity;
import com.obelisk.world.items.ItemManager;

public class StoneWall extends Block{

	int id = 2;
	int life = 110;
	
	public StoneWall(float x, float y, TextureRegion texture, InputHandler input, ItemManager itemmanager) {
		super(x, y, texture, input, itemmanager);
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public void entityCollision(ActiveEntity entity) {
		
	}

	@Override
	public void mouseTouch() {
			
	}

	public int getLife(){
		return life;
	}

	@Override
	public void doDamage(int damage) {
		life -= damage;
	}

	@Override
	public void destroy() {
		alive = false;
	}

}
