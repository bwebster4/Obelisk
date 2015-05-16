package com.obelisk.world.blocks;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.obelisk.InputHandler;
import com.obelisk.world.entities.ActiveEntity;
import com.obelisk.world.items.Item;
import com.obelisk.world.items.ItemManager;
import com.obelisk.world.items.ItemType;

public class Stone extends Block {

	int id = 1;
	int life = 100;
	
	
	public Stone (float x, float y, TextureRegion texture, InputHandler input, ItemManager itemmanager){
		super(x, y, texture, input, itemmanager);
	}

	@Override
	public void entityCollision(ActiveEntity entity) {
		
	}

	@Override
	public void mouseTouch() {
		
	}

	@Override
	public int getLife() {
		return life;
	}

	@Override
	public void doDamage(int damage) {
		life -= damage;
	}

	@Override
	public void destroy() {
		itemmanager.addItem(new Item(ItemType.StonePiece, itemmanager.getTexture("stone"), true, pos.x + width / 4, pos.y + height / 4));
		alive = false;
	}
	
}
