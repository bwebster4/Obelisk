package com.obelisk.world.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class ItemManager {
	
	public Array<Item> worlditems = new Array<Item>();

	Item item;
	
	// Item Types
	static final int object = 0, meleeWeapon = 1, rangeWeapon = 2, ammo = 3, armor = 4, storage = 5;
	
	Texture itemtextures;
	TextureRegion 
					pickaxetexture, 

					stonetexture;
	
	public ItemManager(){
		itemtextures = new Texture("res/Items.png");
		pickaxetexture = new TextureRegion(itemtextures, 0, 0, 64, 64);
		stonetexture = new TextureRegion(itemtextures, 64, 64, 64, 64);
	}
	public TextureRegion getTexture(String string){
		if (string == "sword")
			return pickaxetexture;
		else if (string == "pickaxe")
			return pickaxetexture;
		else if (string == "stone")
			return stonetexture;
		else return null;
	}
	
	public void addItem(Item item){
		worlditems.add(item);
	}
	public void removeItem(Item item){
		worlditems.removeValue(item, true);
	}
	public void render(SpriteBatch batch){
		for (int i = 0; i < worlditems.size; i++){
			item = worlditems.get(i);
			item.render(batch, false);
			if (item.isDisposable())
				worlditems.removeValue(item, true);
		}
	}
}
